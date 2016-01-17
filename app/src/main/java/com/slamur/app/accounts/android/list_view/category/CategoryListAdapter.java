package com.slamur.app.accounts.android.list_view.category;

import android.view.View;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.activity.category.CategoryListActivity;
import com.slamur.app.accounts.android.dao.category.CategoryActivityDao;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.library.daolibrary.android.list_view.ItemActivityDaoListAdapterImpl;

public class CategoryListAdapter extends ItemActivityDaoListAdapterImpl<Category, CategoryActivityDao, CategoryListActivity, CategoryViewHolder> {

    public CategoryListAdapter(CategoryListActivity activity) {
        super(activity);
    }

    @Override
    protected void removeItem(final int position) {
        dao.removeCategory(position);
    }

    @Override
    protected void showItemInfo(int position) {
        activity.showInfoActivity(position);
    }

    @Override
    protected int getListViewLayoutId() {
        return R.layout.list_item_category;
    }

    @Override
    protected CategoryViewHolder createViewHolder(View view) {
        return new CategoryViewHolder(view);
    }

    @Override
    protected void updateViewHolder(View view, CategoryViewHolder viewHolder, int position) {
        Category category = getItem(position);

        viewHolder.nameView.setText(getLabeledText("Name", category.getName()));
    }
}
