package com.slamur.app.accounts.model.category.dao;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.library.daolibrary.base.dao.list.ListDao;
import com.slamur.library.daolibrary.base.dao.list.ListDaoImpl;

public abstract class CategoryDaoImpl<CategoryType extends Category>
        extends ListDaoImpl<CategoryType>
        implements CategoryDao<CategoryType> {

    private long nextCategoryId;

    public CategoryDaoImpl() {
        super();

        this.nextCategoryId = 0;
    }

    @Override
    protected void setItemInfo(CategoryType item, Object... itemInfo) {

    }

    @Override
    protected void removeItemInfo(CategoryType item) {

    }

    protected abstract CategoryType createCategory(long id, String name);

    protected CategoryType createCategory(String name) {
        CategoryType newCategory = createCategory(nextCategoryId, name);
        ++nextCategoryId;

        return newCategory;
    }

    @Override
    public CategoryType addCategory(String name) {
        CategoryType category = createCategory(name);
        super.addItem(category);

        return category;
    }

    @Override
    public void removeCategory(int index) {
        super.removeItem(index);
    }
}
