package com.slamur.app.accounts.android.activity.category;

import com.slamur.app.accounts.android.dao.category.CategoryActivityDao;
import com.slamur.app.accounts.android.provider.category.CategoryActivityProvider;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.library.daolibrary.android.activity.ItemActivity;

public interface CategoryActivity extends ItemActivity<Category, CategoryActivityDao, CategoryActivityProvider> {

    String CATEGORY_TYPE_EXTRA = "Category activity type extra";

    String INCOME_TYPE_NAME = "Income", EXPENSE_TYPE_NAME = "Expense";
}
