package com.slamur.library.daolibrary.android.provider.background;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.slamur.library.daolibrary.android.activity.background.ItemBackgroundActivity;
import com.slamur.library.daolibrary.android.background.BackgroundService;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.rest.ItemGsonProvider;

public abstract class ItemBackgroundProvider<ItemType extends Item, ItemBackgroundServiceType extends BackgroundService<ItemType, ?, ?>>
        extends ItemGsonProvider<ItemType>
        implements ItemActivityProvider<ItemType> {

    public static final String PENDING_INTENT_EXTRA = "Pending intent extra";
    public static final int BACKGROUND_REQUEST_CODE = 1;

    protected final Activity activity;

    public ItemBackgroundProvider(ItemBackgroundActivity<ItemType, ?, ?> activity) {
        this.activity = activity.toActivity();
    }

    protected Bundle createBundle(int action) {
        Bundle bundle = new Bundle();
        bundle.putInt(BackgroundService.ACTION_EXTRA, action);

        return bundle;
    }

    protected abstract Class<ItemBackgroundServiceType> getBackgroundServiceClass();

    protected void startService(Bundle extras) {
        PendingIntent pendingIntent = activity.createPendingResult(BACKGROUND_REQUEST_CODE, new Intent(), 0);
        Intent intent = new Intent(activity, getBackgroundServiceClass())
                .putExtra(PENDING_INTENT_EXTRA, pendingIntent)
                .putExtras(extras);
        // стартуем сервис
        activity.startService(intent);
    }


}
