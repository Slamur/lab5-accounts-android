package com.slamur.app.accounts.model.category.expense.dao;

import com.slamur.app.accounts.model.category.dao.CategoryDaoImpl;
import com.slamur.app.accounts.model.category.expense.ExpenseCategory;
import com.slamur.app.accounts.model.category.income.IncomeCategory;

public class ExpenseCategoryDao extends CategoryDaoImpl<ExpenseCategory> {

    public ExpenseCategoryDao() {
        super();
    }

    @Override
    public ExpenseCategory createCategory(long id, String name) {
        return new ExpenseCategory(id, name);
    }

    @Override
    public Class<ExpenseCategory> getItemClass() {
        return ExpenseCategory.class;
    }
}
