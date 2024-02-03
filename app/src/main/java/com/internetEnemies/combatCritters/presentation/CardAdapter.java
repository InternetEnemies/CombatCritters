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
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);

        imageView.setImageResource(R.drawable.card1);
        cardCostTextView.setText("Cost: " + currentCard.getPlayCost());
        cardView.setBackgroundColor(getBackgroundColour(currentCard));

        if(currentCard instanceof CritterCard) {
            CritterCard currentCritterCard = (CritterCard)currentCard;

            TextView nameTextView = cardView.findViewById(R.id.cardName);
            TextView healthTextView = cardView.findViewById(R.id.cardHealth);
            TextView attackTextView = cardView.findViewById(R.id.cardAttack);

            nameTextView.setText("Critter: " + currentCard.getName());
            healthTextView.setText(String.valueOf(currentCard.getName()));
            attackTextView.setText("Attack: " + String.valueOf(currentCritterCard.getDamage()));
        }
        else {
            ItemCard currentItemCard = (ItemCard)currentCard;

            TextView nameTextView = cardView.findViewById(R.id.cardName);
            TextView effectTextView = cardView.findViewById(R.id.cardEffect);

            nameTextView.setText("Item: " + currentCard.getName());
            effectTextView.setText("Effect: " + currentItemCard.getEffectId());
        }
        return cardView;
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
