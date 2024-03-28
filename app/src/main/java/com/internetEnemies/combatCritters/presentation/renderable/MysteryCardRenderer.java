package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

/**
 * MysteryCardRenderer.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     25/March/24
 *
 * @PURPOSE:    provide a View object for a mystery_card
 */
public class MysteryCardRenderer extends ItemRenderer<Card.Rarity>{
    private final float scaleFactor;
    public MysteryCardRenderer(Card.Rarity item, Context context) {this(item, context, 1);}

    public MysteryCardRenderer(Card.Rarity item, Context context, float scaleFactor) {
        super(item, context);
        this.scaleFactor = scaleFactor;
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        View mysteryView = LayoutInflater.from(getContext()).inflate(R.layout.mystery_card, parent, false);
        ConstraintLayout parentLayout = mysteryView.findViewById(R.id.cardContainer);
        parentLayout.setBackgroundColor(CardViewBuilder.getBackgroundColor(getItem(), getContext()));
        mysteryView.setScaleX(scaleFactor);
        mysteryView.setScaleY(scaleFactor);
        return mysteryView;
    }
}
