package com.slamur.app.accounts.model.category.dao;

import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.category.ExpenseCategory;
import com.slamur.app.accounts.model.category.IncomeCategory;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

import java.util.List;

public interface CategoryDao extends ListDao<Category> {

    IncomeCategory addIncomeCategory(String name, String description);

    ExpenseCategory addExpenseCategory(String name, String description);

    void removeCategory(int index);

    <CategoryType extends Category> List<Category> filterCategoriesOfClass(Class<CategoryType> categoryClass);
}
