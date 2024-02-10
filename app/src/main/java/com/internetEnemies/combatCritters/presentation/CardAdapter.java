package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CardAdapter extends BaseAdapter {
    protected Context context;
    protected List<Card> cardList;
    private int selectedPosition = -1;

    public CardAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public int getCount() {
        return cardList.size();
    }
    @Override
    public Card getItem(int position) {
        return cardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View cardView, ViewGroup parent) {
        if (cardView == null) {
            cardView = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        }

        Card currentCard = getItem(position);

        ImageView imageView = cardView.findViewById(R.id.cardImage);
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);
        TextView nameTextView = cardView.findViewById(R.id.cardName);
        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);
        TextView effectTextView = cardView.findViewById(R.id.cardEffect);

        setCardImage(imageView, currentCard);
        setCardText(context, currentCard, nameTextView, cardCostTextView, healthTextView, attackTextView, effectTextView);

        if (selectedPosition == position) {
            setCardBackground(context, cardView, true, currentCard);
        } else {
            setCardBackground(context, cardView, false, currentCard);
        }

        return cardView;
    }

    public void updateCards(List<Card> cards) {
        cardList = cards;
        notifyDataSetChanged();
    }

    public void clearSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void highlightBackground(Context context, View cardView) {
        cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.highlight));
    }

    public void clearBackground(Context context, View cardView, Card card) {
        cardView.setBackgroundColor(getBackgroundColour(context, card));
    }

    protected void setCardImage(ImageView imageView, Card card) {
        String imageResourceName = card.getImage();
        int imageResourceId = context.getResources().getIdentifier(imageResourceName, "drawable", context.getPackageName());
        imageView.setImageResource(imageResourceId);
    }

    protected void setCardText(Context context, Card card, TextView nameTextView, TextView cardCostTextView, TextView healthTextView, TextView attackTextView, TextView effectTextView) {
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

    private void setCardBackground(Context context, View cardView, boolean isSelected, Card card) {
        if(isSelected) {
            highlightBackground(context, cardView);
        }
        else {
            clearBackground(context, cardView, card);
        }
    }

    protected int getBackgroundColour(Context context, Card card) {
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