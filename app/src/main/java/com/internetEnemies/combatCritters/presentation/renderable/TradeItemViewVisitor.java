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
        ItemViewVisitor currencyViewVisitor = new ItemViewVisitor(context, parent, 1f);
        currency.accept(currencyViewVisitor);
        View currencyView = currencyViewVisitor.getView();
//        TextView currencyTextView = view.findViewById(R.id.currencyTextView);
//        currencyTextView.setTextColor(context.getResources().getColor(android.R.color.black));
        view = currencyView;
//        view.setScaleX(1.5f);
//        view.setScaleY(1.5f);
//        ViewGroup currencyContainer = parent.findViewById(R.id.currencyContainer);
//        currencyContainer.removeAllViews();
//        currencyContainer.addView(currencyView);
//        view = parent;
    }


    private void visitCardOrPack(IItem item) {
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(context)
                .inflate(R.layout.trade_item_container, parent, false);

        ItemViewVisitor itemViewVisitor = new ItemViewVisitor(context, parent);
        item.accept(itemViewVisitor);

        view = itemViewVisitor.getView();
        view.setScaleX(.5f);
        view.setScaleY(.5f);
//        FrameLayout cardContainer = parent.findViewById(R.id.cardContainer);
//        if (cardContainer != null) {
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//            );
//            container.setLayoutParams(layoutParams);
//
//            cardContainer.addView(itemViewVisitor.getView());
//
//            view = container;
//        }
    }

}
