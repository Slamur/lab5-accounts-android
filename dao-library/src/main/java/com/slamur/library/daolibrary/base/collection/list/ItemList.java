package com.slamur.library.daolibrary.base.collection.list;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.collection.ItemCollection;

import java.util.ArrayList;
import java.util.List;

public class ItemList<ItemType extends Item>
        extends ArrayList<ItemType>
        implements ItemCollection<ItemType> {

    @Override
    public List<ItemType> toList() {
        return this;
    }
}
