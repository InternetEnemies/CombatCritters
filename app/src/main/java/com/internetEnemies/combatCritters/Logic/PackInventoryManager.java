package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

public class PackInventoryManager implements IPackInventoryManager{
    IPackInventory inventory;

    public PackInventoryManager(IPackInventory inventory){
        this.inventory = inventory;
    }

    @Override
    public List<Pack> packsInInventory() {
        List<Pack> allPacksSingle = new ArrayList<>();

        List<ItemStack<Pack>> packsFromInventory = inventory.getPacks();

        for (ItemStack<Pack> pack: packsFromInventory) {
            for (int i = 0; i < pack.getAmount(); i++){
                allPacksSingle.add(pack.getItem());
            }
        }

        return allPacksSingle;
    }

    @Override
    public void removePack(Pack pack) {
        inventory.removePack(pack);
    }
}
