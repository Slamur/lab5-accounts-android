package com.slamur.app.accounts.android.activity.operation;

import android.content.Intent;
import android.os.Bundle;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.dao.operation.OperationActivityDao;
import com.slamur.app.accounts.android.dao.operation.OperationBaseDao;
import com.slamur.app.accounts.android.list_view.operation.OperationListAdapter;
import com.slamur.app.accounts.android.provider.operation.OperationActivityProvider;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.android.activity.list.search.SearchItemActivityImpl;
import com.slamur.library.daolibrary.base.event.Event;

public class OperationListActivity extends SearchItemActivityImpl<Operation, OperationActivityDao, OperationActivityProvider, OperationListAdapter>
implements OperationActivity {

    @Override
    protected void onSearchActivityCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void searchItemsByString(String searchString) {
        provider.loadOperationsWithSubstring(searchString);
    }

    @Override
    protected OperationListAdapter createListAdapter() {
        return new OperationListAdapter(this);
    }

    @Override
    protected int getListViewId() {
        return R.id.activity_list_operations_list_view;
    }

    @Override
    protected int getAddItemViewId() {
        return R.id.activity_list_operations_add_button;
    }

    @Override
    protected int getRefreshItemsViewId() {
        return R.id.activity_list_operations_refresh_button;
    }

    @Override
    protected void prepareShowInfoBundle(Bundle extras, int index) {

    }

    @Override
    protected void prepareAddItemBundle(Bundle extras) {

    }

    @Override
    protected void startInfoActivity(Bundle extras, int requestCode) {
        startActivity(OperationInfoActivity.class, extras, requestCode);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_list_operations;
    }

    @Override
    protected OperationActivityProvider createProvider() {
        return OperationBaseDao.getInstance(this);
    }

    @Override
    protected OperationActivityDao createDao() {
        return new OperationActivityDao(this);
    }

    @Override
    public void refreshItems() {
        provider.loadItems();
    }

    @Override
    public void onEvent(Event<Operation> event) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null == data) return;

        switch (requestCode) {
            case OperationInfoActivity.ADD_ITEM_REQUEST_CODE:
                if (RESULT_OK == resultCode) {
                    refreshItems();
                }
                break;
        }
    }
}
