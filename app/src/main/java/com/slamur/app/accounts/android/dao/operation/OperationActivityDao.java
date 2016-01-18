package com.slamur.app.accounts.android.dao.operation;

import com.slamur.app.accounts.android.activity.operation.OperationActivity;
import com.slamur.app.accounts.android.provider.operation.OperationActivityProvider;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.app.accounts.model.operation.dao.OperationDaoImpl;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;

public class OperationActivityDao extends OperationDaoImpl
implements ItemActivityDao<Operation> {

    public OperationActivityDao(OperationActivity activity) {
        super();

        OperationActivityProvider operationProvider = activity.getProvider();

        operationProvider.addListener(this);
        this.addListener(operationProvider);
    }

    @Override
    public void onEvent(Event<Operation> event) {
        if (event instanceof ItemProviderEvent) {
            if (event.isAction(ItemProviderEvent.Action.LOAD_ITEMS)) {
                ItemsLoadEvent<Operation> itemsLoadEvent = event.toType();
                updateItems(itemsLoadEvent.getItems());
            }
        } else if (event instanceof ListEvent) {
            if (event.isAction(DaoEvent.Action.UPDATE)) {
                ListEvent<Operation> operationListEvent = event.toType();
                this.updateItem(operationListEvent.getIndex(), operationListEvent.getItem());
            }
        }
    }
}
