package com.slamur.library.daolibrary.base.event;

import com.slamur.library.daolibrary.base.Item;

public interface Notifier <
            ItemType extends Item
        >  {

    void addListener(Listener<ItemType> listener);

    void removeListener(Listener<ItemType> listener);
}
