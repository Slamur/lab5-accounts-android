package com.slamur.app.accounts.model.category.dao;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.category.ExpenseCategory;
import com.slamur.app.accounts.model.category.IncomeCategory;
import com.slamur.library.daolibrary.base.dao.list.ListDaoImpl;
import com.slamur.library.daolibrary.base.function.ItemPredicate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CategoryDaoImpl
        extends ListDaoImpl<Category>
        implements CategoryDao {

    public CategoryDaoImpl() {
        super();
    }

    @Override
    protected void setItemInfo(Category item, Object... itemInfo) {

    }

    @Override
    protected void removeItemInfo(Category item) {

    }

    protected <CategoryType extends Category> CategoryType createCategory(Class<CategoryType> categoryClass, long id, String name, String description) {
        try {
            return categoryClass.getConstructor(long.class, String.class, String.class).newInstance(id, name, description);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }

    protected <CategoryType extends Category> CategoryType createCategory(Class<CategoryType> categoryClass, String name, String description) {
        return createCategory(categoryClass, generateNextItemId(), name, description);
    }

    @Override
    public IncomeCategory addIncomeCategory(String name, String description) {
        IncomeCategory category = createCategory(IncomeCategory.class, name, description);
        super.addItem(category);

        return category;
    }

    @Override
    public ExpenseCategory addExpenseCategory(String name, String description) {
        ExpenseCategory category = createCategory(ExpenseCategory.class, name, description);
        super.addItem(category);

        return category;
    }

    @Override
    public void removeCategory(int index) {
        super.removeItem(index);
    }

    @Override
    public <CategoryType extends Category> List<Category> filterCategoriesOfClass(final Class<CategoryType> categoryClass) {
        return filterItems(new ItemPredicate<Category>() {
            @Override
            public boolean check(Category item) {
                return categoryClass.isInstance(item);
            }
        });
    }

    @Override
    public Class<Category> getItemClass() {
        return Category.class;
    }
}
