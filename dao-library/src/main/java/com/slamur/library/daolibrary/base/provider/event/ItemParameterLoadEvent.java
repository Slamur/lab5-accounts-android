package com.slamur.library.daolibrary.base.provider.event;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProvider;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;

public class ItemParameterLoadEvent<ItemType extends Item>
        extends ItemProviderEvent<ItemType, Object> {

    protected final String parameterName;

    public ItemParameterLoadEvent(ItemProvider<ItemType> itemProvider, Object parameter, String parameterName) {
        super(itemProvider, Action.LOAD_ITEM_PARAMETER, parameter);
        this.parameterName = parameterName;
    }

    public <ParameterType> ParameterType getParameter() {
        return (ParameterType) eventObject;
    }

    public String getParameterName() {
        return parameterName;
    }
}
