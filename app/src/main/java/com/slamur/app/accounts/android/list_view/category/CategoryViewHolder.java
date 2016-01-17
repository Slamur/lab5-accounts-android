package com.slamur.app.accounts.android.list_view.category;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

import com.slamur.app.accounts.android.R;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapterImpl;

public class CategoryViewHolder extends ItemActivityDaoListAdapterImpl.ItemViewHolder {

    public final TextView nameView;

    protected CategoryViewHolder(View view) {
        super(view);

        this.nameView = AndroidUtils.findViewById(view, getNameViewId());
    }

    @Override
    protected int getDeleteViewId() {
        return R.id.list_item_category_remove;
    }

    @Override
    protected int getShowInfoViewId() {
        return R.id.list_item_category_info;
    }

    @IdRes
    protected int getNameViewId() {
        return R.id.list_item_category_name_text;
    }
}
