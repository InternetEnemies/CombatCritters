package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CardWithQuantityAdapter extends CardAdapter {
    private Map<Card, Integer> cardInventoryMap;

    public CardWithQuantityAdapter(Context context, Map<Card, Integer> cardInventoryMap) {
        super(context, new ArrayList<>(cardInventoryMap.keySet()));
        this.cardInventoryMap = cardInventoryMap;
    }

    @Override
    public int getCount() {
        return cardInventoryMap.size();
    }

    @Override
    public View getView(int position, View cardView, ViewGroup parent) {
        cardView = super.getView(position, cardView, parent);

        Card currentCard = getItem(position);
        Integer quantityOfCard = cardInventoryMap.get(currentCard);
        TextView numCardsTextView = cardView.findViewById(R.id.numCards);
        numCardsTextView.setText(context.getString(R.string.card_quantity, quantityOfCard));

        return cardView;
    }

    public void updateCards(Map<Card, Integer> newCardMap) {
        this.cardInventoryMap = newCardMap;
//        this.cardList = new ArrayList<>(newCardMap.keySet());
        notifyDataSetChanged();
    }
}
