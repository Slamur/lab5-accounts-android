package com.slamur.app.accounts.model.category.income.dao;

import com.slamur.app.accounts.model.category.dao.CategoryDaoImpl;
import com.slamur.app.accounts.model.category.income.IncomeCategory;

public class IncomeCategoryDao extends CategoryDaoImpl<IncomeCategory> {

    public IncomeCategoryDao() {
        super();
    }

    @Override
    public IncomeCategory createCategory(long id, String name) {
        return new IncomeCategory(id, name);
    }

    @Override
    public Class<IncomeCategory> getItemClass() {
        return IncomeCategory.class;
    }
}
