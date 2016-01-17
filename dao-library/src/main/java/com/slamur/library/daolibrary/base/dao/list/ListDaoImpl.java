package com.slamur.library.daolibrary.base.dao.list;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.collection.list.ItemList;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.DaoImpl;

import java.util.List;

public abstract class ListDaoImpl<
        ItemType extends Item
//        ListDaoType extends ListDaoImpl<ItemType, ListDaoType>
        >
    extends DaoImpl<
                    ItemType,
//                    ListDaoListener<ItemType>,
//                    ListEvent<ItemType>,
                    ItemList<ItemType>
        //            ListDaoType
                >
    implements ListDao<ItemType> {

    protected ListDaoImpl() {
        super(new ItemList<ItemType>());
    }

    protected ListEvent<ItemType> createListEvent(DaoEvent.Action action, ItemType item, int index) {
        return new ListEvent<>(this, action, item, index);
    }

    @Override
    public ItemType getItem(int index) {
        return items.get(index);
    }

    protected void addItem(ItemType item, Object... itemInfo) {
        items.add(item);

        setItemInfo(item, itemInfo);

        notifyListeners(
                createListEvent(DaoEvent.Action.ADD, item, items.size() - 1)
        );
    }

    protected void insertItem(int index, ItemType item, Object... itemInfo) {
        if (0 <= index && items.size() > index) {
            items.add(index, item);

            setItemInfo(item, itemInfo);

            notifyListeners(
                    createListEvent(DaoEvent.Action.ADD, item, index)
            );
        } else if (items.size() == index) {
            addItem(item);
        }
    }

    protected void updateItems(List<ItemType> updatedItems) {
        for (int index = 0, size = Math.min(items.size(), updatedItems.size()); index < size; ++index) {
            items.set(index, updatedItems.get(index));
        }

        for (int index = items.size() - 1; index >= updatedItems.size(); --index) {
            items.remove(index);
        }

        for (int index = items.size(); index < updatedItems.size(); ++index) {
            items.add(updatedItems.get(index));
        }

        afterUpdatedItems();

        // -----<=====|
//        notifyListeners(createListEvent(DaoEvent.Action.UPDATE, null, -1));
    }

    protected void updateItem(int index, ItemType updatedItem, Object... itemInfo) {
        if (0 <= index && items.size() > index) {

            ItemType oldItem = items.get(index);
            removeItemInfo(oldItem);

            items.set(index, updatedItem);
            setItemInfo(updatedItem, itemInfo);

            notifyListeners(
                    createListEvent(DaoEvent.Action.UPDATE, updatedItem, index)
            );
        } else if (items.size() == index) {
            addItem(updatedItem);
        }
    }

    protected void removeItem(ItemType item) {
        removeItem(items.indexOf(item));
    }

    protected void removeItem(int index) {
        if (0 <= index && items.size() > index) {
            ItemType removedItem = items.get(index);

            items.remove(index);
            removeItemInfo(removedItem);

            notifyListeners(
                    createListEvent(DaoEvent.Action.REMOVE, removedItem, index)
            );
        }
    }
}
