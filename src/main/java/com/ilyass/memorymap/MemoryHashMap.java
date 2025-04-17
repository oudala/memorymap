package com.ilyass.memorymap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MemoryHashMap implements Serializable{
    private static final long serialVersionUID = 1L;

    private int[] keys;
    private String[] values;
    private boolean[] used;
    private int size;
    private int capacity;
    private static final float LOAD_FACTOR = 0.7f;
    private final transient PoolManager pool = new PoolManager(); //  String pool added

    public MemoryHashMap() {
        capacity = 16;
        size = 0;
        keys = new int[capacity];
        values = new String[capacity];
        used = new boolean[capacity];
    }

    //serialize and deserialize methods
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }
    public static MemoryHashMap loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (MemoryHashMap) in.readObject();
        }
    }

    public void put(int key, String value) {
        value = pool.get(value); //  Use pooled string

        int index = HashFunction.hash(key, capacity);

        while (used[index]) {
            if (keys[index] == key) {
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
        }

        keys[index] = key;
        values[index] = value;
        used[index] = true;
        size++;

        if ((float) size / capacity >= LOAD_FACTOR) {
            resize();
        }
    }

    public String get(int key) {
        int index = HashFunction.hash(key, capacity);
        while (used[index]) {
            if (keys[index] == key) {
                return values[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    public void remove(int key) {
        int index = HashFunction.hash(key, capacity);
        while (used[index]) {
            if (keys[index] == key) {
                used[index] = false;
                values[index] = null;
                size--;
                return;
            }
            index = (index + 1) % capacity;
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        int[] oldKeys = keys;
        String[] oldValues = values;
        boolean[] oldUsed = used;

        keys = new int[newCapacity];
        values = new String[newCapacity];
        used = new boolean[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldUsed[i]) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (used[i]) {
                sb.append(keys[i]).append(": ").append(values[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
