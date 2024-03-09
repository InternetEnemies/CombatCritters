package com.internetEnemies.combatCritters.objects;

import android.view.View;
import android.view.ViewGroup;

public interface IItemViewCreator {
    View createViewForCritterCard(CritterCard card, ViewGroup parent);
    View createViewForItemCard(ItemCard card, ViewGroup parent);
    View createViewForPack(Pack pack, ViewGroup parent);
    View createViewForCurrency(Currency currency, ViewGroup parent);
}

