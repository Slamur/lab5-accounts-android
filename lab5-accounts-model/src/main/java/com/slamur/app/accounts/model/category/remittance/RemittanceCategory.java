package com.slamur.app.accounts.model.category.remittance;

import com.slamur.app.accounts.model.category.Category;

public class RemittanceCategory extends Category {

    private static final RemittanceCategory instance;

    static {
        instance = new RemittanceCategory(-1, "Remittance fictive category");
    }

    public static RemittanceCategory getInstance() {
        return instance;
    }

    private RemittanceCategory(long id, String name) {
        super(id, name);
    }
}
