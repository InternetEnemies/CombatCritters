package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

/**
 * TradeItemViewVisitor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     21/March/2024
 *
 * @PURPOSE:    Generates the views trade items. Packs and cards will have their stack amounts added to the
 *              view, currency will not have its stack amount added to the view.
 */
public class TradeItemViewVisitor implements IItemVisitor {
    private final Context context;
    private final View view;
    private final ViewGroup parent;
    private final int amount;
    private static final float CURRENCY_SCALE_FACTOR = 1.5f;
    private static final float CARD_SCALE_FACTOR = .6f;

    public TradeItemViewVisitor(Context context, ViewGroup parent, int amount) {
        this.context = context;
        this.parent = parent;
        this.amount = amount;
        this.view = LayoutInflater.from(this.context).inflate(R.layout.trade_item, parent, false);
    }

    /**
     * @return the view of the item.
     */
    public View getView() {
        return view;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        visitCardOrPack(card);
    }

    @Override
    public void visitItemCard(ItemCard card) {
        visitCardOrPack(card);
    }

    @Override
    public void visitPack(Pack pack) {
        visitCardOrPack(pack);
    }

    @Override
    public void visitCurrency(Currency currency) {
        ItemViewVisitor currencyViewVisitor = new ItemViewVisitor(context, parent, CURRENCY_SCALE_FACTOR);
        currency.accept(currencyViewVisitor);

        LinearLayout itemContainer = view.findViewById(R.id.itemContainer);
        itemContainer.addView(currencyViewVisitor.getView());
    }

    /**
     * Helper function for visiting CritterCard, ItemCard and Pack
     * @param item item to visit
     */
    private void visitCardOrPack(IItem item) {
        ItemViewVisitor itemViewVisitor = new ItemViewVisitor(context, parent, CARD_SCALE_FACTOR);
        item.accept(itemViewVisitor);

        TextView countText = view.findViewById(R.id.countText);
        String countTextString = "x" + amount;
        countText.setText(countTextString);
        countText.setTextColor(context.getResources().getColor(android.R.color.white));

        LinearLayout itemContainer = view.findViewById(R.id.itemContainer);
        itemContainer.addView(itemViewVisitor.getView());
    }
}
