package com.slamur.app.accounts.android.list_view.operation;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

import com.slamur.app.accounts.android.R;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapterImpl;

public class OperationViewHolder extends ItemActivityDaoListAdapterImpl.ItemViewHolder {

    public final TextView typeView;
    public final TextView dateView;
    public final TextView valueView;
    public final TextView sourceView, targetView;

    protected OperationViewHolder(View view) {
        super(view);

        this.typeView = AndroidUtils.findViewById(view, getTypeViewId());
        this.dateView = AndroidUtils.findViewById(view, getDateViewId());
        this.valueView = AndroidUtils.findViewById(view, getValueViewId());
        this.sourceView = AndroidUtils.findViewById(view, getSourceViewId());
        this.targetView = AndroidUtils.findViewById(view, getTargetViewId());
    }

    @Override
    protected int getDeleteViewId() {
        return R.id.list_item_operation_remove;
    }

    @Override
    protected int getShowInfoViewId() {
        return R.id.list_item_operation_info;
    }

    @IdRes
    protected int getTypeViewId() {
        return R.id.list_item_operation_type_text;
    }

    @IdRes
    protected int getDateViewId() {
        return R.id.list_item_operation_date_text;
    }

    @IdRes
    protected int getValueViewId() {
        return R.id.list_item_operation_value_text;
    }

    @IdRes
    protected int getSourceViewId() {
        return R.id.list_item_operation_source_text;
    }

    @IdRes
    protected int getTargetViewId() {
        return R.id.list_item_operation_target_text;
    }
}
