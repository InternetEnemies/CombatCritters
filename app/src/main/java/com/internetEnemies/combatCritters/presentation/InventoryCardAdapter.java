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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryCardAdapter extends BaseAdapter {
    private Context context;
    private Map<Card, Integer> cardInventory;
    private List<Card> cardList;
    private int selectedPosition;

    public InventoryCardAdapter(Context context, Map<Card, Integer> cardInventory) {
        this.context = context;
        this.cardInventory = cardInventory;
        this.cardList = new ArrayList<>(cardInventory.keySet());
        selectedPosition = -1;
    }

    @Override
    public int getCount() {
        return cardInventory.size();
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
        cardView = CardAdapterHelper.inflateCard(context, cardView, parent);

        Card currentCard = getItem(position);
        Integer quantityOfCard = cardInventory.get(currentCard);

        ImageView imageView = cardView.findViewById(R.id.cardImage);
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);
        TextView nameTextView = cardView.findViewById(R.id.cardName);
        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);
        TextView effectTextView = cardView.findViewById(R.id.cardEffect);
        TextView numCardsTextView = cardView.findViewById(R.id.numCards);

        CardAdapterHelper.setCardImage(context, imageView, currentCard);
        CardAdapterHelper.setCardText(context, currentCard, nameTextView, cardCostTextView, healthTextView, attackTextView, effectTextView);

        numCardsTextView.setText(context.getString(R.string.card_quantity, quantityOfCard));


        if (selectedPosition == position) {
            CardAdapterHelper.setCardBackground(context, cardView, true, currentCard);
        } else {
            CardAdapterHelper.setCardBackground(context, cardView, false, currentCard);
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
