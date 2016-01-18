package com.slamur.app.accounts.model.category.dao;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

import java.util.List;

public interface CategoryDao extends ListDao<Category> {

    Category addIncomeCategory(String name, String description);

    Category addExpenseCategory(String name, String description);

    void removeCategory(int index);

    List<Category> filterCategoriesOfType(OperationType type);
}
