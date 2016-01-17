package com.slamur.app.accounts.model.category;

import com.slamur.app.accounts.model.account.Account;

public class ExpenseCategory extends Category {

    public static final Account EXPENSE_TARGET;

    static {
        EXPENSE_TARGET = new Account(-1, "Expense", 0, "Expense category fictive account");
    }

    public ExpenseCategory(long id, String name, String description) {
        super(id, name, description);
    }
}
