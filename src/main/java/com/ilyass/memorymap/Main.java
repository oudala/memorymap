package com.ilyass.memorymap;

public class Main {
    public static void main(String[] args) {
        MemoryHashMap map = new MemoryHashMap();

        // Put values
        map.put(1, "test1");
        map.put(2, "Ilyass");
        map.put(3, "test2");

        System.out.println(" Initial Map:");
        System.out.println(map);

        // Get values
        System.out.println(" Get key 2: " + map.get(2));
        System.out.println(" Get key 99 (not exist): " + map.get(99));

        // Update existing key
        map.put(2, "Updated Ilyass");
        System.out.println(" After update key 2:");
        System.out.println(map);

        // Remove key
        map.remove(3);
        System.out.println(" After removing key 3:");
        System.out.println(map);

        // Trigger resize
        for (int i = 4; i <= 20; i++) {
            map.put(i, "Val" + i);
        }
        System.out.println(" After adding more entries (resize triggered):");
        System.out.println(map);
    }
}
