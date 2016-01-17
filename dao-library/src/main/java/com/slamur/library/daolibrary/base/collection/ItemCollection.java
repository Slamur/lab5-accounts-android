package com.slamur.library.daolibrary.base.collection;

import com.slamur.library.daolibrary.base.Item;

import java.util.List;

public interface ItemCollection<ItemType extends Item>
extends Iterable<ItemType> {

    int size();
    List<ItemType> toList();
}
