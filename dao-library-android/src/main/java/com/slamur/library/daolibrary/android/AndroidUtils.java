package com.slamur.library.daolibrary.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidUtils {

    public static <ViewType> ViewType findViewById(View view, @IdRes int viewId) {
        return (ViewType) view.findViewById(viewId);
    }

    public static <ViewType> ViewType findViewById(Activity activity, @IdRes int viewId) {
        return (ViewType) activity.findViewById(viewId);
    }

    public static <ViewType> ViewType findViewById(Dialog dialog, @IdRes int viewId) {
        return (ViewType) dialog.findViewById(viewId);
    }

    public static void setText(Activity activity, int textViewId, String text) {
        AndroidUtils.<TextView> findViewById(activity, textViewId).setText(text);
    }

    public static void setText(View view, int textViewId, String text) {
        AndroidUtils.<TextView> findViewById(view, textViewId).setText(text);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
