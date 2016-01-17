package com.slamur.library.daolibrary.base.auth;

public interface AuthService extends AuthConstants {

    String encodeCredentials(String login, String password);

    Credentials decodeCredentials(String encodedCredentials);
}
