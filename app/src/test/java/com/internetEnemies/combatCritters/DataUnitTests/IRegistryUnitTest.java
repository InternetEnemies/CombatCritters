package com.internetEnemies.combatCritters.DataUnitTests;
import org.junit.Test;

import com.internetEnemies.combatCritters.data.IRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IRegistryUnitTest {
    // Define a sample implementation of IRegistry for testing
    private class SampleRegistry implements IRegistry<String> {
        private List<String> items;

        public SampleRegistry(List<String> items) {
            this.items = items;
        }

        @Override
        public String getSingle(int id) {
            if (id >= 0 && id < items.size()) {
                return items.get(id);
            } else {
                return null;
            }
        }

        @Override
        public List<String> getListOf(List<Integer> ids) {
            List<String> result = new ArrayList<>();
            for (int id : ids) {
                String item = getSingle(id);
                if (item != null) {
                    result.add(item);
                }
            }
            return result;
        }
    }

    // Test the getSingle method
    @Test
    public void getSingle_validId_returnsItem() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");

        SampleRegistry registry = new SampleRegistry(items);

        assertEquals("Item 1", registry.getSingle(0));
        assertEquals("Item 2", registry.getSingle(1));
    }

    @Test
    public void getSingle_invalidId_returnsNull() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");

        SampleRegistry registry = new SampleRegistry(items);

        assertNull(registry.getSingle(-1));
        assertNull(registry.getSingle(2));
    }

    // Test the getListOf method
    @Test
    public void getListOf_validIds_returnsItems() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        SampleRegistry registry = new SampleRegistry(items);

        List<String> result = registry.getListOf(Arrays.asList(0, 2));

        assertEquals(2, result.size());
        assertEquals("Item 1", result.get(0));
        assertEquals("Item 3", result.get(1));
    }

    @Test
    public void getListOf_invalidIds_returnsEmptyList() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");

        SampleRegistry registry = new SampleRegistry(items);

        List<String> result = registry.getListOf(Arrays.asList(-1, 2, 3));

        assertEquals(0, result.size());
    }
}
