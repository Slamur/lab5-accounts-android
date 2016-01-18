package com.slamur.library.daolibrary.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Слава on 18.01.2016.
 */
public class DatetimeUtils {

    public interface DatetimePickerCallback {

        void invoke(Date date);
    }


    @LayoutRes
    public static int getDatetimePickerDialogId() {
        return R.layout.dialog_datetime_picker;
    }

    @IdRes
    public static int getDatePickerId() {
        return R.id.dialog_datetime_picker_datePicker;
    }

    @IdRes
    public static int getTimePickerId() {
        return R.id.dialog_datetime_picker_timePicker;
    }

    public static View.OnClickListener getDatetimePickerClickListener(
            final Activity activity, final String title, final DatetimePickerCallback pickerCallback) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = activity.getLayoutInflater()
                        .inflate(getDatetimePickerDialogId(), null);

                AlertDialog alertDialog = new AlertDialog.Builder(activity)
                        .setTitle(title)
                        .setView(dialogView)
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Dialog thisDialog = Dialog.class.cast(dialog);

                                DatePicker date = AndroidUtils.findViewById(thisDialog, getDatePickerId());
                                TimePicker time = AndroidUtils.findViewById(thisDialog, getTimePickerId());

                                Calendar totalDate = Calendar.getInstance();
                                totalDate.set(
                                        date.getYear(), date.getMonth(), date.getDayOfMonth(),
                                        time.getHour(), time.getMinute()
                                );

                                pickerCallback.invoke(totalDate.getTime());
                            }
                        })
                        .create();

                alertDialog.show();
            }
        };
    }
}
