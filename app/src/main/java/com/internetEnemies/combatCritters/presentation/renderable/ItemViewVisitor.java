package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

/**
 * ItemViewVisitor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     09/March/2024
 *
 * @PURPOSE:    Generates the views for various items.
 */
public class ItemViewVisitor implements IItemVisitor {
    private final Context context;
    private View view;
    private final ViewGroup parent;

    public ItemViewVisitor(Context context, ViewGroup parent) {
        this.context = context;
        this.parent = parent;
    }

    /**
     * @return the view of the item.
     */
    public View getView() {
        return view;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        visitCard(card);
    }

    @Override
    public void visitItemCard(ItemCard card) {
        visitCard(card);
    }

    /**
     * helper for both types of card
     * @param card card to operate on
     */
    private void visitCard(Card card) {
        CardViewBuilder cardViewBuilder = new CardViewBuilder(context, parent);
        card.clone(cardViewBuilder);
        view = cardViewBuilder.getCardView();
    }

    @Override
    public void visitPack(Pack pack) {
        View packView = LayoutInflater.from(this.context).inflate(R.layout.pack, parent, false);
        TextView packText = packView.findViewById(R.id.packName);
        packText.setText(pack.getName());
        view = packView;
    }

    @Override
    public void visitCurrency(Currency currency) {
        View currencyView = LayoutInflater.from(this.context).inflate(R.layout.currency, parent, false);
        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setText(String.valueOf(currency.getAmount()));
        view = currencyView;
    }
}
