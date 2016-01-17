package com.slamur.app.accounts.model.account.dao;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListDao;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;

public class AccountRemoveEvent extends ListEvent<Account> {

    private final Account heir;

    public AccountRemoveEvent(ListDao<Account> dao, Account account, int index, Account heir) {
        super(dao, DaoEvent.Action.REMOVE, account, index);

        this.heir = heir;
    }

    public Account getHeir() {
        return heir;
    }
}
