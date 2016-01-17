package com.slamur.app.accounts.android.activity.account;

import com.slamur.app.accounts.android.dao.account.AccountActivityDao;
import com.slamur.app.accounts.android.provider.account.AccountActivityProvider;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.android.activity.ItemActivity;

public interface AccountActivity extends ItemActivity<Account, AccountActivityDao, AccountActivityProvider> {



}
