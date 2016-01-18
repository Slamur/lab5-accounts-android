package com.slamur.app.accounts.android.list_view.account;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.activity.account.AccountListActivity;
import com.slamur.app.accounts.android.dao.account.AccountActivityDao;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapterImpl;

public class AccountListAdapter extends ItemActivityDaoListAdapterImpl<Account, AccountActivityDao, AccountListActivity, AccountViewHolder> {

    public static final String ONLY_THIS_ACCOUNT_MESSAGE = "There are can't be no accounts";
    public static final String REMOVE_ACCOUNT_DIALOG_TITLE = "Delete account";

    public AccountListAdapter(AccountListActivity activity) {
        super(activity);
    }

    @LayoutRes
    protected int getDeleteAccountDialogLayoutId() {
        return R.layout.dialog_list_item_account_delete;
    }

    @IdRes
    protected int getDeleteAccountDialogHeirsSpinnerId() {
        return R.id.dialog_list_item_account_delete_spinner_heirs;
    }

    @IdRes
    protected int getDeleteAccountDialogButtonId() {
        return R.id.dialog_list_item_account_delete_button;
    }

    @Override
    protected void removeItem(final int position) {
        Account removingAccount = getItem(position);

        Account[] others = dao.getAccountsExcept(removingAccount).toArray(new Account[0]);
        if (others.length > 0) {
            final AlertDialog.Builder viewDialog = new AlertDialog.Builder(activity);
            viewDialog.setTitle(REMOVE_ACCOUNT_DIALOG_TITLE);

            final View dialogView = LayoutInflater.from(activity).inflate(getDeleteAccountDialogLayoutId(), null);
            viewDialog.setView(dialogView);

            final Dialog dialog = viewDialog.create();

            final Spinner spinner = AndroidUtils.findViewById(dialogView, getDeleteAccountDialogHeirsSpinnerId());
            ArrayAdapter<Account> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, others);
            spinner.setAdapter(adapter);

            View deleteButton = AndroidUtils.findViewById(dialogView, getDeleteAccountDialogButtonId());
            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Account heir = (Account) spinner.getSelectedItem();

                    dao.removeAccount(position, heir);
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            AndroidUtils.showToast(activity, ONLY_THIS_ACCOUNT_MESSAGE);
        }
    }

    @Override
    protected void showItemInfo(int position) {
        activity.showInfoActivity(position);
    }

    @Override
    protected int getListViewLayoutId() {
        return R.layout.list_item_account;
    }

    @Override
    protected AccountViewHolder createViewHolder(View view) {
        return new AccountViewHolder(view);
    }

    @Override
    protected void updateViewHolder(View view, AccountViewHolder viewHolder, int position) {
        Account account = getItem(position);

        viewHolder.nameView.setText(getLabeledText("Name", account.getName()));
        viewHolder.balanceView.setText(getLabeledText("Balance", account.getBalance()));
    }
}
