package com.slamur.app.accounts.model.account.dao;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

import java.util.List;

public interface AccountDao extends ListDao<Account> {

    Account addAccount(String name, double balance, String description);

    List<Account> getAccountsExcept(Account account);

    void removeAccount(int index, Account heir);
}
