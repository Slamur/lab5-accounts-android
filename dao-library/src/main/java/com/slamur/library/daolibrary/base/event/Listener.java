package com.slamur.library.daolibrary.base.event;

import com.slamur.library.daolibrary.base.Item;

public interface Listener <
        ItemType extends Item
        > {

    void onEvent(Event<ItemType> event);
}
