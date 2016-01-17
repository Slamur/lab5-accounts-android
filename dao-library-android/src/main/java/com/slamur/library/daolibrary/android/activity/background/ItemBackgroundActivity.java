package com.slamur.library.daolibrary.android.activity.background;

import android.app.Activity;

import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.android.provider.background.ItemBackgroundProvider;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Listener;
import com.slamur.library.daolibrary.base.event.Notifier;

public interface ItemBackgroundActivity<
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemBackgroundProvider<ItemType, ?>
        >
        extends Notifier<ItemType>, Listener<ItemType> {

    ItemActivityDaoType getDao();

    ItemActivityProviderType getProvider();

    Activity toActivity();
}
