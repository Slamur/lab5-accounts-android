package com.slamur.app.accounts.model.category.provider;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.base.provider.collection.ListItemProvider;

public interface CategoryProvider extends ListItemProvider<Category> {

    void loadCategoriesOfType(OperationType type);
}
