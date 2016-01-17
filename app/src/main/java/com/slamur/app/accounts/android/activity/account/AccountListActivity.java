package com.slamur.app.accounts.android.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Menu;
import android.view.MenuItem;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.activity.category.CategoryActivity;
import com.slamur.app.accounts.android.activity.category.CategoryListActivity;
import com.slamur.app.accounts.android.dao.account.AccountActivityDao;
import com.slamur.app.accounts.android.dao.account.AccountBaseDao;
import com.slamur.app.accounts.android.list_view.account.AccountListAdapter;
import com.slamur.app.accounts.android.provider.account.AccountActivityProvider;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.android.activity.list.ListItemActivityImpl;
import com.slamur.library.daolibrary.base.event.Event;

public class AccountListActivity extends ListItemActivityImpl<Account, AccountActivityDao, AccountActivityProvider, AccountListAdapter>
implements AccountActivity {

    @Override
    protected void onListActivityCreate(Bundle savedInstanceState) {

    }

    @Override
    protected AccountListAdapter createListAdapter() {
        return new AccountListAdapter(this);
    }

    @Override
    protected int getListViewId() {
        return R.id.account_list_view;
    }

    @Override
    protected int getAddItemViewId() {
        return R.id.account_list_add_button;
    }

    @Override
    protected int getRefreshItemsViewId() {
        return R.id.account_list_refresh_button;
    }

    @Override
    protected void prepareShowInfoBundle(Bundle extras, int index) {

    }

    @Override
    protected void prepareAddItemBundle(Bundle extras) {

    }

    @Override
    protected void startInfoActivity(Bundle extras, int requestCode) {
        startActivity(AccountInfoActivity.class, extras, requestCode);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_list_accounts;
    }

    @Override
    protected AccountActivityProvider createProvider() {
        return AccountBaseDao.getInstance(this);
    }

    @Override
    protected AccountActivityDao createDao() {
        return new AccountActivityDao(this);
    }

    @Override
    public void refreshItems() {
        provider.loadItems();
    }

    @IdRes
    protected int getIncomeCategoriesMenuItemId() {
        return R.id.menu_activity_list_accounts_income_action;
    }

    @IdRes
    protected int getExpenseCategoriesMenuItemId() {
        return R.id.menu_activity_list_accounts_expense_action;
    }

    @IdRes
    protected int getOperationsMenuItemId() {
        return R.id.menu_activity_list_accounts_operation_action;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_list_accounts, menu);
        return true;
    }

    protected void startCategoriesListActivity(int categoryTypeExtra) {
        Bundle extras = new Bundle();
        extras.putInt(CategoryActivity.CATEGORY_TYPE_EXTRA, categoryTypeExtra);

        startActivity(CategoryListActivity.class, extras);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        if (getIncomeCategoriesMenuItemId() == item.getItemId()) {
            startCategoriesListActivity(CategoryActivity.INCOME_TYPE);
        }

        if (getExpenseCategoriesMenuItemId() == item.getItemId()) {
            startCategoriesListActivity(CategoryActivity.EXPENSE_TYPE);
        }

        if (getOperationsMenuItemId() == item.getItemId()) {
            throw new UnsupportedOperationException();
        }

        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onEvent(Event<Account> event) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null == data) return;

        switch (requestCode) {
            case AccountInfoActivity.ADD_ITEM_REQUEST_CODE:
                if (RESULT_OK == resultCode) {
                    refreshItems();
                }
                break;
        }
    }
}
