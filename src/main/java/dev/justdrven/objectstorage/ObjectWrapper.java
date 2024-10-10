package dev.justdrven.objectstorage;

import dev.justdrven.objectstorage.exception.DataNotFoundException;
import dev.justdrven.objectstorage.type.Data;

import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class ObjectWrapper {

    private final Set<Data<?>> map = new HashSet<>();

    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    public ObjectWrapper(InputStream input, OutputStream output) {
        this.input = (ObjectInputStream)input;
        this.output = (ObjectOutputStream)output;
    }

    public ObjectWrapper(File file) throws IOException {
        this(Files.newInputStream(file.toPath()), Files.newOutputStream(file.toPath()));
    }

    public final void putData(Data<?> data) {
        if (contains(data.getId()))return;

        map.add(data);
    }

    public final boolean contains(String id) {
        return map.stream()
                .anyMatch(data -> data.getId().equals(id));
    }

    public final Data<?> read(String id) {
        return readFromFile().stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(id));
    }

    private Set<Data<?>> readFromFile() {
        try {
            return (Set<Data<?>>)input.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Input cannot be " + e.getMessage());
        }
    }

    public final void save() {
        try {
            output.writeObject(map);

            output.close();
        } catch (IOException e) {
            throw new RuntimeException("Output cannot be " + e.getMessage());
        }
    }


}
