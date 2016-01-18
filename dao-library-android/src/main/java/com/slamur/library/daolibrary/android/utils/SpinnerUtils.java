package com.slamur.library.daolibrary.android.utils;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerUtils {

    public static <ValueType> ArrayAdapter<ValueType> createSimpleSpinnerArrayAdapter(Activity activity, ValueType[] values) {
        ArrayAdapter<ValueType> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    public static void selectItem(Spinner spinner, Object item) {
        for (int index = 0; index < spinner.getCount(); ++index) {
            if (spinner.getItemAtPosition(index).equals(item)) {
                spinner.setSelection(index);
            }
        }
    }
}
