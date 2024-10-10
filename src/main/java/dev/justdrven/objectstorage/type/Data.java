package dev.justdrven.objectstorage.type;

import java.io.Serializable;

public class Data<T> implements Serializable {

    private final String id;
    private final T type;

    public Data(String id, T type) {
        this.id = id;
        this.type = type;
    }

    public final String getId() {
        return id;
    }

    public final T get() {
        return type;
    }
}
