package com.slamur.library.daolibrary.android.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class BackgroundBroadcastReceiver extends BroadcastReceiver {

    public static final String BACKGROUND_RECEIVER_ACTION = "com.slamur.library.daolibrary.android.background.BACKGROUND_RECEIVER_ACTION";

    public static final String ACTION_EXTRA = "Action extra";

    public static final int
            NO_ACTION = -1,
            APP_ACTIVATED_ACTION = NO_ACTION + 1,
            APP_DEACTIVATED_ACTION = APP_ACTIVATED_ACTION + 1,
            USER_ACTION_START = APP_DEACTIVATED_ACTION + 1;

    protected boolean isAppRunning = false;

    protected abstract void processIntent(Context context, Intent intent, int intentAction);

    @Override
    public void onReceive(Context context, Intent intent) {
        int intentAction = intent.getIntExtra(ACTION_EXTRA, NO_ACTION);

        if (APP_ACTIVATED_ACTION == intentAction) {
            isAppRunning = true;
        } else if (APP_DEACTIVATED_ACTION == intentAction) {
            isAppRunning = false;
        }

        processIntent(context, intent, intentAction);
//
//        Intent intentToSelf = new Intent(context, MeetingsBroadcastReceiver.class);
//        intentToSelf.putExtra("purpose", "alarmToSelf");
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentToSelf, 0);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.cancel(pendingIntent);
//        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + interval, pendingIntent);
//
//        if (!isActivityRunning(context)) {
//            Intent downloadIntent = new Intent(context, MeetingService.class);
//            downloadIntent.putExtra("purpose", MeetingService.BACKGROUND_DOWNLOAD);
//            context.startService(downloadIntent);
//        }
    }
//
//    public boolean isActivityRunning(Context context) {
//
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> activitys = activityManager.getRunningTasks(Integer.MAX_VALUE);
//        boolean appIsOnForeground = false;
//
//
//        if (activitys.get(0).baseActivity.toString().equalsIgnoreCase("ComponentInfo{tavi.tiki.niki.meetingsapplication/tavi.tiki.niki.meetingsapplication.MeetingsActivity}")) {
//            appIsOnForeground = true;
//
//        }
//        return appIsOnForeground;
//    }
}
