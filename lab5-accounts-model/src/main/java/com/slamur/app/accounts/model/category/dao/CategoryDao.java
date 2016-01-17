package com.slamur.app.accounts.model.category.dao;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

public interface CategoryDao <CategoryType extends Category> extends ListDao<CategoryType> {

    CategoryType addCategory(String name);

    void removeCategory(int index);
}
