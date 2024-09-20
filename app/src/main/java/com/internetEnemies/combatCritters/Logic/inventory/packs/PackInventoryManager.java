package com.internetEnemies.combatCritters.Logic.inventory.packs;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

/**
 * PackInventoryManager.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-13
 *
 * @PURPOSE:    Implementation for IPackInventoryManager.
 */

public class PackInventoryManager implements IPackInventoryManager{
    IPackInventory packInventory;
    ICardInventory cardInventory;

    IPackOpener packOpener;

    public PackInventoryManager(){
        cardInventory = Database.getInstance().getCardInventory();
        packInventory = Database.getInstance().getPackInventory();
        packOpener = new PackOpener(cardInventory);
    }


    public PackInventoryManager(IPackInventory packInventory, ICardInventory cardInventory){
        this.packInventory = packInventory;
        this.cardInventory = cardInventory;
        packOpener = new PackOpener(cardInventory);
    }

    @Override
    public List<Pack> packsInInventory() {
        List<Pack> allPacksSingle = new ArrayList<>();

        List<ItemStack<Pack>> packsFromInventory = packInventory.getPacks();

        for (ItemStack<Pack> pack: packsFromInventory) {
            for (int i = 0; i < pack.getAmount(); i++){
                allPacksSingle.add(pack.getItem());
            }
        }

        return allPacksSingle;
    }

    @Override
    public List<Card> openPack(Pack pack) {
        List<Card> pulledCards = packOpener.openPack(pack);
        packInventory.removePack(pack);

        return pulledCards;
    }
}
