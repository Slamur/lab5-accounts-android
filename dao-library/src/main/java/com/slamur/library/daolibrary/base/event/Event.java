package com.slamur.library.daolibrary.base.event;

import com.slamur.library.daolibrary.base.Item;

public abstract class Event <ItemType extends Item>  {

    public interface EventAction { }

    private final Notifier<ItemType> notifier;
    private final EventAction action;

    protected Event(Notifier<ItemType> notifier, EventAction action) {
        this.notifier = notifier;
        this.action = action;
    }

    public <NotifierType extends Notifier<ItemType>> NotifierType getNotifier() {
        return (NotifierType)notifier;
    }

//    public EventAction getAction() {
//        return action;
//    }

    public boolean isAction(EventAction action) {
        return action.equals(this.action);
    }

    public <EventType extends Event<ItemType>> EventType toType() {
        return (EventType) this;
    }
}
