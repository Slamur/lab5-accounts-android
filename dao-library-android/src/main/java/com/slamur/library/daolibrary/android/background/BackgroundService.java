package com.slamur.library.daolibrary.android.background;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.google.gson.Gson;
import com.slamur.library.daolibrary.android.activity.background.ItemBackgroundActivity;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.android.provider.background.ItemBackgroundProvider;
import com.slamur.library.daolibrary.base.Item;

public abstract class BackgroundService<
        ItemType extends Item,
        ItemActivityProviderType extends ItemActivityProvider<ItemType>,
        ItemActivityType extends ItemBackgroundActivity<ItemType, ?, ?>
        >
        extends IntentService {

    public static final String ACTION_EXTRA = "Back service action extra";

    protected interface BackgroundCallback {

        void invoke(Bundle bundle);
    }

    public static final int
        NO_ACTION = -1;

    protected Gson gson;

    public BackgroundService() {
        this("back service");
    }

    public BackgroundService(String name) {
        super(name);

        this.gson = new Gson();
    }

    protected abstract ItemActivityProviderType createProvider(
            Context context, Intent intent, int action, BackgroundCallback callback
    );

    protected abstract void processAction(Intent intent, int action, ItemActivityProviderType provider);

    @Override
    protected void onHandleIntent(Intent intent) {
        final int action = intent.getIntExtra(ACTION_EXTRA, NO_ACTION);
        if (NO_ACTION == action) {
            return;
        }

        final PendingIntent pendingIntent = intent.getParcelableExtra(ItemBackgroundProvider.PENDING_INTENT_EXTRA);

        final ItemActivityProviderType provider = createProvider(getApplicationContext(), intent, action,
                new BackgroundCallback() {
            @Override
            public void invoke(Bundle bundle) {
                Intent backIntent = new Intent();
                backIntent.putExtras(bundle);

                try {
                    pendingIntent.send(
                            BackgroundService.this,
                            Activity.RESULT_OK,
                            backIntent
                    );
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        });

        processAction(intent, action, provider);
    }

    protected void showNotification(int iconResId, String contentTitle, String contentText, Class<ItemActivityType> activityClass) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(iconResId)
                        .setContentTitle(contentTitle)
                        .setAutoCancel(true)
                        .setContentText(contentText);

        Intent resultIntent = new Intent(this, activityClass);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(activityClass);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, mBuilder.build());
    }
}
