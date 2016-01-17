package com.slamur.app.accounts.model.operation.dao;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.ExpenseCategory;
import com.slamur.app.accounts.model.category.IncomeCategory;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

import java.util.Date;
import java.util.List;

public interface OperationDao extends ListDao<Operation> {

    Operation addIncomeOperation(Date date, IncomeCategory category, double value, Account target, String description);
    Operation addExpenseOperation(Date date, ExpenseCategory category, double value, Account source, String description);
    Operation addRemittanceOperation(Date date, double value, Account source, Account target, String description);

    List<Operation> filterBySubstring(String substring);
//    List<Operation> filterByAccount(long accountId);

    void updateOperationAccounts(Account oldAccount, Account newAccount);

    void removeOperation(long id);
}
