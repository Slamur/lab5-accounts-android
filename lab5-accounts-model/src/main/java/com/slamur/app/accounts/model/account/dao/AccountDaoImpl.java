package com.slamur.app.accounts.model.account.dao;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.base.dao.list.ListDaoImpl;
import com.slamur.library.daolibrary.base.function.ItemPredicate;

import java.util.List;

public class AccountDaoImpl extends ListDaoImpl<Account>
implements AccountDao {

    protected long nextAccountId;

    public AccountDaoImpl() {
        super();

        this.nextAccountId = 0;
    }


    @Override
    protected void removeItemInfo(Account item) {

    }

    @Override
    protected void setItemInfo(Account item, Object... itemInfo) {
        this.nextAccountId = Math.max(nextAccountId, item.getId() + 1);
    }

    protected Account createAccount(String name, double balance, String description) {
        Account account = new Account(nextAccountId, name, balance, description);
        ++nextAccountId;

        return account;
    }

    @Override
    public Account addAccount(String name, double balance, String description) {
        Account account = createAccount(name, balance, description);
        super.addItem(account);

        return account;
    }

    @Override
    public List<Account> getAccountsExcept(final Account account) {
        return filterItems(new ItemPredicate<Account>() {
            @Override
            public boolean check(Account item) {
                return !item.equals(account);
            }
        });
    }

    @Override
    public void removeAccount(int index, Account heir) {
        Account removedAccount = getItem(index);
        super.removeItem(index);

        notifyListeners(
                new AccountRemoveEvent(this, removedAccount, index, heir)
        );
    }

    @Override
    public Class<Account> getItemClass() {
        return Account.class;
    }
}
