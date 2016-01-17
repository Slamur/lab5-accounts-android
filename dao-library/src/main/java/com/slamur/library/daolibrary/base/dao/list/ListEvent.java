package com.slamur.library.daolibrary.base.dao.list;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.DaoEvent;

public class ListEvent<ItemType extends Item> extends DaoEvent<ItemType> {

    protected final int index;

    public ListEvent(ListDao<ItemType> dao, DaoEvent.Action action, ItemType item, int index) {
        super(dao, action, item);

        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
