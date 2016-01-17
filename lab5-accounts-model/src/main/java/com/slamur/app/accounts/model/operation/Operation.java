package com.slamur.app.accounts.model.operation;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.library.daolibrary.base.Item;

import java.util.Date;

public class Operation extends Item {

    private final Date date;

    private final OperationType type;
    private final Category category;

    private final double value;

    private final Account source, target;

    private final String description;

    public Operation(long id, Date date, OperationType type, Category category, double value, Account source, Account target, String description) {
        super(id);
        this.date = date;
        this.type = type;
        this.category = category;
        this.value = value;
        this.source = source;
        this.target = target;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public OperationType getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }

    public Account getSource() {
        return source;
    }

    public Account getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }

    public void accept() {
        getSource().decreaseBalance(value);
        getTarget().increaseBalance(value);
    }

    public void decline() {
        getSource().increaseBalance(value);
        getTarget().decreaseBalance(value);
    }
}
