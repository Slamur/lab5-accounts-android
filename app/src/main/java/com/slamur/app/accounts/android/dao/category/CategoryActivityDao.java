package com.slamur.app.accounts.android.dao.category;

import com.slamur.app.accounts.android.activity.category.CategoryActivity;
import com.slamur.app.accounts.android.provider.category.CategoryActivityProvider;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.category.dao.CategoryDaoImpl;
import com.slamur.library.daolibrary.android.dao.ItemActivityDao;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.list.ListEvent;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;

public class CategoryActivityDao extends CategoryDaoImpl
implements ItemActivityDao<Category> {

    public CategoryActivityDao(CategoryActivity activity) {
        super();

        CategoryActivityProvider categoryProvider = activity.getProvider();

        categoryProvider.addListener(this);
        this.addListener(categoryProvider);
    }

    @Override
    public void onEvent(Event<Category> event) {
        if (event instanceof ItemProviderEvent) {
            if (event.isAction(ItemProviderEvent.Action.LOAD_ITEMS)) {
                ItemsLoadEvent<Category> itemsLoadEvent = event.toType();
                updateItems(itemsLoadEvent.getItems());
            }
        } else if (event instanceof ListEvent) {
            if (event.isAction(DaoEvent.Action.UPDATE)) {
                ListEvent<Category> categoryListEvent = event.toType();
                this.updateItem(categoryListEvent.getIndex(), categoryListEvent.getItem());
            }
        }
    }
}
