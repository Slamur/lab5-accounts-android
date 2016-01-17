package com.slamur.app.accounts.model.category;

import com.slamur.library.daolibrary.base.Item;

public abstract class Category extends Item {

    private final String name, description;

    protected Category(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }
}
