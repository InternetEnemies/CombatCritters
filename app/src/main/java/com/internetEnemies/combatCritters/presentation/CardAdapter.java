package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.List;

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
        Card currentCard = getItem(position);
        CardRenderer cardRenderer = new CardRenderer(currentCard,this.context);
        return cardRenderer.getView(cardView,parent);
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
}