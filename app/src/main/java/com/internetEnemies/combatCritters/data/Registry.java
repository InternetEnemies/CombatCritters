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

    public void add(T item) {
        if(item == null) {
            throw new NullPointerException();
        }
        if(items.contains(item)){
            throw new UnsupportedOperationException("Item already in registry");
        }
        items.add(item);
    }
}
