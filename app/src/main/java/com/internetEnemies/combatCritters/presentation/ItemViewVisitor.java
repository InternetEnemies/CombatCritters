package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardViewBuilder;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyViewBuilder;
import com.internetEnemies.combatCritters.presentation.renderable.PackViewBuilder;

public class ItemViewVisitor implements IItemVisitor {

    private final Context context;
    private View view;
    private final ViewGroup parent;

    public ItemViewVisitor(Context context, ViewGroup parent) {
        this.context = context;
        this.parent = parent;
    }

    public View getView() {
        return view;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        View tempView = LayoutInflater.from(this.context).inflate(R.layout.critter_card, parent, false);
        CardViewBuilder cardViewBuilder = new CardViewBuilder(context, tempView);
        card.clone(cardViewBuilder);
        view = cardViewBuilder.getCardView();
    }

    @Override
    public void visitItemCard(ItemCard card) {
        View tempView = LayoutInflater.from(this.context).inflate(R.layout.item_card, parent, false);
        CardViewBuilder cardViewBuilder = new CardViewBuilder(context, tempView);
        card.clone(cardViewBuilder);
        view = cardViewBuilder.getCardView();
    }

    @Override
    public void visitPack(Pack pack) {
        View tempView = LayoutInflater.from(this.context).inflate(R.layout.pack, parent, false);
        PackViewBuilder packViewBuilder = new PackViewBuilder(context, tempView, pack.getName());
        view = packViewBuilder.getPackView();
    }

    @Override
    public void visitCurrency(Currency currency) {
        View tempView = LayoutInflater.from(this.context).inflate(R.layout.currency, parent, false);
        CurrencyViewBuilder currencyViewBuilder = new CurrencyViewBuilder(context, tempView, currency.getAmount());
        view = currencyViewBuilder.getCurrencyView();
    }
}
