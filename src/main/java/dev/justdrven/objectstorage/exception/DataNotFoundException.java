package dev.justdrven.objectstorage.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String id) {
        super(String.format("Data '%s' not found!", id));
    }

}
