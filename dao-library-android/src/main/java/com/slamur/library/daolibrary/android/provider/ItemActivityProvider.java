package com.slamur.library.daolibrary.android.provider;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Listener;
import com.slamur.library.daolibrary.base.provider.ItemProvider;

public interface ItemActivityProvider <ItemType extends Item>
        extends ItemProvider<ItemType>, Listener<ItemType> {

}
