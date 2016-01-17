package com.slamur.app.accounts.android.dao.category;

import android.content.Context;

import com.slamur.app.accounts.android.provider.category.CategoryActivityProvider;
import com.slamur.app.accounts.android.settings.AccountsSettingsUtils;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.category.ExpenseCategory;
import com.slamur.app.accounts.model.category.IncomeCategory;
import com.slamur.app.accounts.model.category.dao.CategoryDaoImpl;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.gson.GsonUtils;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemParameterLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;

import java.util.List;

public class CategoryBaseDao extends CategoryDaoImpl
implements CategoryActivityProvider {

    private static CategoryBaseDao instance;

    public static CategoryBaseDao getInstance(Context context) {
        if (null == instance) {
            instance = new CategoryBaseDao(context);

            List<Category> categories = AccountsSettingsUtils.getIncomeCategories(context);
            categories.addAll(AccountsSettingsUtils.getExpenseCategories(context));

            instance.updateItems(categories);

            instance.addListener(instance);
        }

        return instance;
    }

    private final Context context;

    private CategoryBaseDao(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void loadItem(int index) {
        notifyListeners(
                new ItemLoadEvent<>(this, getItem(index))
        );
    }

    @Override
    public void loadItems() {
        notifyListeners(
                new ItemsLoadEvent<>(this, getItems())
        );
    }

    @Override
    public void loadItem(long id) {
        notifyListeners(
                new ItemLoadEvent<>(this, getItem(id))
        );
    }

    @Override
    public void loadItemsCount() {
        notifyListeners(
                new ItemParameterLoadEvent<>(this, getItemCount(), ITEMS_COUNT_PARAMETER_NAME)
        );
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
    }

    @Override
    public void onEvent(Event<Category> event) {
        if (this == event.getNotifier()) {
            Category[] incomes = filterCategoriesOfClass(IncomeCategory.class).toArray(new Category[0]);
            AccountsSettingsUtils.setStringValue(context, AccountsSettingsUtils.INCOME_CATEGORY_DAO_KEY, GsonUtils.toJson(incomes));

            Category[] expenses = filterCategoriesOfClass(ExpenseCategory.class).toArray(new Category[0]);
            AccountsSettingsUtils.setStringValue(context, AccountsSettingsUtils.EXPENSE_CATEGORY_DAO_KEY, GsonUtils.toJson(expenses));
        } else if (event instanceof ListEvent) {
            ListEvent<Category> listEvent = event.toType();

            Category category = listEvent.getItem();
            int index = listEvent.getIndex();

            if (listEvent.isAction(DaoEvent.Action.ADD)) {
                if (category instanceof IncomeCategory) {
                    this.addIncomeCategory(category.getName(), category.getDescription());
                } else if (category instanceof ExpenseCategory) {
                    this.addExpenseCategory(category.getName(), category.getDescription());
                } else {
                    throw new UnsupportedOperationException();
                }
            } else if (listEvent.isAction(DaoEvent.Action.UPDATE)) {
                this.updateItem(index, category);
            } else if (listEvent.isAction(DaoEvent.Action.REMOVE)) {
                this.removeItem(index);
            }
        }
    }

    @Override
    public <CategoryType extends Category> void loadCategoriesOfClass(Class<CategoryType> categoryClass) {
        List<Category> filtered = this.filterCategoriesOfClass(categoryClass);

        notifyListeners(
                new ItemsLoadEvent<>(this, filtered)
        );
    }
}
