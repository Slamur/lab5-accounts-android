package com.slamur.app.accounts.model.category.income;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;

public class IncomeCategory extends Category {

    public static final Account INCOME_SOURCE;

    static {
        INCOME_SOURCE = new Account(-1, "Income", 0, "Income category fictive account");
    }

    public IncomeCategory(long id, String name) {
        super(id, name);
    }
}
