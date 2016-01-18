package com.slamur.app.accounts.model.category.dao;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.base.dao.list.ListDaoImpl;
import com.slamur.library.daolibrary.base.function.ItemPredicate;

import java.util.List;

public class CategoryDaoImpl
        extends ListDaoImpl<Category>
        implements CategoryDao {

    public CategoryDaoImpl() {
        super();
    }

    @Override
    protected void setItemInfo(Category item, Object... itemInfo) {
        nextItemId = Math.max(nextItemId, item.getId() + 1);
    }

    @Override
    protected void removeItemInfo(Category item) {

    }

    protected Category createCategory(String name, String description, OperationType type) {
        return new Category(generateNextItemId(), name, description, type);
    }

    protected Category addCategory(String name, String description, OperationType type) {
        Category category = createCategory(name, description, type);
        super.addItem(category);

        return category;
    }

    @Override
    public Category addIncomeCategory(String name, String description) {
        return addCategory(name, description, OperationType.INCOME);
    }

    @Override
    public Category addExpenseCategory(String name, String description) {
        return addCategory(name, description, OperationType.EXPENSE);
    }

    @Override
    public void removeCategory(int index) {
        super.removeItem(index);
    }

    @Override
    public List<Category> filterCategoriesOfType(final OperationType type) {
        return filterItems(new ItemPredicate<Category>() {
            @Override
            public boolean check(Category item) {
                return item.getType().equals(type);
            }
        });
    }

    @Override
    public Class<Category> getItemClass() {
        return Category.class;
    }
}
