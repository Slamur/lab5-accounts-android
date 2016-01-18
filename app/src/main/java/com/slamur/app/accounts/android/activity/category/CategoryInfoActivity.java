package com.slamur.app.accounts.android.activity.category;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.EditText;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.dao.category.CategoryActivityDao;
import com.slamur.app.accounts.android.dao.category.CategoryBaseDao;
import com.slamur.app.accounts.android.provider.category.CategoryActivityProvider;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.activity.info.InfoItemActivityImpl;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;

import java.util.Locale;

public class CategoryInfoActivity extends InfoItemActivityImpl<Category, CategoryActivityDao, CategoryActivityProvider>
implements CategoryActivity {

    protected OperationType mCategoryType;
    protected EditText mNameView;
    protected EditText mDescriptionView;

    @StringRes
    protected int getAddCategoryButtonTextId() {
        return R.string.activity_info_category_add_button_text;
    }

    @Override
    protected void onInfoActivityCreate(Bundle savedInstanceState, int actionType) {
        this.mCategoryType = OperationType.valueOf(
                getIntent().getStringExtra(CATEGORY_TYPE_EXTRA)
        );

        if (null == mCategoryType) {
            setResult(RESULT_CANCELED);
            finish();
        }

        String categoryTypeName = (OperationType.INCOME == mCategoryType ? INCOME_TYPE_NAME : EXPENSE_TYPE_NAME);
        AndroidUtils.<Button> findViewById(this, getAddItemViewId()).setText(
                String.format(Locale.US,
                        getString(getAddCategoryButtonTextId()),
                        categoryTypeName.toLowerCase()
                )
        );

        mNameView = AndroidUtils.findViewById(this, getNameViewId());
        mDescriptionView = AndroidUtils.findViewById(this, getDescriptionViewId());
    }

    @Override
    protected int getAddItemViewId() {
        return R.id.activity_info_category_add_button;
    }

    @IdRes
    protected int getNameViewId() {
        return R.id.activity_info_category_name_edit;
    }

    @IdRes
    protected int getDescriptionViewId() {
        return R.id.activity_info_category_description_edit;
    }

    @Override
    protected void disableInputs() {
        mNameView.setEnabled(false);
        mDescriptionView.setEnabled(false);
    }

    @Override
    protected Category createAndAddItem() {
        String name = mNameView.getText().toString();
        String description = mDescriptionView.getText().toString();

        switch (mCategoryType) {
            case INCOME:
                return dao.addIncomeCategory(name, description);
            case EXPENSE:
                return dao.addExpenseCategory(name, description);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_info_category;
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
    public void onEvent(Event<Category> event) {
        if (event instanceof ItemLoadEvent) {
            ItemLoadEvent<Category> itemLoadEvent = event.toType();

            Category category = itemLoadEvent.getItem();

            mItemId = category.getId();

            mNameView.setText(category.getName());
            mDescriptionView.setText(category.getDescription());
        }
    }
}
