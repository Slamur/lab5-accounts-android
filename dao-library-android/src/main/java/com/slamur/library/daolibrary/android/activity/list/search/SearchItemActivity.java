package com.slamur.library.daolibrary.android.activity.list.search;

import com.slamur.library.daolibrary.android.activity.list.ListItemActivity;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;

public interface SearchItemActivity<
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>
        >
        extends ListItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    void searchItems();
}
