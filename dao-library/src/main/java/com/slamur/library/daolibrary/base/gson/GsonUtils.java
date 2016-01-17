package com.slamur.library.daolibrary.base.gson;

import com.google.gson.Gson;

public class GsonUtils {

    public static <ValueType> ValueType fromJson(String valueJsonString, Class<ValueType> valueClass) {
        return new Gson().fromJson(valueJsonString, valueClass);
    }

    public static <ValueType> String toJson(ValueType value) {
        return new Gson().toJson(value);
    }
}
