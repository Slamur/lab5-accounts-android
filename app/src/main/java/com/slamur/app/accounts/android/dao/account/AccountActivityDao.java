package com.slamur.app.accounts.android.dao.account;

import com.slamur.app.accounts.android.activity.account.AccountActivity;
import com.slamur.app.accounts.android.provider.account.AccountActivityProvider;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.account.dao.AccountDaoImpl;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;

public class AccountActivityDao extends AccountDaoImpl
implements ItemActivityDao<Account> {

    public AccountActivityDao(AccountActivity activity) {
        super();

        AccountActivityProvider activityProvider = activity.getProvider();

        activityProvider.addListener(this);
        this.addListener(activityProvider);
    }

    @Override
    public void onEvent(Event<Account> event) {
        if (event instanceof ItemProviderEvent) {
            if (event.isAction(ItemProviderEvent.Action.LOAD_ITEMS)) {
                ItemsLoadEvent<Account> itemsLoadEvent = event.toType();
                updateItems(itemsLoadEvent.getItems());
            }
        } else if (event instanceof ListEvent) {
            if (event.isAction(DaoEvent.Action.UPDATE)) {
                ListEvent<Account> accountListEvent = event.toType();
                this.updateItem(accountListEvent.getIndex(), accountListEvent.getItem());
            }
        }
    }
}
