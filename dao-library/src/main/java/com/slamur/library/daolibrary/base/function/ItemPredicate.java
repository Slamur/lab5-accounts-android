package com.slamur.library.daolibrary.base.function;

import com.slamur.library.daolibrary.base.Item;

public interface ItemPredicate<ItemType extends Item> {

    boolean check(ItemType item);
}
