package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private final float scaleFactor;
    private static final float EPSILON = .00001f;

    public ItemViewVisitor(Context context, ViewGroup parent) {
        this(context, parent, 1.0f);
    }

    public ItemViewVisitor(Context context, ViewGroup parent, float scaleFactor) {
        this.context = context;
        this.parent = parent;
        this.scaleFactor = scaleFactor;
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
        scaleView();
    }

    @Override
    public void visitPack(Pack pack) {
        View packView = LayoutInflater.from(this.context).inflate(R.layout.pack, parent, false);

        TextView packText = packView.findViewById(R.id.packName);
        packText.setText(pack.getName());

        ImageView packImage = packView.findViewById(R.id.packImage);
        int imageResourceId = context.getResources().getIdentifier(pack.getImage(),"drawable",context.getPackageName());
        packImage.setImageResource(imageResourceId);
        view = packView;
        scaleView();
    }

    @Override
    public void visitCurrency(Currency currency) {
        View currencyView = LayoutInflater.from(this.context).inflate(R.layout.currency, parent, false);
        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setText(String.valueOf(currency.getAmount()));
        view = currencyView;
        scaleView();
    }

    /**
     * Scales the view by scaleFactor.
     */
    private void scaleView() {
        //If scaleFactor == 1.0f there is no need to do anything.
        if (Math.abs(scaleFactor - 1.0f) >= EPSILON) {
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
    }
}
