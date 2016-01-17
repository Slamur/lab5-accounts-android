package com.slamur.library.daolibrary.base.rest;

public interface ItemRestService extends ItemRestPathConstants {

    String GET_ITEMS_PATH = GET_PATH_PART + ITEMS_PATH_PART + SELF_PATH_PART;

    String getItems();

    String GET_ITEMS_COUNT_PATH = GET_PATH_PART + ITEMS_PATH_PART + INFO_PATH_PART + COUNT_PATH_PART;

    String getItemsCount();

    String GET_ITEM_BY_ID_PATH = GET_PATH_PART + ITEM_PATH_PART + SELF_PATH_PART + ID_PATH_PART + ID_MASK_PATH_PART;

    String getItem(long id);
}