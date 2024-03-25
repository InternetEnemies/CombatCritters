package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

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
        parentLayout.setBackgroundColor(getBackgroundColor(getItem()));
        mysteryView.setScaleX(scaleFactor);
        mysteryView.setScaleY(scaleFactor);
        return mysteryView;
    }

    private int getBackgroundColor(Card.Rarity rarity){
        int color;
        switch (rarity) {
            case COMMON:
                color = ContextCompat.getColor(getContext(), R.color.common);
                break;
            case UNCOMMON:
                color = ContextCompat.getColor(getContext(), R.color.uncommon);
                break;
            case RARE:
                color = ContextCompat.getColor(getContext(), R.color.rare);
                break;
            case EPIC:
                color = ContextCompat.getColor(getContext(), R.color.epic);
                break;
            case LEGENDARY:
                color = ContextCompat.getColor(getContext(), R.color.legendary);
                break;
            default:
                color = ContextCompat.getColor(getContext(), R.color.common);
        }
        return color;
    }
}
