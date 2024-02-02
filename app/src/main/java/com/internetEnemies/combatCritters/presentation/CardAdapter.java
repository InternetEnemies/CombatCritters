package com.internetEnemies.combatCritters.presentation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.List;

public class CardAdapter extends BaseAdapter {
    private Context context;
    private List<CritterCard> cards;

    public CardAdapter(Context context, List<CritterCard> cards) {
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
        // If CritterCard has a unique ID, return that
        // For example, assuming CritterCard has a method getId() that returns a unique long ID:
        // return cards.get(position).getId();

        // If there's no unique ID, you can just return the position as the ID
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cardView = convertView;
        if (cardView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cardView = inflater.inflate(R.layout.card, parent, false);
        }

        TextView healthTextView = cardView.findViewById(R.id.cardHealth);
        TextView attackTextView = cardView.findViewById(R.id.cardAttack);

        Card currentCard = cards.get(position);
        healthTextView.setText(String.valueOf(currentCard.getName()));
        attackTextView.setText(String.valueOf(currentCard.getRarity()));

        return cardView;
    }
}
