package dev.justdrven.objectstorage;

import dev.justdrven.objectstorage.exception.DataNotFoundException;
import dev.justdrven.objectstorage.type.Data;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ObjectWrapper {

    private final Set<Data<?>> map = new HashSet<>();
    private final File file;

    public ObjectWrapper(File file) {
        if (file == null || !file.exists()) {
            throw new RuntimeException("File cannot be null!");
        }
        this.file = file;
    }

    public final void putData(Data<?> data) {
        map.add(data);
    }

    public final boolean contains(String id) {
        for (Data<?> data : map) {
            if (data.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return readFromFile().size();
    }

    public final <T extends Data<?>> T readData(String id) {
        Set<Data<?>> data = readFromFile();
        return ((T)data.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(id)));
    }

    public final void clear() {
        if (map.isEmpty())return;

        map.clear();
    }

    public final boolean isEmpty() {
        return readFromFile().isEmpty() || map.isEmpty();
    }

    private Set<Data<?>> readFromFile() {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (Set<Data<?>>) input.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Input cannot be " + e.getMessage());
        }
    }

    public final void save() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))){
            output.writeObject(map);

            output.flush();
        } catch (IOException e) {
            throw new RuntimeException("Output cannot be " + e.getMessage());
        }
    }
}
