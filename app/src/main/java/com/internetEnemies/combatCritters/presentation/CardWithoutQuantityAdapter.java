package com.internetEnemies.combatCritters.presentation;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;


public class CardWithoutQuantityAdapter extends BaseAdapter {
    private final Context context;
    private final List<Card> cards;

    private int selectedPosition;

    public CardWithoutQuantityAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
        selectedPosition = -1;
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
    public View getView(int position, View cardView, ViewGroup parent) {
        cardView = CardAdapter.inflateCard(context, cardView, parent);

        Card currentCard = cards.get(position);

        ImageView imageView = cardView.findViewById(R.id.cardImage);
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);
        TextView nameTextView = cardView.findViewById(R.id.cardName);
        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);
        TextView effectTextView = cardView.findViewById(R.id.cardEffect);

        CardAdapter.setCardImage(context, imageView, currentCard);
        CardAdapter.setCardText(context, currentCard, nameTextView, cardCostTextView, healthTextView, attackTextView, effectTextView);
        CardAdapter.setCardBackground(context, cardView, false, currentCard);

        if (selectedPosition == position) {
            CardAdapter.setCardBackground(context, cardView, true, currentCard);
        } else {
            CardAdapter.setCardBackground(context, cardView, false, currentCard);
        }

        return cardView;
    }

    public void clearSelection() {
        if (selectedPosition != -1) {
            selectedPosition = -1;
            notifyDataSetChanged();
        }
    }

    public void setSelectedCard(int selection) {
        selectedPosition = selection;
    }
}
