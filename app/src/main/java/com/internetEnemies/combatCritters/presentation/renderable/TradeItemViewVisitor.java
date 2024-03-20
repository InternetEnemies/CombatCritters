package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class TradeItemViewVisitor implements IItemVisitor {
    private final Context context;
    private View view;
    private final ViewGroup parent;
    private final int amount;
    private static final float CURRENCY_SCALE_FACTOR = 1.5f;
    private static final float CARD_SCALE_FACTOR = .5f;

    public TradeItemViewVisitor(Context context, ViewGroup parent, int amount) {
        this.context = context;
        this.parent = parent;
        this.amount = amount;
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
        ItemViewVisitor currencyViewVisitor = new ItemViewVisitor(context, parent, 1f);
        currency.accept(currencyViewVisitor);

        view = currencyViewVisitor.getView();

        view.setScaleX(CURRENCY_SCALE_FACTOR);
        view.setScaleY(CURRENCY_SCALE_FACTOR);
    }

    /**
     * Helper function for visiting CritterCard, ItemCard and Pack
     * @param item item to visit
     */
    private void visitCardOrPack(IItem item) {
        ItemViewVisitor itemViewVisitor = new ItemViewVisitor(context, parent);
        item.accept(itemViewVisitor);

        TextView countText = parent.findViewById(R.id.countText);
        String countTextString = "x" + String.valueOf(amount);
        countText.setText(countTextString);
        countText.setTextColor(context.getResources().getColor(android.R.color.white));

        view = itemViewVisitor.getView();

        view.setScaleX(CARD_SCALE_FACTOR);
        view.setScaleY(CARD_SCALE_FACTOR);
    }
}
