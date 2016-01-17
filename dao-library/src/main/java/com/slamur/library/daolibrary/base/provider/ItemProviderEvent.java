package com.slamur.library.daolibrary.base.provider;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Event;

public abstract class ItemProviderEvent<ItemType extends Item, EventObjectType> extends Event<ItemType> {

    public enum Action implements Event.EventAction {
        LOAD_ITEM, LOAD_ITEMS, LOAD_ITEM_PARAMETER
    }

    protected final EventObjectType eventObject;

    protected ItemProviderEvent(ItemProvider<ItemType> itemProvider, ItemProviderEvent.Action action, EventObjectType eventObject) {
        super(itemProvider, action);
        this.eventObject = eventObject;
    }
}
