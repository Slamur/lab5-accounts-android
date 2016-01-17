package com.slamur.library.daolibrary.base.dao;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.collection.ItemCollection;
import com.slamur.library.daolibrary.base.event.NotifierImpl;
import com.slamur.library.daolibrary.base.function.ItemPredicate;

import java.util.ArrayList;
import java.util.List;

public abstract class DaoImpl<
        ItemType extends Item,
//        DaoListenerType extends DaoListener<ItemType>,
//        DaoEventType extends DaoEvent<ItemType>,
        ItemCollectionType extends ItemCollection<ItemType>
//        DaoType extends DaoImpl<ItemType, DaoListenerType, DaoEventType, ItemCollectionType, DaoType>
        >
        extends NotifierImpl<ItemType>
    implements Dao<ItemType> {

    protected ItemCollectionType items;
    protected long nextItemId;

    protected DaoImpl(ItemCollectionType items) {
        this.items = items;
        this.nextItemId = 0;
    }

    protected long generateNextItemId() {
        long id = nextItemId;
        ++nextItemId;

        return id;
    }

    @Override
    public List<ItemType> getItems() {
        return items.toList();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected abstract void removeItemInfo(ItemType item);

    protected abstract void setItemInfo(ItemType item, Object... itemInfo);

    /**
     * Internal method for calling after updating of all items
     */
    protected void afterUpdatedItems() {
        for (ItemType item : items) {
            this.nextItemId = Math.max(nextItemId, item.getId() + 1);
        }
    }

    protected List<ItemType> filterItems(ItemPredicate<ItemType> predicate) {
        List<ItemType> filtered = new ArrayList<>();
        for (ItemType item : getItems()) {
            if (predicate.check(item)) {
                filtered.add(item);
            }
        }

        return filtered;
    }

    protected ItemType filterItem(ItemPredicate<ItemType> predicate) {
        List<ItemType> filtered = filterItems(predicate);
        if (filtered.isEmpty()) {
            return null;
        } else {
            return filtered.get(0);
        }
    }

    @Override
    public ItemType getItem(final long id) {
        return filterItem(new ItemPredicate<ItemType>() {
            @Override
            public boolean check(ItemType item) {
                return (item.getId() == id);
            }
        });
    }
}
