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


public class DeckBuilderCardAdapter extends BaseAdapter {
    private final Context context;
    private final List<Card> cards;

    public DeckBuilderCardAdapter(Context context, List<Card> cards) {
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
    public View getView(int position, View cardView, ViewGroup parent) {
        cardView = CardAdapterHelper.inflateCard(context, cardView, parent);

        Card currentCard = cards.get(position);

        ImageView imageView = cardView.findViewById(R.id.cardImage);
        TextView cardCostTextView = cardView.findViewById(R.id.cardCost);
        TextView nameTextView = cardView.findViewById(R.id.cardName);
        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);
        TextView effectTextView = cardView.findViewById(R.id.cardEffect);

        CardAdapterHelper.setCardImage(context, imageView, currentCard);
        CardAdapterHelper.setCardText(context, currentCard, nameTextView, cardCostTextView, healthTextView, attackTextView, effectTextView);
        CardAdapterHelper.setCardBackground(context, cardView, false, currentCard);

        return cardView;
    }
}
