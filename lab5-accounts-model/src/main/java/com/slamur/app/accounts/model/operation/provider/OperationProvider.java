package com.slamur.app.accounts.model.operation.provider;

import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.base.provider.collection.ListItemProvider;

public interface OperationProvider extends ListItemProvider<Operation> {

    void loadOperationsWithSubstring(String substring);
}
