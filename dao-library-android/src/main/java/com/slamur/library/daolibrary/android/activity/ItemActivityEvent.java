package com.slamur.library.daolibrary.android.activity;

import android.content.Intent;
import android.os.Bundle;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Event;

public class ItemActivityEvent <ItemType extends Item> extends Event<ItemType> {

    public enum Action implements Event.EventAction {
        ON_RESULT, NEED_SAVE_STATE
    }

    protected final Intent intent;

    public ItemActivityEvent(ItemActivity itemActivity, Action action, Intent intent) {
        super(itemActivity, action);

        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }
}
