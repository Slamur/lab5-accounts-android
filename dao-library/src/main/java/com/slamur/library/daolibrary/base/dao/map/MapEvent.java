package com.slamur.library.daolibrary.base.dao.map;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.DaoEvent;

public class MapEvent<KeyType, ItemType extends Item> extends DaoEvent<ItemType> {

    protected final KeyType key;

    public MapEvent(MapDao<KeyType, ItemType> dao, DaoEvent.Action action, ItemType item, KeyType key) {
        super(dao, action, item);

        this.key = key;
    }

    public KeyType getKey() {
        return key;
    }
}
