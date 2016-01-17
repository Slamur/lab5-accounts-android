package com.slamur.library.daolibrary.base.provider.collection;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProvider;

public interface ListItemProvider <ItemType extends Item>
    extends ItemProvider<ItemType> {

    void loadItem(int index);

    void removeItem(int index);
}
