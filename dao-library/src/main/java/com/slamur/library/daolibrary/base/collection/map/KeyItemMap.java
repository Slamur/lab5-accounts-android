package com.slamur.library.daolibrary.base.collection.map;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.collection.ItemCollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class KeyItemMap <KeyType, ItemType extends Item>
extends LinkedHashMap<KeyType, ItemType>
implements ItemCollection<ItemType> {

    @Override
    public List<ItemType> toList() {
        List<ItemType> items = new ArrayList<>();
        for (ItemType item : values()) {
            items.add(item);
        }

        return items;
    }

    @Override
    public Iterator<ItemType> iterator() {
        return toList().iterator();
    }
}
