package com.slamur.library.daolibrary.android.dao;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.Dao;
import com.slamur.library.daolibrary.base.event.Listener;

public interface ItemActivityDao <ItemType extends Item> extends Dao<ItemType>, Listener<ItemType> {

}
