package com.slamur.library.daolibrary.base.dao.map;

import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.collection.map.KeyItemMap;
import com.slamur.library.daolibrary.base.dao.DaoEvent;
import com.slamur.library.daolibrary.base.dao.DaoImpl;

import java.util.Map;

public abstract class MapDaoImpl<
        KeyType,
        ItemType extends Item
//        MapDaoType extends MapDaoImpl<KeyType, ItemType, MapDaoType>
        >
    extends DaoImpl<
                ItemType,
//                MapDaoListener<KeyType, ItemType>,
//                MapEvent<KeyType, ItemType>,
                KeyItemMap<KeyType, ItemType>
    //                MapDaoType
            >
    implements MapDao<KeyType, ItemType> {

    protected MapDaoImpl() {
        super(new KeyItemMap<KeyType, ItemType>());
    }

    protected MapEvent<KeyType, ItemType> createMapEvent(DaoEvent.Action action, ItemType item, KeyType key) {
        return new MapEvent<>(this, action, item, key);
    }

    @Override
    public ItemType getItem(KeyType key) {
        return items.get(key);
    }

    protected void addItem(KeyType key, ItemType item, Object... itemInfo) {
        items.put(key, item);

        setItemInfo(item, itemInfo);

        notifyListeners(createMapEvent(DaoEvent.Action.ADD, item, key));
    }

    protected void updateItems(KeyItemMap<KeyType, ItemType> updatedItems) {
        for (KeyType key : items.keySet()) {
            if (!updatedItems.containsKey(key)) {
                removeItem(key);
            }
        }

        for (Map.Entry<KeyType, ItemType> updatedKeyItemEntry : updatedItems.entrySet()) {
            updateItem(updatedKeyItemEntry.getKey(), updatedKeyItemEntry.getValue());
        }
    }

    protected void updateItem(KeyType key, ItemType item, Object... itemInfo) {
        if (items.containsKey(key)) {
            items.put(key, item);

            setItemInfo(item, itemInfo);

            notifyListeners(createMapEvent(DaoEvent.Action.UPDATE, item, key));
        } else {
            addItem(key, item, itemInfo);
        }
    }

    protected void removeItem(KeyType key) {
        ItemType removedItem = items.remove(key);

        removeItemInfo(removedItem);

        notifyListeners(createMapEvent(DaoEvent.Action.REMOVE, removedItem, key));
    }
}
