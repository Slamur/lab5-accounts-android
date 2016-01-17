package com.slamur.library.daolibrary.android.activity;

import android.app.Activity;

import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Listener;
import com.slamur.library.daolibrary.base.event.Notifier;

public interface ItemActivity <
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>
        >
        extends Notifier<ItemType>, Listener<ItemType> {

    ItemActivityDaoType getDao();

    ItemActivityProviderType getProvider();

    Activity toActivity();
}
