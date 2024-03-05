package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class SetFragmentVisitor implements IItemVisitor {
    private Fragment selectedFragment;
    private final FragmentManager fragmentManager;

    public SetFragmentVisitor(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        CardFragment cardFragment = new CardFragment();
        Bundle args = new Bundle();
        args.putSerializable("card", card);
        cardFragment.setArguments(args);
        selectedFragment = cardFragment;
    }

    @Override
    public void visitPack(Pack pack) {
        PackFragment packFragment = new PackFragment();
        Bundle args = new Bundle();
        args.putSerializable("pack", pack);
        packFragment.setArguments(args);
        selectedFragment = packFragment;
    }

    @Override
    public void visitCurrency(Currency currency){}

    @Override
    public void visitItemCard(ItemCard itemCard){}

    public void setFragment(int containerId) {
        if (selectedFragment != null) {
            fragmentManager.beginTransaction().replace(containerId, selectedFragment).commit();
        }
    }
}
