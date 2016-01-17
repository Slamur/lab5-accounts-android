package com.slamur.library.daolibrary.android.list_view;

import android.widget.ListAdapter;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.event.Listener;

public interface ItemActivityDaoListAdapter<ItemType extends Item>
extends ListAdapter, Listener<ItemType> {

}
