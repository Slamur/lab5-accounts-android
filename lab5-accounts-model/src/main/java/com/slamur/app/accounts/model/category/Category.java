package com.slamur.app.accounts.model.category;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.base.Item;

public class Category extends Item {

    public static final Category REMITTANCE_CATEGORY = new Category(
            -1, "Remittance", "Remittance fictive category", OperationType.REMITTANCE
    );

    public static final Account INCOME_SOURCE = new Account(
            -1, "Income source", 0, "Fictive income source"
    );

    public static final Account EXPENSE_TARGET = new Account(
            -1, "Expense target", 0, "Fictive expense target"
    );

    private final String name, description;
    private final OperationType type;

    public Category(long id, String name, String description, OperationType type) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

    public OperationType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
