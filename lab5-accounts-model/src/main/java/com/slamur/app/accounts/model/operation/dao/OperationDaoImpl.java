package com.slamur.app.accounts.model.operation.dao;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.category.expense.ExpenseCategory;
import com.slamur.app.accounts.model.category.income.IncomeCategory;
import com.slamur.app.accounts.model.category.remittance.RemittanceCategory;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.base.dao.list.ListDaoImpl;
import com.slamur.library.daolibrary.base.function.ItemPredicate;

import java.util.Date;
import java.util.List;

public class OperationDaoImpl extends ListDaoImpl<Operation>
implements OperationDao {

    private long nextOperationId;

    public OperationDaoImpl() {
        super();

        nextOperationId = 0;
    }

    protected Operation createOperation(Date date, OperationType type, Category category, double value, Account source, Account target, String description) {
        Operation operation = new Operation(nextOperationId, date, type, category, value, source, target, description);
        ++nextOperationId;

        return operation;
    }

    protected Operation addOperation(Date date, OperationType type, Category category, double value, Account source, Account target, String description) {
        Operation operation = createOperation(date, type, category, value, source, target, description);
        super.addItem(operation);

        return operation;
    }

    @Override
    public Operation addIncomeOperation(Date date, IncomeCategory category, double value, Account target, String description) {
        return addOperation(date, OperationType.INCOME, category, value, IncomeCategory.INCOME_SOURCE, target, description);
    }

    @Override
    public Operation addExpenseOperation(Date date, ExpenseCategory category, double value, Account source, String description) {
        return addOperation(date, OperationType.EXPENSE, category, value, source, ExpenseCategory.EXPENSE_TARGET, description);
    }

    @Override
    public Operation addRemittanceOperation(Date date, double value, Account source, Account target, String description) {
        return addOperation(date, OperationType.REMITTANCE, RemittanceCategory.getInstance(), value, source, target, description);
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

//    @Override
//    public List<Operation> filterByAccount(final long accountId) {
//        return filterItems(new ItemPredicate<Operation>() {
//            @Override
//            public boolean check(Operation operation) {
//                return operation.getSource().getId() == accountId
//                        || operation.getTarget().getId() == accountId;
//            }
//        });
//    }

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
                        operation.getId(), operation.getDate(), operation.getType(), operation.getCategory(),
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
        operation.decline();
    }

    @Override
    protected void setItemInfo(Operation operation, Object... itemInfo) {
        operation.accept();
    }
}
