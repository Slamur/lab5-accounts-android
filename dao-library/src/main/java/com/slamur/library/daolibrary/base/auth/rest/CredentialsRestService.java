package com.slamur.library.daolibrary.base.auth.rest;

import com.slamur.library.daolibrary.base.rest.map.MapItemRestService;

public interface CredentialsRestService extends MapItemRestService, CredentialsRestPathConstants {

    String CREDENTIALS_SERVICE_PATH = "/credentials";

    String ADD_CREDENTIALS_PATH =
            PUT_PATH_PART + ITEM_PATH_PART + SELF_PATH_PART
                    + LOGIN_MASK_PATH_PART + PASSWORD_MASK_PATH_PART;

    String addCredentials(String login, String password);
}
