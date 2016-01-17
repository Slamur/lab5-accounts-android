package com.slamur.library.daolibrary.base.provider.event;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProvider;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;

import java.util.List;

public class ItemsLoadEvent<ItemType extends Item>
        extends ItemProviderEvent<ItemType, List<ItemType>> {

    public ItemsLoadEvent(ItemProvider<ItemType> itemProvider, List<ItemType> items) {
        super(itemProvider, Action.LOAD_ITEMS, items);
    }

    public List<ItemType> getItems() {
        return eventObject;
    }
}
