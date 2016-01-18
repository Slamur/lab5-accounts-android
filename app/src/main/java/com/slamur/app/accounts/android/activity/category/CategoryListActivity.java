package com.slamur.app.accounts.android.activity.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.TextView;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.dao.category.CategoryActivityDao;
import com.slamur.app.accounts.android.dao.category.CategoryBaseDao;
import com.slamur.app.accounts.android.list_view.category.CategoryListAdapter;
import com.slamur.app.accounts.android.provider.category.CategoryActivityProvider;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.activity.list.ListItemActivityImpl;
import com.slamur.library.daolibrary.base.event.Event;

public class CategoryListActivity extends ListItemActivityImpl<Category, CategoryActivityDao, CategoryActivityProvider, CategoryListAdapter>
implements CategoryActivity {

    protected OperationType mCategoryType;
    protected TextView mActivityTitle;

    @IdRes
    protected int getActivityTitleId() {
        return R.id.activity_list_categories_title;
    }

    @Override
    protected void onListActivityCreate(Bundle savedInstanceState) {
        this.mCategoryType = OperationType.valueOf(
                getIntent().getStringExtra(CATEGORY_TYPE_EXTRA)
        );

        if (null == mCategoryType) {
            setResult(RESULT_CANCELED);
            finish();
        }

        mActivityTitle = AndroidUtils.findViewById(this, getActivityTitleId());

        switch (mCategoryType) {
            case INCOME:
                mActivityTitle.setText(INCOME_TYPE_NAME + "s");
                break;
            case EXPENSE:
                mActivityTitle.setText(EXPENSE_TYPE_NAME + "s");
                break;
        }
    }

    @Override
    protected CategoryListAdapter createListAdapter() {
        return new CategoryListAdapter(this);
    }

    @Override
    protected int getListViewId() {
        return R.id.activity_list_categories_list_view;
    }

    @Override
    protected int getAddItemViewId() {
        return R.id.activity_list_categories_add_button;
    }

    @Override
    protected int getRefreshItemsViewId() {
        return R.id.activity_list_categories_refresh_button;
    }

    @Override
    protected void prepareShowInfoBundle(Bundle extras, int index) {
        extras.putString(CATEGORY_TYPE_EXTRA, mCategoryType.name());
    }

    @Override
    protected void prepareAddItemBundle(Bundle extras) {
        extras.putString(CATEGORY_TYPE_EXTRA, mCategoryType.name());
    }

    @Override
    protected void startInfoActivity(Bundle extras, int requestCode) {
        startActivity(CategoryInfoActivity.class, extras, requestCode);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_list_categories;
    }

    @Override
    protected CategoryActivityProvider createProvider() {
        return CategoryBaseDao.getInstance(this);
    }

    @Override
    protected CategoryActivityDao createDao() {
        return new CategoryActivityDao(this);
    }

    @Override
    public void refreshItems() {
        provider.loadCategoriesOfType(mCategoryType);
    }

    @Override
    public void onEvent(Event<Category> event) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null == data) return;

        switch (requestCode) {
            case CategoryInfoActivity.ADD_ITEM_REQUEST_CODE:
                if (RESULT_OK == resultCode) {
                    refreshItems();
                }
                break;
        }
    }
}
