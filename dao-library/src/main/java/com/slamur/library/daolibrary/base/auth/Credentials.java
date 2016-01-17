package com.slamur.library.daolibrary.base.auth;

import com.slamur.library.daolibrary.base.Item;

public class Credentials extends Item {

    private final String login, password;

    public Credentials(String login, String password) {
        super(-1);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;

        Credentials other = (Credentials) o;

        if (!login.equals(other.login)) return false;
        if (!password.equals(other.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
