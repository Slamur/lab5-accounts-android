package com.slamur.library.daolibrary.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.collection.ItemCollection;
import com.slamur.library.daolibrary.base.dao.DaoImpl;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.event.Listener;
import com.slamur.library.daolibrary.base.event.NotifierImpl;

public abstract class ItemActivityImpl<
        ItemType extends Item,
        ItemActivityDaoType extends ItemActivityDao<ItemType>,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>>
extends Activity
implements ItemActivity<ItemType, ItemActivityDaoType, ItemActivityProviderType> {

    protected class ItemActivityNotifier
            extends NotifierImpl<
                        ItemType
//                                        ItemActivityListener<ItemType>,
//                        ItemActivityEvent<ItemType>
                    > {

        protected final ItemActivityEvent<ItemType> NEED_SAVE_STATE_EVENT =
                new ItemActivityEvent<>(ItemActivityImpl.this, ItemActivityEvent.Action.NEED_SAVE_STATE, null);

        protected ItemActivityNotifier() {

        }

        public void notifyAboutNeedSaveState() {
            super.notifyListeners(NEED_SAVE_STATE_EVENT);
        }

        public void notifyAboutResult(Intent intent) {
            super.notifyListeners(
                    new ItemActivityEvent<ItemType>(ItemActivityImpl.this, ItemActivityEvent.Action.ON_RESULT, intent)
            );
        }
    }

    protected ItemActivityNotifier itemActivityNotifier;
    protected ItemActivityProviderType provider;
    protected ItemActivityDaoType dao;

    @LayoutRes
    protected abstract int getActivityLayoutId();

    protected abstract void onItemActivityCreate(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());

        this.itemActivityNotifier = new ItemActivityNotifier();

        this.provider = createProvider();

        this.dao = createDao();

        onItemActivityCreate(savedInstanceState);

//        itemActivityNotifier.addListener(dao); // it was need in case dao.provider.saveItems(dao.items)
    }

    @Override
    public Activity toActivity() {
        return this;
    }

    protected abstract ItemActivityProviderType createProvider();

    @Override
    public ItemActivityProviderType getProvider() {
        return provider;
    }

    protected abstract ItemActivityDaoType createDao();

    @Override
    public ItemActivityDaoType getDao() {
        return dao;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        itemActivityNotifier.notifyAboutNeedSaveState();
    }

    @Override
    protected void onPause() {
        itemActivityNotifier.notifyAboutNeedSaveState();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        itemActivityNotifier.notifyAboutNeedSaveState();
        super.onDestroy();
    }

    @Override
    public void addListener(Listener<ItemType> listener) {
        this.itemActivityNotifier.addListener(listener);
    }

    @Override
    public void removeListener(Listener<ItemType> listener) {
        this.itemActivityNotifier.removeListener(listener);
    }

    protected <ActivityType extends Activity> void startActivity(Class<ActivityType> activityClass) {
        this.startActivity(activityClass, new Bundle());
    }

    protected <ActivityType extends Activity> void startActivity(Class<ActivityType> activityClass, Bundle extras) {
        this.startActivity(activityClass, extras, -1);
    }

    protected <ActivityType extends Activity> void startActivity(Class<ActivityType> activityClass, int requestCode) {
        this.startActivity(activityClass, new Bundle(), requestCode);
    }

    protected <ActivityType extends Activity> void startActivity(Class<ActivityType> activityClass, Bundle extras, int requestCode) {
        Intent activityIntent = new Intent(getBaseContext(), activityClass);
        activityIntent.putExtras(extras);
        startActivityForResult(activityIntent, requestCode);
    }
}
