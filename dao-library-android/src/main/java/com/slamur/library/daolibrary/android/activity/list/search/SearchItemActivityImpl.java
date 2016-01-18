package com.slamur.library.daolibrary.android.activity.list.search;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;

import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.R;
import com.slamur.library.daolibrary.android.activity.list.ListItemActivityImpl;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapter;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.list.ListDao;

public abstract class SearchItemActivityImpl<
        ItemType extends Item,
        ItemActivityDaoType extends ListDao<ItemType> & ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>,
        ItemActivityListAdapterType extends ItemActivityDaoListAdapter<ItemType>
        >
        extends ListItemActivityImpl<ItemType, ItemActivityDaoType, ItemActivityProviderType, ItemActivityListAdapterType>
implements SearchItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    protected abstract void onSearchActivityCreate(Bundle savedInstanceState);

    protected EditText mSearchEdit;
    protected View mSearchButton;

    @IdRes
    protected int getSearchEditViewId() {
        return R.id.search_activity_widget_edit;
    }

    @IdRes
    protected int getSearchButtonViewId() {
        return R.id.search_activity_widget_button;
    }

    @Override
    protected void onListActivityCreate(Bundle savedInstanceState) {
        mSearchEdit = AndroidUtils.findViewById(this, getSearchEditViewId());

        mSearchButton = AndroidUtils.findViewById(this, getSearchButtonViewId());
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItems();
            }
        });

        onSearchActivityCreate(savedInstanceState);
    }

    protected abstract void searchItemsByString(String searchString);

    @Override
    public void searchItems() {
        String searchString = mSearchEdit.getText().toString();
        if (searchString.isEmpty()) {
            refreshItems();
        } else {
            searchItemsByString(searchString);
        }
    }
}
