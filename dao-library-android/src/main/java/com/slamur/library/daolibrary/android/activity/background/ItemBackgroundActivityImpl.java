package com.slamur.library.daolibrary.android.activity.background;

import android.os.Bundle;

import com.slamur.library.daolibrary.android.activity.ItemActivityImpl;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.background.ItemBackgroundProvider;
import com.slamur.library.daolibrary.base.Item;

public abstract class ItemBackgroundActivityImpl<
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemBackgroundProvider<ItemType, ?>>
extends ItemActivityImpl<ItemType, ItemActivityDaoType, ItemActivityProviderType>
implements ItemBackgroundActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
