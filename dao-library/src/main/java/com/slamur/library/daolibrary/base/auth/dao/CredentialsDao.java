package com.slamur.library.daolibrary.base.auth.dao;

import com.slamur.library.daolibrary.base.auth.Credentials;
import com.slamur.library.daolibrary.base.dao.map.MapDao;

public interface CredentialsDao extends MapDao<String, Credentials> {

    void addCredentials(String login, String password);
}
