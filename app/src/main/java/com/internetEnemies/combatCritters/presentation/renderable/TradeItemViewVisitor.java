package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.ClipData;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
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
    private static final float CURRENCY_SCALE_FACTOR = 3f;
    private final float CARD_SCALE_FACTOR = .5f;

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
//        parent.setScaleX(CURRENCY_SCALE_FACTOR);
//        parent.setScaleY(CURRENCY_SCALE_FACTOR);
        ItemViewVisitor currencyViewVisitor = new ItemViewVisitor(context, parent);
        currency.accept(currencyViewVisitor);
        view = currencyViewVisitor.getView();
        TextView currencyTextView = view.findViewById(R.id.currencyTextView);
        currencyTextView.setTextColor(context.getResources().getColor(android.R.color.black));
    }


    private void visitCardOrPack(IItem item) {
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.trade_item_card_container,parent,false);
        ItemViewVisitor itemViewVisitor = new ItemViewVisitor(context, parent);
        item.accept(itemViewVisitor);

        FrameLayout cardContainer = container.findViewById(R.id.item_container);
        cardContainer.addView(itemViewVisitor.getView());


        TextView count = container.findViewById(R.id.info_text);
        count.setText(String.valueOf(amount));
        count.setTextColor(context.getResources().getColor(android.R.color.black));

//        container.setScaleX(CARD_SCALE_FACTOR);
//        container.setScaleY(CARD_SCALE_FACTOR);
//        cardContainer.setScaleX(CARD_SCALE_FACTOR);
//        cardContainer.setScaleY(CARD_SCALE_FACTOR);

//        count.setScaleX(3f);
//        count.setScaleY(3f);

//        parent.setScaleX(CARD_SCALE_FACTOR);
//        parent.setScaleY(CARD_SCALE_FACTOR);
        view = container;
    }
}
