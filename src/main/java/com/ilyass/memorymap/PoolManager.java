package com.ilyass.memorymap;

import java.io.Serializable;
import java.util.HashMap;

public class PoolManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private final HashMap<String, String> pool;

    public PoolManager() {
        pool = new HashMap<>();
    }

    /**
     * Get the pooled version of a string.
     * If it exists, return it. Else, put it in the pool and return.
     */
    public String get(String value) {
        if (value == null) return null;
        String pooled = pool.get(value);
        if (pooled != null) {
            return pooled;
        }
        pool.put(value, value);
        return value;
    }

    /**
     * Optional: current size of the pool.
     */
    public int size() {
        return pool.size();
    }
}
