package com.slamur.library.daolibrary.base.provider.event;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProvider;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;

public class ItemLoadEvent <ItemType extends Item>
        extends ItemProviderEvent<ItemType, ItemType> {

    public ItemLoadEvent(ItemProvider<ItemType> itemProvider, ItemType item) {
        super(itemProvider, Action.LOAD_ITEM, item);
    }

    public ItemType getItem() {
        return eventObject;
    }
}
