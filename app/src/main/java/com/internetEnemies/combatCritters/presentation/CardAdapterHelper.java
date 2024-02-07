package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

public class CardAdapterHelper {

    public static View inflateCard(Context context, View cardView, ViewGroup parent) {
        if (cardView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cardView = inflater.inflate(R.layout.card, parent, false);
        }
        return cardView;
    }

    public static void setCardImage(Context context, ImageView imageView, Card card) {
        String imageResourceName = card.getImage();
        int imageResourceId = context.getResources().getIdentifier(imageResourceName, "drawable", context.getPackageName());
        imageView.setImageResource(imageResourceId);
    }


    public static void setCardText(Context context, Card card, TextView nameTextView, TextView cardCostTextView, TextView healthTextView, TextView attackTextView, TextView effectTextView) {
        cardCostTextView.setText(context.getString(R.string.card_cost, card.getPlayCost()));
        nameTextView.setText(context.getString(R.string.critter_card, card.getName()));
        if (card instanceof CritterCard) {
            CritterCard critterCard = (CritterCard) card;
            effectTextView.setVisibility(View.GONE);
            healthTextView.setText(context.getString(R.string.card_health, critterCard.getHealth()));
            attackTextView.setText(context.getString(R.string.card_attack, critterCard.getDamage()));
        } else if (card instanceof ItemCard) {
            ItemCard itemCard = (ItemCard) card;
            healthTextView.setVisibility(View.GONE);
            attackTextView.setVisibility(View.GONE);
            effectTextView.setText(context.getString(R.string.card_effect, itemCard.getEffectId()));
        }
    }

    public static void setCardBackground(Context context, View cardView, boolean isSelected, Card card) {
        if(isSelected) {
            highlightBackground(context, cardView);
        }
        else {
            clearBackground(context, cardView, card);
        }
    }

    public static void highlightBackground(Context context, View cardView) {
        cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.highlight));
    }

    public static void clearBackground(Context context, View cardView, Card card) {
        cardView.setBackgroundColor(getBackgroundColour(context, card));
    }

    private static int getBackgroundColour(Context context, Card card) {
        if(card.getRarity() == Card.Rarity.COMMON) {
            return ContextCompat.getColor(context, R.color.common);
        }
        else if(card.getRarity() == Card.Rarity.UNCOMMON) {
            return ContextCompat.getColor(context, R.color.uncommon);
        }
        else if(card.getRarity() == Card.Rarity.RARE) {
            return ContextCompat.getColor(context, R.color.rare);
        }
        else if(card.getRarity() == Card.Rarity.EPIC) {
            return ContextCompat.getColor(context, R.color.epic);
        }
        else {
            return ContextCompat.getColor(context, R.color.legendary);
        }
    }
}
