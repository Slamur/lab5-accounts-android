package com.slamur.app.accounts.android.dao.account;

import android.content.Context;

import com.slamur.app.accounts.android.dao.operation.OperationBaseDao;
import com.slamur.app.accounts.android.provider.account.AccountActivityProvider;
import com.slamur.app.accounts.android.settings.AccountsSettingsUtils;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.account.dao.AccountDaoImpl;
import com.slamur.app.accounts.model.account.dao.AccountRemoveEvent;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.event.Listener;
import com.slamur.library.daolibrary.base.gson.GsonUtils;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemParameterLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;

import java.util.List;

public class AccountBaseDao extends AccountDaoImpl
implements AccountActivityProvider {

    private static AccountBaseDao instance;

    public static AccountBaseDao getInstance(Context context) {
        if (null == instance) {
            instance = new AccountBaseDao(context);

            List<Account> accounts = AccountsSettingsUtils.getAccounts(context);
            instance.updateItems(accounts);

            instance.addListener(instance);

            OperationBaseDao.getInstance(context).addListener(new Listener<Operation>() {
                @Override
                public void onEvent(Event<Operation> event) {
                    if (event instanceof DaoEvent) {
                        DaoEvent<Operation> operationDaoEvent = event.toType();

                        Operation operation = operationDaoEvent.getItem();

                        if (operation.getType() != OperationType.INCOME) {
                            int sourceIndex = instance.getIndex(operation.getSource());
                            instance.updateItem(sourceIndex, operation.getSource());
                        }

                        if (operation.getType() != OperationType.EXPENSE) {
                            int targetIndex = instance.getIndex(operation.getTarget());
                            instance.updateItem(targetIndex, operation.getTarget());
                        }
                    }
                }
            });
        }

        return instance;
    }

    private final Context context;

    private AccountBaseDao(Context context) {
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
    public void onEvent(Event<Account> event) {
        if (this == event.getNotifier()) {
            Account[] accounts = getItems().toArray(new Account[0]);
            AccountsSettingsUtils.setStringValue(context, AccountsSettingsUtils.ACCOUNT_DAO_KEY, GsonUtils.toJson(accounts));
        } else if (event instanceof AccountRemoveEvent) {
            AccountRemoveEvent accountRemoveEvent = event.toType();
            this.removeAccount(accountRemoveEvent.getIndex(), accountRemoveEvent.getHeir());
        } else if (event instanceof ListEvent) {
            ListEvent<Account> listEvent = event.toType();

            Account account = listEvent.getItem();
            int index = listEvent.getIndex();

            if (listEvent.isAction(DaoEvent.Action.ADD)) {
//                this.addAccount(account.getName(), account.getBalance(), account.getDescription());
                this.addItem(account);
            } else if (listEvent.isAction(DaoEvent.Action.REMOVE)) {

            }
        }
    }
}
