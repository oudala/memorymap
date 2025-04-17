package com.ilyass.memorymap;

public class HashFunction {

    /**
     * Custom lightweight hash function for integer keys.
     * Uses XOR and unsigned right shift to improve bit mixing.
     */
    public static int hash(int key, int capacity) {
        int hash = key ^ (key >>> 16);
        return Math.abs(hash % capacity);
    }

}
