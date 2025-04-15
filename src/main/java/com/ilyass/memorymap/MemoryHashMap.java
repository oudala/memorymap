package com.ilyass.memorymap;

public class MemoryHashMap {
    private int[] keys;
    private String[] values;
    private boolean[] used;
    private int size;
    private int capacity;
    private static final float LOAD_FACTOR = 0.7f;

    public MemoryHashMap() {
        capacity = 16;
        size = 0;
        keys = new int[capacity];
        values = new String[capacity];
        used = new boolean[capacity];
    }
}