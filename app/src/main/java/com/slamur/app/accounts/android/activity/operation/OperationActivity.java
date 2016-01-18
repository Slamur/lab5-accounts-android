package com.slamur.app.accounts.android.activity.operation;

import com.slamur.app.accounts.android.dao.operation.OperationActivityDao;
import com.slamur.app.accounts.android.provider.operation.OperationActivityProvider;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.android.activity.ItemActivity;

public interface OperationActivity extends ItemActivity<Operation, OperationActivityDao, OperationActivityProvider> {

}
