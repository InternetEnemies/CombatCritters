package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public interface IPackInventoryManager {
    List<Pack> packsInInventory();

    void removePack(Pack pack);
}
