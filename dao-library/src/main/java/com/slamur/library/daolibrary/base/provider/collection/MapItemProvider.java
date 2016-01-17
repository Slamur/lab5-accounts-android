package com.slamur.library.daolibrary.base.provider.collection;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProvider;

public interface MapItemProvider <KeyType, ItemType extends Item>
    extends ItemProvider<ItemType> {

    void loadItem(KeyType key);

    void removeItem(KeyType key);
}
