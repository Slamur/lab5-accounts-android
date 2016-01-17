package com.slamur.library.daolibrary.base.rest.list;

import com.slamur.library.daolibrary.base.rest.ItemRestService;

public interface ListItemRestService
        extends ItemRestService, ListItemRestPathConstants {

    String GET_ITEM_BY_INDEX_PATH =
            GET_PATH_PART + ITEM_PATH_PART + SELF_PATH_PART + INDEX_PATH_PART
                    + INDEX_MASK_PATH_PART;

    String getItem(int index);

    String DELETE_ITEM_BY_INDEX_PATH =
            DELETE_PATH_PART + ITEM_PATH_PART + SELF_PATH_PART + INDEX_PATH_PART
                    + INDEX_MASK_PATH_PART;

    String deleteItem(int index);
}
