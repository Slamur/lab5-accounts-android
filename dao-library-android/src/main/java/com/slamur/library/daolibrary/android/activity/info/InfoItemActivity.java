package com.slamur.library.daolibrary.android.activity.info;

import com.slamur.library.daolibrary.android.activity.ItemActivity;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;

public interface InfoItemActivity <
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>
        >
        extends ItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    String ACTION_TYPE_EXTRA = "Info activity action type extra";
    String ITEM_ID_EXTRA = "Info activity item id extra";
    String FINISH_AFTER_ADD_EXTRA = "Info activity finish after add extra";

    int NO_ACTION = -1,
        ADD_ITEM_ACTION = NO_ACTION + 1,
        SHOW_ITEM_ACTION = ADD_ITEM_ACTION + 1;

    int SHOW_ITEM_REQUEST_CODE = 0,
            ADD_ITEM_REQUEST_CODE = SHOW_ITEM_REQUEST_CODE + 1;
}
