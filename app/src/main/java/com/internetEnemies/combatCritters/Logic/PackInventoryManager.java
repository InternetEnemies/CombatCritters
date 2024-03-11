package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

//TODO: temp class
public class PackInventoryManager implements IPackInventoryManager{
    public List<Pack> getPacks() {
        IPackInventory packInventory = Database.getInstance().getPackInventory();
        List<ItemStack<Pack>> packStackList = packInventory.getPacks();
        List<Pack> packs = new ArrayList<>();

        for(int i = 0; i < packStackList.size(); i++) {
            for(int j = 0; j < packStackList.get(i).getAmount(); j++) {
                packs.add(packStackList.get(i).getItem());
            }
        }
        return packs;
    }
}
