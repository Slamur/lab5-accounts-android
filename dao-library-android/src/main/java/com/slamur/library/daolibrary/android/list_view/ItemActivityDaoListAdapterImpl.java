package com.slamur.library.daolibrary.android.list_view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.slamur.library.daolibrary.android.activity.ItemActivity;
import com.slamur.library.daolibrary.android.activity.list.ListItemActivity;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListDao;
import com.slamur.library.daolibrary.base.event.Event;

public abstract class ItemActivityDaoListAdapterImpl<
        ItemType extends Item,
        ItemActivityDaoType extends ListDao<ItemType> & ItemActivityDao<ItemType>,
        ItemActivityType extends ListItemActivity<ItemType, ItemActivityDaoType, ?>,
        ItemViewHolderType extends ItemActivityDaoListAdapterImpl.ItemViewHolder
                >
        extends BaseAdapter
implements ItemActivityDaoListAdapter<ItemType> {

    protected static String getLabeledText(String label, Object object) {
        return label + ": " + object.toString();
    }

    public static abstract class ItemViewHolder {

        public final View deleteView, showInfoView;

        @IdRes
        protected abstract int getDeleteViewId();

        @IdRes
        protected abstract int getShowInfoViewId();

        protected ItemViewHolder(View view) {
            this.deleteView = view.findViewById(getDeleteViewId());
            this.showInfoView = view.findViewById(getShowInfoViewId());
        }
    }

    protected final ItemActivityType activity;
    protected final ItemActivityDaoType dao;
    protected final LayoutInflater layoutInflater;

    protected ItemActivityDaoListAdapterImpl(ItemActivityType activity) {
        this.activity = activity;
        this.dao = activity.getDao();

        this.layoutInflater = (LayoutInflater)
                this.activity.toActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dao.getItemCount();
    }

    @Override
    public ItemType getItem(int position) {
        return dao.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return dao.getItem(position).getId();
    }

    @Override
    public void onEvent(Event<ItemType> event) {
        if (event instanceof DaoEvent) {
            notifyDataSetChanged();
        }
    }

    protected abstract void removeItem(int position);

    protected abstract void showItemInfo(int position);

    @LayoutRes
    protected abstract int getListViewLayoutId();

    protected abstract ItemViewHolderType createViewHolder(View view);

    protected abstract void updateViewHolder(View view, ItemViewHolderType viewHolder, int position);

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (null == view) {
            view = layoutInflater.inflate(getListViewLayoutId(), parent, false);
            ItemViewHolderType viewHolder = createViewHolder(view);
            view.setTag(viewHolder);
        }

        ItemViewHolderType viewHolder = (ItemViewHolderType) view.getTag();

        viewHolder.deleteView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });

        viewHolder.showInfoView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showItemInfo(position);
            }

        });

        updateViewHolder(view, viewHolder, position);

        return view;
    }
}
