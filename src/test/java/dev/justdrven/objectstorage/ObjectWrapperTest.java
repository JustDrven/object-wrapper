package dev.justdrven.objectstorage;

import dev.justdrven.objectstorage.type.DataBoolean;
import dev.justdrven.objectstorage.type.DataFloat;
import dev.justdrven.objectstorage.type.DataString;
import dev.justdrven.objectstorage.util.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ObjectWrapperTest {

    private final String msg = "this is test message";
    private ObjectWrapper objectWrapper;

    @BeforeEach
    void setUp() {
        try {
            File file = Paths.get(
                    getClass().getClassLoader().getResource("data.ow").toURI()
            ).toFile();

            this.objectWrapper = new ObjectWrapper(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(objectWrapper);
    }

    @Test
    void readData() {
        objectWrapper.putData(new DataString(Tests.test(), msg));
        objectWrapper.save();

        DataString expected = objectWrapper.readData(Tests.test());

        assertNotNull(expected);
        assertEquals(expected.get(), msg);

        Tests.count();
    }

    @Test
    void get() {
        String test1 = Tests.test();
        objectWrapper.putData(new DataString(test1, msg));

        Tests.count();
        String test2 = Tests.test();
        objectWrapper.putData(new DataBoolean(test2, false));

        Tests.count();
        String test3 = Tests.test();
        objectWrapper.putData(new DataFloat(test3, 1f));

        objectWrapper.save();

        DataString dataString = objectWrapper.readData(test1);
        assertEquals(msg, dataString.get());

        DataBoolean dataBoolean = objectWrapper.readData(test2);
        assertFalse(dataBoolean.get());

        DataFloat dataFloat = objectWrapper.readData(test3);
        assertEquals(1f, dataFloat.get());
    }

    @Test
    void size() {
        objectWrapper.putData(new DataString(Tests.test(), msg));
        Tests.count();
        objectWrapper.putData(new DataBoolean(Tests.test(), false));
        Tests.count();
        objectWrapper.putData(new DataFloat(Tests.test(), 1f));

        objectWrapper.save();

        assertEquals(3, objectWrapper.size());
    }


}