
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ilyass.memorymap.MemoryHashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;

public class MemoryHashMapTest {

    private MemoryHashMap map;

    @BeforeEach
    public void setup() {
        map = new MemoryHashMap();
    }

    @Test
    public void testPutAndGet() {
        map.put(1, "One");
        map.put(2, "Two");

        assertEquals("One", map.get(1));
        assertEquals("Two", map.get(2));
        System.out.println(" put/get test passed: " + map.get(1));
    }

    @Test
    public void testOverwriteValue() {
        map.put(1, "One");
        map.put(1, "Uno");

        assertEquals("Uno", map.get(1));
    }

    @Test
    public void testRemove() {
        map.put(3, "Three");
        assertEquals("Three", map.get(3));

        map.remove(3);
        assertNull(map.get(3));
    }

    @Test
    public void testGetNonExistentKey() {
        assertNull(map.get(99));
    }

    @Test
    public void testRemoveNonExistentKey() {
        map.remove(100); // Should not crash
        assertNull(map.get(100));
    }

    @Test
    public void testResizeMaintainsData() {
        for (int i = 0; i < 100; i++) {
            map.put(i, "Val" + i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("Val" + i, map.get(i));
        }
    }
    @AfterAll
    static void done() {
        System.out.println(" All tests executed.");
    }
}
