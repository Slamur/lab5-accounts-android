package com.slamur.library.daolibrary.base.auth.dao;

import com.slamur.library.daolibrary.base.auth.Credentials;
import com.slamur.library.daolibrary.base.dao.map.MapDaoImpl;

public abstract class CredentialsDaoImpl extends MapDaoImpl<String, Credentials>
implements CredentialsDao {

    public CredentialsDaoImpl() {
        super();
    }

    @Override
    protected void removeItemInfo(Credentials item) {

    }

    @Override
    protected void setItemInfo(Credentials item, Object... itemInfo) {

    }

    @Override
    public Class<Credentials> getItemClass() {
        return Credentials.class;
    }

    @Override
    public void addCredentials(String login, String password) {
        Credentials pair = getItem(login);
        if (null == pair) {
            super.addItem(login, new Credentials(login, password));
        }
    }
}
