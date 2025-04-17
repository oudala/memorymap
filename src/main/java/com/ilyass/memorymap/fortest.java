package com.ilyass.memorymap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class fortest {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, String> map = new HashMap<>();

        int n = 1_000_000;
        long start, end;

        // ⏱ Insert entries
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.put(i, "val" + i);
        }
        end = System.nanoTime();
        System.out.println("Inserted 1M entries in: " + (end - start) / 1_000_000.0 + " ms");

        //  Save to disk
        start = System.nanoTime();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("map_dump.ser"))) {
            out.writeObject(map);
        }
        end = System.nanoTime();
        System.out.println("Saved to disk in: " + (end - start) / 1_000_000.0 + " ms");

        //  Load back
        start = System.nanoTime();
        HashMap<Integer, String> loadedMap;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("map_dump.ser"))) {
            loadedMap = (HashMap<Integer, String>) in.readObject();
        }
        end = System.nanoTime();
        System.out.println("Loaded from disk in: " + (end - start) / 1_000_000.0 + " ms");

        // Optional check
        System.out.println("Loaded value for key 123456: " + loadedMap.get(123456));
        System.out.println("Press Enter to exit...");
        System.in.read();
    }
}
