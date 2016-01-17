package com.slamur.library.daolibrary.android.activity.list;

import com.slamur.library.daolibrary.android.activity.ItemActivity;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;

public interface ListItemActivity <
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>
        >
        extends ItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    void refreshItems();

    void showInfoActivity(int index);

    void showAddItemActivity();
}
