package com.slamur.library.daolibrary.base.dao.list;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.Dao;

public interface ListDao <ItemType extends Item>
    extends Dao<ItemType> {

    ItemType getItem(int index);
}
