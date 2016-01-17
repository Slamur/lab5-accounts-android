package com.slamur.library.daolibrary.base.rest.map;

import com.slamur.library.daolibrary.base.rest.ItemRestService;

public interface MapItemRestService extends ItemRestService, MapItemRestPathConstants {

    String GET_ITEM_BY_KEY_PATH_PART =
            GET_PATH_PART + ITEM_PATH_PART + SELF_PATH_PART + KEY_PATH_PART
                    + KEY_MASK_PATH_PART;

    String getItem(String keyString);

    String DELETE_ITEM_BY_KEY_PATH =
            DELETE_PATH_PART + ITEM_PATH_PART + SELF_PATH_PART + KEY_PATH_PART
                    + KEY_MASK_PATH_PART;

    String deleteItem(String keyString);
}
