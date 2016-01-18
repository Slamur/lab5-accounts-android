package com.slamur.app.accounts.android.list_view.operation;

import android.view.View;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.activity.operation.OperationListActivity;
import com.slamur.app.accounts.android.dao.operation.OperationActivityDao;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapterImpl;

import java.text.SimpleDateFormat;

public class OperationListAdapter extends ItemActivityDaoListAdapterImpl<Operation, OperationActivityDao, OperationListActivity, OperationViewHolder> {

    public OperationListAdapter(OperationListActivity activity) {
        super(activity);
    }

    @Override
    protected void removeItem(final int position) {
        Operation operation = dao.getItem(position);
        dao.removeOperation(operation.getId());
    }

    @Override
    protected void showItemInfo(int position) {
        activity.showInfoActivity(position);
    }

    @Override
    protected int getListViewLayoutId() {
        return R.layout.list_item_operation;
    }

    @Override
    protected OperationViewHolder createViewHolder(View view) {
        return new OperationViewHolder(view);
    }

    @Override
    protected void updateViewHolder(View view, OperationViewHolder viewHolder, int position) {
        Operation operation = getItem(position);

        viewHolder.typeView.setText(
                getLabeledText(
                        "Type",
                        operation.getType().name()
                )
        );

        viewHolder.dateView.setText(
                getLabeledText(
                        "Date",
                        SimpleDateFormat.getInstance().format(operation.getDate())
                )
        );

        viewHolder.valueView.setText(
                getLabeledText(
                        "Value",
                        String.format("%.2f", operation.getValue())
                )
        );

        String sourceName;
        if (Category.INCOME_SOURCE.equals(operation.getSource())) {
            sourceName = operation.getCategory().getName();
        } else {
            sourceName = operation.getSource().getName();
        }

        viewHolder.dateView.setText(
                getLabeledText(
                        "Source",
                        sourceName
                )
        );

        String targetName;
        if (Category.EXPENSE_TARGET.equals(operation.getTarget())) {
            targetName = operation.getCategory().getName();
        } else {
            targetName = operation.getTarget().getName();
        }

        viewHolder.valueView.setText(
                getLabeledText(
                        "Target",
                        targetName
                )
        );
    }
}
