package com.internetEnemies.combatCritters.data;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PacksStub implements IRegistry<Pack>{
    List<Pack> allPacks;

    public PacksStub(List<Pack> allPacks) {
        this.allPacks = allPacks;
    }

    @Override
    public Pack getSingle(int id) {

        for (Pack i: allPacks) {
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Pack> getListOf(List<Integer> ids) {
        List<Pack> resultSet = new ArrayList<>();
        for (int i: ids) {
            List<Pack> packsWithID = allPacks.stream().filter(p -> p.getId() == i).collect(Collectors.toList());
            resultSet.addAll(packsWithID);
        }
        return resultSet;
    }


    @NonNull
    @Override
    public Iterator<Pack> iterator() {
        return new ArrayList<>(allPacks).iterator();
    }
}
