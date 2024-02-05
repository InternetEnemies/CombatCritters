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


public class CardAdapter extends BaseAdapter {
    private final Context context;
    private final List<Card> cards;

    private int selectedCard = -1;

    public CardAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Card getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cardView = convertView;
        if (cardView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cardView = inflater.inflate(R.layout.card, parent, false);
        }
        Card currentCard = cards.get(position);

        ImageView imageView = cardView.findViewById(R.id.cardImage);

        TextView nameTextView = cardView.findViewById(R.id.cardName);
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);
        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);
        TextView effectTextView = cardView.findViewById(R.id.cardEffect);

        imageView.setImageResource(R.drawable.card1);
        cardCostTextView.setText("Cost: " + currentCard.getPlayCost());
        cardView.setBackgroundColor(getBackgroundColour(currentCard));

        if(currentCard instanceof CritterCard) {
            CritterCard currentCritterCard = (CritterCard)currentCard;

            effectTextView.setVisibility(View.GONE);
            nameTextView.setText("Critter: " + currentCard.getName());
            healthTextView.setText("Health" + currentCritterCard.getHealth());
            attackTextView.setText("Attack: " + String.valueOf(currentCritterCard.getDamage()));
        }
        else {
            ItemCard currentItemCard = (ItemCard)currentCard;

            healthTextView.setVisibility(View.GONE);
            attackTextView.setVisibility(View.GONE);
            nameTextView.setText("Item: " + currentCard.getName());
            effectTextView.setText("Effect: " + currentItemCard.getEffectId());
        }


        if (selectedCard == position) {
            // This card is selected
            cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.legendary));
        } else {
            // This card is not selected
            cardView.setBackgroundColor(getBackgroundColour(currentCard));
        }

        return cardView;
    }

    public void setSelectedCard(int selection) {
        selectedCard = selection;
    }


    public void clearSelection() {
        if (selectedCard != -1) {
            selectedCard = -1;
            notifyDataSetChanged();
        }
    }

    private int getBackgroundColour(Card card) {
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
