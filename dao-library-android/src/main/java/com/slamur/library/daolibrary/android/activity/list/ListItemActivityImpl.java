package com.slamur.library.daolibrary.android.activity.list;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ListView;

import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.activity.ItemActivityImpl;
import com.slamur.library.daolibrary.android.activity.info.InfoItemActivity;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapter;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

public abstract class ListItemActivityImpl <
        ItemType extends Item,
        ItemActivityDaoType extends ListDao<ItemType> & ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>,
        ItemActivityListAdapterType extends ItemActivityDaoListAdapter<ItemType>
        >
        extends ItemActivityImpl<ItemType, ItemActivityDaoType, ItemActivityProviderType>
implements ListItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    protected abstract void onListActivityCreate(Bundle savedInstanceState);

    @Override
    protected void onItemActivityCreate(Bundle savedInstanceState) {
        initAdapter();

        View addItemView = AndroidUtils.findViewById(this, getAddItemViewId());
        addItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showAddItemActivity();
            }
        });

        View refreshItemsView = AndroidUtils.findViewById(this, getRefreshItemsViewId());
        refreshItemsView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                refreshItems();
            }
        });

        onListActivityCreate(savedInstanceState);

        refreshItems();
    }

    protected abstract ItemActivityListAdapterType createListAdapter();

    @IdRes
    protected abstract int getListViewId();

    public void initAdapter() {
        ItemActivityListAdapterType adapter = createListAdapter();

        ListView itemsListView = (ListView) findViewById(getListViewId());
        itemsListView.setAdapter(adapter);

        dao.addListener(adapter);
    }

    @IdRes
    protected abstract int getAddItemViewId();

    @IdRes
    protected abstract int getRefreshItemsViewId();

    protected abstract void prepareShowInfoBundle(Bundle extras, int index);

    protected abstract void prepareAddItemBundle(Bundle extras);

    protected abstract void startInfoActivity(Bundle extras, int action);

    @Override
    public void showInfoActivity(int index) {
        Bundle extras = new Bundle();

        extras.putInt(InfoItemActivity.ACTION_TYPE_EXTRA, InfoItemActivity.SHOW_ITEM_ACTION);
        extras.putLong(InfoItemActivity.ITEM_ID_EXTRA, dao.getItem(index).getId());

        prepareShowInfoBundle(extras, index);

        startInfoActivity(extras, InfoItemActivity.SHOW_ITEM_REQUEST_CODE);
    }

    @Override
    public void showAddItemActivity() {
        Bundle extras = new Bundle();

        extras.putInt(InfoItemActivity.ACTION_TYPE_EXTRA, InfoItemActivity.ADD_ITEM_ACTION);
        extras.putBoolean(InfoItemActivity.FINISH_AFTER_ADD_EXTRA, true);

        prepareAddItemBundle(extras);

        startInfoActivity(extras, InfoItemActivity.ADD_ITEM_REQUEST_CODE);
    }
}
