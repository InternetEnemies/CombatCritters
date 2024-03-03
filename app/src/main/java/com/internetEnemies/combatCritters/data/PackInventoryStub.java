package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PackInventoryStub implements IPackInventory{
    private final List<ItemStack<Pack>> packDB;

    public PackInventoryStub() {
        packDB = new ArrayList<>();
    }

    @Override
    public int getPackAmount(Pack pack) {
        ItemStack<Pack> stack = getItemStack(pack);
        if (stack != null) {
            return stack.getAmount();
        } else {
            return 0;// card not found so quantity == 0
        }
    }

    @Override
    public void addPack(Pack pack) {
        ItemStack<Pack> packStack = getItemStack(pack);
        if (packStack != null) { // replace old item stack with new one with updated quantity
            ItemStack<Pack> newStack = new ItemStack<>(pack,packStack.getAmount() + 1);
            packDB.set(packDB.indexOf(packStack), newStack);
        } else { // add new itemstack
            ItemStack<Pack> newStack = new ItemStack<>(pack, 1);
            packDB.add(newStack);
        }

    }

    @Override
    public void addPacks(List<Pack> packs) {
        for (Pack pack : packs) {
            addPack(pack);
        }
    }

    @Override
    public void removePack(Pack pack, int amount) {
        ItemStack<Pack> packStack = getItemStack(pack);
        assert packStack != null;

        if (packStack.getAmount() <= amount){
            // full remove card
            packDB.remove(packStack);
        } else {
            // partial remove
            ItemStack<Pack> newPackStack = new ItemStack<>(pack, packStack.getAmount() - amount);
            packDB.set(packDB.indexOf(packStack),newPackStack);
        }

    }

    @Override
    public void removePack(Pack pack) {
        removePack(pack, 1);
    }

    @Override
    public List<ItemStack<Pack>> getPacks() {
        return Collections.unmodifiableList(new ArrayList<>(packDB));
    }

    private ItemStack<Pack> getItemStack(Pack pack){
        assert pack != null;
        Optional<ItemStack<Pack>> stack = packDB.stream()
                .filter(e -> e.getItem().equals(pack)) // we could assert that the count here is always 1 or 0
                .findFirst();

        return stack.orElse(null);
    }
}
