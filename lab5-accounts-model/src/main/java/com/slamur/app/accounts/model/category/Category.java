package com.slamur.app.accounts.model.category;

import com.slamur.library.daolibrary.base.Item;

public abstract class Category extends Item {

    private final String name;

    protected Category(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
