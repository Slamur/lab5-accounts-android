package com.slamur.app.accounts.model.operation.dao;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.base.dao.list.ListDaoImpl;
import com.slamur.library.daolibrary.base.function.ItemPredicate;

import java.util.Date;
import java.util.List;

public class OperationDaoImpl extends ListDaoImpl<Operation>
implements OperationDao {

    public OperationDaoImpl() {
        super();
    }

    protected Operation createOperation(Date date, Category category, double value, Account source, Account target, String description) {
        return new Operation(generateNextItemId(), date, category, value, source, target, description);
    }

    protected Operation addOperation(Date date, Category category, double value, Account source, Account target, String description) {
        Operation operation = createOperation(date, category, value, source, target, description);
        super.addItem(operation);

        return operation;
    }

    @Override
    public Operation addIncomeOperation(Date date, Category incomeCategory, double value, Account target, String description) {
        return addOperation(date, incomeCategory, value, Category.INCOME_SOURCE, target, description);
    }

    @Override
    public Operation addExpenseOperation(Date date, Category expenseCategory, double value, Account source, String description) {
        return addOperation(date, expenseCategory, value, source, Category.EXPENSE_TARGET, description);
    }

    @Override
    public Operation addRemittanceOperation(Date date, double value, Account source, Account target, String description) {
        return addOperation(date, Category.REMITTANCE_CATEGORY, value, source, target, description);
    }

    @Override
    public List<Operation> filterBySubstring(final String substring) {
        return filterItems(new ItemPredicate<Operation>() {
            @Override
            public boolean check(Operation operation) {
                return operation.getDescription().contains(substring);
            }
        });
    }

    @Override
    public List<Operation> filterByAccount(final Account account) {
        return filterItems(new ItemPredicate<Operation>() {
            @Override
            public boolean check(Operation item) {
                return item.getSource().equals(account) || item.getTarget().equals(account);
            }
        });
    }

    @Override
    public void updateOperationAccounts(Account oldAccount, Account newAccount) {
        if (oldAccount.equals(newAccount)) return;

        for (int index = 0, size = getItemCount(); index < size; ++index) {
            Operation operation = getItem(index);

            final Account oldSource = operation.getSource();
            final Account oldTarget = operation.getTarget();

            final boolean isOldSource = (oldSource.equals(oldAccount));
            final boolean isOldTarget = (oldTarget.equals(oldAccount));

            if (isOldSource || isOldTarget) {
                final Account newSource = (isOldSource ? newAccount : oldSource);
                final Account newTarget = (isOldTarget ? newAccount : oldTarget);

                Operation updatedOperation = new Operation(
                        operation.getId(), operation.getDate(), operation.getCategory(),
                        operation.getValue(), newSource, newTarget, operation.getDescription()
                );

                super.updateItem(index, updatedOperation);
            }
        }
    }

    @Override
    public void removeOperation(long id) {
        Operation operation = getItem(id);
        removeItem(operation);
    }

    @Override
    public Class<Operation> getItemClass() {
        return Operation.class;
    }

    @Override
    protected void removeItemInfo(Operation operation) {

    }

    @Override
    protected void setItemInfo(Operation operation, Object... itemInfo) {
        nextItemId = Math.max(nextItemId, operation.getId() + 1);
    }
}
