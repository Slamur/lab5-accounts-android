package com.slamur.library.daolibrary.base.dao;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Event;

public abstract class DaoEvent<ItemType extends Item> extends Event<ItemType> {

    public enum Action implements Event.EventAction {
        ADD, UPDATE, REMOVE
    }

    protected final ItemType item;

    protected DaoEvent(Dao<ItemType> dao, DaoEvent.Action action, ItemType item) {
        super(dao, action);
        this.item = item;
    }

    public ItemType getItem() {
        return item;
    }
}
