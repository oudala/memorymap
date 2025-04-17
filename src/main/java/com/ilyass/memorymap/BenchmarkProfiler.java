package com.ilyass.memorymap;


public class BenchmarkProfiler {
    public static void main(String[] args) throws Exception {
        MemoryHashMap map = new MemoryHashMap();

        int n = 1_000_000;
        long start, end;

        // ⏱ Insert entries
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.put(i, "val" + i);
        }
        end = System.nanoTime();
        System.out.println("Inserted 1M entries in: " + (end - start) / 1_000_000.0 + " ms");

        // Save to disk
        start = System.nanoTime();
        map.saveToFile("map_dump.ser");
        end = System.nanoTime();
        System.out.println("Saved to disk in: " + (end - start) / 1_000_000.0 + " ms");

        // Load back
        start = System.nanoTime();
        MemoryHashMap loadedMap = MemoryHashMap.loadFromFile("map_dump.ser");
        end = System.nanoTime();
        System.out.println("Loaded from disk in: " + (end - start) / 1_000_000.0 + " ms");

        // ✅ Optional check
        System.out.println("Loaded value for key 123456: " + loadedMap.get(123456));
        System.out.println("Press Enter to exit...");
        System.in.read();
    }
    }

