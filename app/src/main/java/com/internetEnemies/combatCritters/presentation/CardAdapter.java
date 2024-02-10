package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.List;

public abstract class CardAdapter extends GenericAdapter<Card> {
    public CardAdapter(Context context, List<Card> cardList) {
        super(context, cardList);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.card;
    }

    @Override
    protected void bindView(View cardView, Card card) {
        ImageView imageView = cardView.findViewById(R.id.cardImage);
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);
        TextView nameTextView = cardView.findViewById(R.id.cardName);
        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);
        TextView effectTextView = cardView.findViewById(R.id.cardEffect);

        setCardImage(imageView, card);
        setCardText(context, card, nameTextView, cardCostTextView, healthTextView, attackTextView, effectTextView);
        cardView.setBackgroundColor(getBackgroundColour(context, card));
    }

    @Override
    protected void highlightView(View cardView, boolean isSelected, int position) {
        if (isSelected) {
            cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.highlight));
        } else {
            cardView.setBackgroundColor(getBackgroundColour(context, getItem(position)));
        }
    }

    private void setCardImage(ImageView imageView, Card card) {
        String imageResourceName = card.getImage();
        int imageResourceId = context.getResources().getIdentifier(imageResourceName, "drawable", context.getPackageName());
        imageView.setImageResource(imageResourceId);
    }

    private void setCardText(Context context, Card card, TextView nameTextView, TextView cardCostTextView, TextView healthTextView, TextView attackTextView, TextView effectTextView) {
        cardCostTextView.setText(context.getString(R.string.card_cost, card.getPlayCost()));
        if (card instanceof CritterCard) {
            CritterCard critterCard = (CritterCard) card;
            effectTextView.setVisibility(View.GONE);
            nameTextView.setText(context.getString(R.string.critter_card, card.getName()));
            healthTextView.setText(context.getString(R.string.card_health, critterCard.getHealth()));
            attackTextView.setText(context.getString(R.string.card_attack, critterCard.getDamage()));
        } else if (card instanceof ItemCard) {
            ItemCard itemCard = (ItemCard) card;
            healthTextView.setVisibility(View.GONE);
            attackTextView.setVisibility(View.GONE);
            nameTextView.setText(context.getString(R.string.item_card, card.getName()));
            effectTextView.setText(context.getString(R.string.card_effect, itemCard.getEffectId()));
        }
    }

    private int getBackgroundColour(Context context, Card card) {
        if (card.getRarity() == Card.Rarity.COMMON) {
            return ContextCompat.getColor(context, R.color.common);
        } else if (card.getRarity() == Card.Rarity.UNCOMMON) {
            return ContextCompat.getColor(context, R.color.uncommon);
        } else if (card.getRarity() == Card.Rarity.RARE) {
            return ContextCompat.getColor(context, R.color.rare);
        } else if (card.getRarity() == Card.Rarity.EPIC) {
            return ContextCompat.getColor(context, R.color.epic);
        } else {
            return ContextCompat.getColor(context, R.color.legendary);
        }
    }
}