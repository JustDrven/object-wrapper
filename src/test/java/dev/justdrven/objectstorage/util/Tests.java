package dev.justdrven.objectstorage.util;

public class Tests {

    private static int count = 1;

    public static String test() {
        return "test-" + count;
    }

    public static void count() {
        count++;
    }
}
