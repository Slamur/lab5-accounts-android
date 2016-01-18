package com.slamur.app.accounts.android.activity.account;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.InputType;
import android.widget.EditText;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.dao.account.AccountActivityDao;
import com.slamur.app.accounts.android.dao.account.AccountBaseDao;
import com.slamur.app.accounts.android.provider.account.AccountActivityProvider;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.activity.info.InfoItemActivityImpl;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;

public class AccountInfoActivity extends InfoItemActivityImpl<Account, AccountActivityDao, AccountActivityProvider>
implements AccountActivity {



    protected EditText mNameView;

    // TODO try after with NumberPicker
    protected EditText mBalanceView;

    protected EditText mDescriptionView;

    @Override
    protected void onInfoActivityCreate(Bundle savedInstanceState, int actionType) {
        mNameView = AndroidUtils.findViewById(this, getNameViewId());

        mBalanceView = AndroidUtils.findViewById(this, getBalanceViewId());
        mBalanceView.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        mDescriptionView = AndroidUtils.findViewById(this, getDescriptionViewId());
    }

    @Override
    protected int getAddItemViewId() {
        return R.id.account_info_add_button;
    }

    @IdRes
    protected int getNameViewId() {
        return R.id.account_name_edit;
    }

    @IdRes
    protected int getBalanceViewId() {
        return R.id.account_balance_edit;
    }

    @IdRes
    protected int getDescriptionViewId() {
        return R.id.account_description_edit;
    }

    @Override
    protected void disableInputs() {
        mNameView.setEnabled(false);
        mBalanceView.setEnabled(false);
        mDescriptionView.setEnabled(false);
    }

    @Override
    protected Account createAndAddItem() {
        String name = mNameView.getText().toString();
        double balance = Double.parseDouble(mBalanceView.getText().toString());
        String description = mDescriptionView.getText().toString();

        return dao.addAccount(name, balance, description);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_info_account;
    }

    @Override
    protected AccountActivityProvider createProvider() {
        return AccountBaseDao.getInstance(this);
    }

    @Override
    protected AccountActivityDao createDao() {
        return new AccountActivityDao(this);
    }

    @Override
    public void onEvent(Event<Account> event) {
        if (event instanceof ItemLoadEvent) {
            ItemLoadEvent<Account> itemLoadEvent = event.toType();

            Account account = itemLoadEvent.getItem();

            mItemId = account.getId();

            mNameView.setText(account.getName());
            mBalanceView.setText(Double.toString(account.getBalance()));
            mDescriptionView.setText(account.getDescription());
        }
    }
}
