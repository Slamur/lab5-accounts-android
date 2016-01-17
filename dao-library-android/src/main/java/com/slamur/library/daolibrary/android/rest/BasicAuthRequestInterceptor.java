package com.slamur.library.daolibrary.android.rest;

import android.content.Context;
import android.util.Base64;

import com.slamur.library.daolibrary.android.settings.SettingsUtils;
import com.slamur.library.daolibrary.base.auth.basic.BasicAuthServiceImpl;

import retrofit.RequestInterceptor;

public class BasicAuthRequestInterceptor extends BasicAuthServiceImpl implements RequestInterceptor {

    @Override
    protected String encodeBase64(String s) {
        return Base64.encodeToString(s.getBytes(), Base64.NO_WRAP);
    }

    @Override
    protected String decodeBase64(String s) {
        return new String(
                Base64.decode(s, Base64.NO_WRAP)
        );
    }

    private final Context context;

    public BasicAuthRequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade requestFacade) {
        String login = SettingsUtils.getLogin(context);
        String password = SettingsUtils.getPassword(context);

        if (null != login && null != password) {
            final String authorizationValue = encodeCredentials(login, password);
            requestFacade.addHeader(AUTHENTICATION_HEADER, authorizationValue);
        }
    }
}
