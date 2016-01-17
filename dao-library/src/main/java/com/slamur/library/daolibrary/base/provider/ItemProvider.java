package com.slamur.library.daolibrary.base.provider;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Notifier;

import java.util.List;

public interface ItemProvider <ItemType extends Item>
        extends Notifier<
            ItemType
        > {

    String ITEMS_COUNT_PARAMETER_NAME = "itemsCount";

    void loadItems();

    void loadItem(long id);

    void loadItemsCount();
}
