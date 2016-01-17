package com.slamur.app.accounts.model.category;

import com.slamur.app.accounts.model.account.Account;

public class IncomeCategory extends Category {

    public static final Account INCOME_SOURCE;

    static {
        INCOME_SOURCE = new Account(-1, "Income", 0, "Income category fictive account");
    }

    public IncomeCategory(long id, String name, String description) {
        super(id, name, description);
    }
}
