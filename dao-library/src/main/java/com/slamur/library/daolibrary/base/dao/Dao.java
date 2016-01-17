package com.slamur.library.daolibrary.base.dao;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Notifier;

import java.util.List;

public interface Dao <
            ItemType extends Item
        >
        extends Notifier<ItemType> {

    Class<ItemType> getItemClass();

    List<ItemType> getItems();

    int getItemCount();

    ItemType getItem(long id);
}
