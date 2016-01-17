package com.slamur.app.accounts.android.list_view.account;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

import com.slamur.app.accounts.android.R;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapterImpl;

public class AccountViewHolder extends ItemActivityDaoListAdapterImpl.ItemViewHolder {

    public final TextView nameView, balanceView;

    protected AccountViewHolder(View view) {
        super(view);

        this.nameView = AndroidUtils.findViewById(view, getNameViewId());
        this.balanceView = AndroidUtils.findViewById(view, getBalanceViewId());
    }

    @Override
    protected int getDeleteViewId() {
        return R.id.account_list_item_remove;
    }

    @Override
    protected int getShowInfoViewId() {
        return R.id.account_list_item_info;
    }

    @IdRes
    protected int getNameViewId() {
        return R.id.account_list_item_name;
    }

    @IdRes
    protected int getBalanceViewId() {
        return R.id.account_list_item_balance;
    }
}
