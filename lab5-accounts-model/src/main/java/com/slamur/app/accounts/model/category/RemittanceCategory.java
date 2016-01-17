package com.slamur.app.accounts.model.category;

public class RemittanceCategory extends Category {

    private static final RemittanceCategory instance;

    static {
        instance = new RemittanceCategory(-1, "Remittance", "Remittance fictive category");
    }

    public static RemittanceCategory getInstance() {
        return instance;
    }

    private RemittanceCategory(long id, String name, String description) {
        super(id, name, description);
    }
}
