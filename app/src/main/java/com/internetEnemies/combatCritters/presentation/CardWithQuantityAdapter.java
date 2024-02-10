package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.CardUtils;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
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

//    @Override
//    public View getView(int position, View cardView, ViewGroup parent) {
//        cardView = super.getView(position, cardView, parent);
//
//        Card currentCard = getItem(position);
//        Integer quantityOfCard = cardInventoryMap.get(currentCard);
//        TextView numCardsTextView = cardView.findViewById(R.id.numCards);
//        numCardsTextView.setText(context.getString(R.string.card_quantity, quantityOfCard));
//
//        return cardView;
//    }

    @Override
    protected void bindView(View view, Card card) {
        super.bindView(view, card);

        TextView quantityTextView = view.findViewById(R.id.numCards);
        Integer quantity = cardInventoryMap.get(card);
        if (quantity != null) {
            quantityTextView.setText(String.valueOf(quantity));
        }
    }




    @Override
    public void updateItems(List<Card> cardList) {
//        Log.d("here", "here");
        CardCatalog cardCatalog = new CardCatalog();

        cardInventoryMap = cardCatalog.getAll();
        items = cardList;
        notifyDataSetChanged();
    }
}
