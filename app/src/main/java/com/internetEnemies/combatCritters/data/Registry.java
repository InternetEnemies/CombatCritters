/**
 * Registry.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-07
 *
 * @PURPOSE:    generic implementation of IRegistry
 */

package com.internetEnemies.combatCritters.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Registry<T> implements IRegistry<T>{
    private final List<T> items;

    public Registry(List<T> start) {
        this();
        items.addAll(start);
    }

    public Registry(){
        items = new ArrayList<>();
    }
    @Override
    public T getSingle(int id) {
        return items.get(id);
    }

    @Override
    public List<T> getListOf(List<Integer> ids) {
        List<T> result = new ArrayList<>();
        for(int id : ids) {
            result.add(items.get(id));
        }
        return result;
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }

    public T add(T item) {
        assert item != null;
        if(items.contains(item)){
            throw new UnsupportedOperationException("Item already in registry");
        }
        items.add(item);
        return item;
    }
}
