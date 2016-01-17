package com.slamur.library.daolibrary.base.auth.basic;

import com.slamur.library.daolibrary.base.auth.Credentials;

import java.util.StringTokenizer;

public abstract class BasicAuthServiceImpl implements BasicAuthService {

    protected BasicAuthServiceImpl() {

    }

    protected abstract String encodeBase64(String s);

    protected abstract String decodeBase64(String s);

    public String encodeCredentials(String login, String password) {
        final String loginAndPassword = encodeBase64(login) + BASIC_AUTHENTICATION_CREDENTIALS_SEPARATOR + encodeBase64(password);
        return BASIC_AUTHENTICATION_CREDENTIALS_PREFIX + encodeBase64(loginAndPassword);
    }

    public Credentials decodeCredentials(String encodedCredentials) {
        if (encodedCredentials.startsWith(BASIC_AUTHENTICATION_CREDENTIALS_PREFIX)) {
            encodedCredentials = encodedCredentials.substring(BASIC_AUTHENTICATION_CREDENTIALS_PREFIX.length());
        } else {
            return null;
        }

        String decodedCredentials = decodeBase64(encodedCredentials);

        final StringTokenizer tokenizer = new StringTokenizer(
                decodedCredentials, BASIC_AUTHENTICATION_CREDENTIALS_SEPARATOR
        );

        final String encodedLogin = tokenizer.nextToken();
        final String encodedPassword = tokenizer.nextToken();

        return new Credentials(
                decodeBase64(encodedLogin), decodeBase64(encodedPassword)
        );
    }
}
