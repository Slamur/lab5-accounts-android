package com.slamur.library.daolibrary.base.provider.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProviderImpl;

public abstract class ItemGsonProvider <ItemType extends Item>
        extends ItemProviderImpl<ItemType>{

    protected final Gson gson = new Gson();

    protected ItemGsonProvider() {

    }

    protected <ValueType> ValueType fromJson(String valueJsonString, Class<ValueType> valueClass) {
        return gson.fromJson(valueJsonString, valueClass);
    }

//    protected <ValueType> ValueType fromJson(JsonElement jsonElement, Class<ValueType> valueClass) {
//        return gson.fromJson(jsonElement, valueClass);
//    }

    protected <ValueType> String toJson(ValueType value) {
        return gson.toJson(value);
    }
}
