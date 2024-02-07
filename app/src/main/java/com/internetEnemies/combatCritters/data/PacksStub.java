package com.internetEnemies.combatCritters.data;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PacksStub implements IRegistry<Pack>{
    Map<Integer, Pack> allPacks;

    public PacksStub(Map<Integer, Pack> allPacks) {
        this.allPacks = allPacks;
    }

    @Override
    public Pack getSingle(int id) {

        for (Map.Entry<Integer, Pack> entry: allPacks.entrySet()) {
            if (entry.getKey() == id){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Pack> getListOf(List<Integer> ids) {
        List<Pack> resultSet = new ArrayList<>();
        for (int i: ids) {
            resultSet.add(allPacks.get(i));
            System.out.println(allPacks.get(i).getName());
        }
        return resultSet;
    }

    @Override
    public List<Pack> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(allPacks.values()));
    }
}
