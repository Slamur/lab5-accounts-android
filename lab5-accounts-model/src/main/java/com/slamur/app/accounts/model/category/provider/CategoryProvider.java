package com.slamur.app.accounts.model.category.provider;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.library.daolibrary.base.provider.collection.ListItemProvider;

public interface CategoryProvider extends ListItemProvider<Category> {

    <CategoryType extends Category> void loadCategoriesOfClass(Class<CategoryType> categoryClass);
}
