package com.slamur.library.daolibrary.base.provider;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.NotifierImpl;

public abstract class ItemProviderImpl<
        ItemType extends Item
//        ItemProviderType extends ItemProviderImpl<ItemType, ItemProviderType>
        >
        extends NotifierImpl<
                            ItemType
                //            ItemProviderType,
//                            ItemProviderEvent<ItemType, ?>
                        > {
}
