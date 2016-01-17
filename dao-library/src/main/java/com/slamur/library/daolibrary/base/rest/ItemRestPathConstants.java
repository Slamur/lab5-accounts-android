package com.slamur.library.daolibrary.base.rest;

public interface ItemRestPathConstants {

    String VOID_RESPONSE = "Action performed";

    String GET_PATH_PART = "/get";
    String PUT_PATH_PART = "/put";
    String DELETE_PATH_PART = "/delete";

    String ITEM_PATH_PART = "/item";
    String ITEMS_PATH_PART = "/items";

    String SELF_PATH_PART = "/self";
    String INFO_PATH_PART = "/info";
    String FILTER_PATH_PART = "/filter";

    String COUNT_PATH_PART = "/count";

    String ID_PATH_PART = "/id";
    String ID_MASK = "id";
    String ID_MASK_PATH_PART = "/{" + ID_MASK + "}";

    String ITEM_MASK = "item";
    String ITEM_MASK_PATH_PART = "/{" + ITEM_MASK + "}";
}
