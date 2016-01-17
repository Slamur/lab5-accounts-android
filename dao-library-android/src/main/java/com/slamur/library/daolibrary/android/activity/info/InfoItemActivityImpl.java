package com.slamur.library.daolibrary.android.activity.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;

import com.slamur.library.daolibrary.android.activity.ItemActivityImpl;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;

public abstract class InfoItemActivityImpl<
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>
        >
        extends ItemActivityImpl<ItemType, ItemActivityDaoType, ItemActivityProviderType>
implements InfoItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    protected abstract void onInfoActivityCreate(Bundle savedInstanceState, int actionType);

    protected long mItemId;
    protected boolean mFinishAfterAdd;

    @Override
    protected void onItemActivityCreate(Bundle savedInstanceState) {
        int actionType = getIntent().getIntExtra(ACTION_TYPE_EXTRA, NO_ACTION);

        this.mItemId = getIntent().getLongExtra(ITEM_ID_EXTRA, -1);
        this.mFinishAfterAdd = getIntent().getBooleanExtra(FINISH_AFTER_ADD_EXTRA, false);

        if (-1 == mItemId && SHOW_ITEM_ACTION == actionType) {
            actionType = ADD_ITEM_ACTION;
        }

        onInfoActivityCreate(savedInstanceState, actionType);

        if (ADD_ITEM_ACTION == actionType) {
            setActivityInAddMode();
            this.provider.loadItems();
        } else if (SHOW_ITEM_ACTION == actionType) {
            setActivityInShowMode();
            this.provider.loadItem(mItemId);
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @IdRes
    protected abstract int getAddItemViewId();

    protected abstract void disableInputs();

    protected abstract ItemType createAndAddItem();

    protected void setActivityInAddMode() {
        this.provider.removeListener(this);

        View addItemView = findViewById(getAddItemViewId());

        addItemView.setVisibility(View.VISIBLE);
        addItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    protected void setActivityInShowMode() {
        this.provider.addListener(this);

        disableInputs();
        findViewById(getAddItemViewId()).setVisibility(View.INVISIBLE);
    }

    protected Intent createFinishAfterAddIntent(ItemType item) {
        return new Intent();
    }

    public void addItem(){
        ItemType item = createAndAddItem();

        if (mFinishAfterAdd) {
            Intent intent = createFinishAfterAddIntent(item);
            setResult(RESULT_OK, intent);

            finish();
        } else {
            this.mItemId = item.getId();
            setActivityInShowMode();
        }
    }
}
