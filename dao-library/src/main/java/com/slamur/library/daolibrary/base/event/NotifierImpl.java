package com.slamur.library.daolibrary.base.event;

import com.slamur.library.daolibrary.base.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class NotifierImpl<
                ItemType extends Item
//                ListenerType extends Listener<ItemType>,
//                NotifierType extends NotifierImpl<ItemType, ListenerType, NotifierType, EventType>,
                >
    implements Notifier<ItemType> {

    protected Collection<Listener<ItemType>> listeners;

    protected NotifierImpl() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addListener(Listener<ItemType> listener) {
        listeners.add(listener);
//        listener.setNotifier((NotifierType) this);
    }

    @Override
    public void removeListener(Listener<ItemType> listener) {
        listeners.remove(listener);
//        listener.setNotifier(null);
    }

    protected void notifyListeners(Event<ItemType> event) {
        for (Listener<ItemType> listener : listeners) {
            listener.onEvent(event);
        }
    }
}
