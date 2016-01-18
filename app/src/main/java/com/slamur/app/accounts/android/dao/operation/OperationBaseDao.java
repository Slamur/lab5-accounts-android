package com.slamur.app.accounts.android.dao.operation;

import android.content.Context;

import com.slamur.app.accounts.android.dao.account.AccountBaseDao;
import com.slamur.app.accounts.android.provider.operation.OperationActivityProvider;
import com.slamur.app.accounts.android.settings.AccountsSettingsUtils;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.account.dao.AccountRemoveEvent;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.app.accounts.model.operation.dao.OperationDaoImpl;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.event.Listener;
import com.slamur.library.daolibrary.base.gson.GsonUtils;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemParameterLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;

import java.util.List;

public class OperationBaseDao extends OperationDaoImpl
implements OperationActivityProvider {

    private static OperationBaseDao instance;

    public static OperationBaseDao getInstance(Context context) {
        if (null == instance) {
            instance = new OperationBaseDao(context);

            List<Operation> operations = AccountsSettingsUtils.getOperations(context);
            instance.updateItems(operations);

            instance.addListener(instance);

            AccountBaseDao.getInstance(context).addListener(new Listener<Account>() {
                @Override
                public void onEvent(Event<Account> event) {
                    if (event instanceof AccountRemoveEvent) {
                        AccountRemoveEvent accountRemoveEvent = event.toType();

                        Account removedAccount = accountRemoveEvent.getItem();
                        Account heir = accountRemoveEvent.getHeir();

                        instance.updateOperationAccounts(removedAccount, heir);
                    }
                }
            });
        }

        return instance;
    }

    private final Context context;

    private OperationBaseDao(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void loadItem(int index) {
        notifyListeners(
                new ItemLoadEvent<>(this, getItem(index))
        );
    }

    @Override
    public void loadItems() {
        notifyListeners(
                new ItemsLoadEvent<>(this, getItems())
        );
    }

    @Override
    public void loadItem(long id) {
        notifyListeners(
                new ItemLoadEvent<>(this, getItem(id))
        );
    }

    @Override
    public void loadItemsCount() {
        notifyListeners(
                new ItemParameterLoadEvent<>(this, getItemCount(), ITEMS_COUNT_PARAMETER_NAME)
        );
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
    }

    @Override
    public void onEvent(Event<Operation> event) {
        if (this == event.getNotifier()) {
            Operation[] operations = getItems().toArray(new Operation[0]);
            AccountsSettingsUtils.setStringValue(context, AccountsSettingsUtils.OPERATION_DAO_KEY, GsonUtils.toJson(operations));
        } else if (event instanceof ListEvent) {
            ListEvent<Operation> listEvent = event.toType();

            Operation operation = listEvent.getItem();
            int index = listEvent.getIndex();

            if (listEvent.isAction(DaoEvent.Action.ADD)) {
                this.addItem(operation);
//                switch (operation.getType()) {
//                    case INCOME:
//                        this.addIncomeOperation(operation.getDate(), operation.getCategory(), operation.getValue(), operation.getTarget(), operation.getDescription());
//                        break;
//                    case EXPENSE:
//                        this.addExpenseOperation(operation.getDate(), operation.getCategory(), operation.getValue(), operation.getSource(), operation.getDescription());
//                        break;
//                    case REMITTANCE:
//                        this.addRemittanceOperation(operation.getDate(), operation.getValue(), operation.getSource(), operation.getTarget(), operation.getDescription());
//                        break;
//                }
            } else if (listEvent.isAction(DaoEvent.Action.REMOVE)) {
                this.removeItem(index);
            }
        }
    }

    @Override
    public void loadOperationsWithSubstring(String substring) {
        notifyListeners(
                new ItemsLoadEvent<>(
                        this, filterBySubstring(substring)
                )
        );
    }

    @Override
    protected void removeItemInfo(Operation operation) {
        super.removeItemInfo(operation);

        operation.decline();
    }

    @Override
    protected void setItemInfo(Operation operation, Object... itemInfo) {
        super.setItemInfo(operation, itemInfo);

        operation.accept();
    }
}
