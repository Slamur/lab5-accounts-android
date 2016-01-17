package com.slamur.library.daolibrary.base.dao.map;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.Dao;

public interface MapDao <KeyType, ItemType extends Item>
    extends Dao <ItemType> {

    ItemType getItem(KeyType key);
}
