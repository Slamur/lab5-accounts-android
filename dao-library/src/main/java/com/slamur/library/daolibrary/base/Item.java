package com.slamur.library.daolibrary.base;

public abstract class Item {

    private static final int
            INTEGER_MASK = Integer.MAX_VALUE,
            INTEGER_BITS = Integer.bitCount(INTEGER_MASK);

    private final long id;

    protected Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (null == obj) {
            return (null == this);
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item item = (Item) obj;

        if (id != item.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash |= (id >> INTEGER_BITS);
        hash |= (id & INTEGER_MASK);

        return hash;
    }
}
