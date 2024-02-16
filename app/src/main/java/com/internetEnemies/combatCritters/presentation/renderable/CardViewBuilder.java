package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardType;
import com.internetEnemies.combatCritters.objects.ICardBuilder;

public class CardViewBuilder implements ICardBuilder {
    private final View cardView;
    private final Context context;

    public CardViewBuilder(Context context, ViewGroup parent){
        this.context = context;
        this.cardView = LayoutInflater.from(this.context).inflate(R.layout.card, parent, false);
    }

    public View getCardView() {
        return cardView;
    }

    @Override
    public void setRarity(Card.Rarity rarity) {
        cardView.setBackgroundColor(getBackgroundColor(rarity));
    }

    @Override
    public void setId(int id) {
        //* Not sure if this will be needed be needed for this builder (no ui element displaying the id)
    }

    @Override
    public void setName(String name) {
        setText(R.id.cardName,name);
    }

    @Override
    public void setImage(String image) {
        int imageResourceId = context.getResources().getIdentifier(image,"drawable",context.getPackageName());//? is there a way to fix the warning here?
        ImageView cardImage = cardView.findViewById(R.id.cardImage);
        cardImage.setImageResource(imageResourceId);
    }

    @Override
    public void setCost(int cost) {
        setText(R.id.cardCost, String.valueOf(cost));
    }

    @Override
    public void setType(CardType type) {
        //TODO
    }

    @Override
    public void setEffect(int id) {
        setText(R.id.cardEffect, String.valueOf(id));
    }

    @Override
    public void setDamage(int damage) {
        setText(R.id.cardAttack, String.valueOf(damage));
    }

    @Override
    public void setHealth(int health) {
        setText(R.id.cardHealth,String.valueOf(health));
    }

    @Override
    public void addAbility(int abilityId) {
        //TODO
    }

    /**
     * set the text of a TextView given the id and a text to set it to
     * @param id id of TextView
     * @param text text to set to
     */
    private void setText(int id, String text) {
        TextView view = cardView.findViewById(id);
        view.setText(text);
    }
    private int getBackgroundColor(Card.Rarity rarity){
        int color;
        switch (rarity) {
            case COMMON:
                color = ContextCompat.getColor(context, R.color.common);
            case UNCOMMON:
                color = ContextCompat.getColor(context, R.color.uncommon);
            case RARE:
                color = ContextCompat.getColor(context, R.color.rare);
            case EPIC:
                color = ContextCompat.getColor(context, R.color.epic);
            case LEGENDARY:
                color = ContextCompat.getColor(context, R.color.legendary);
            default:
                color = ContextCompat.getColor(context, R.color.common);
        }
        return color;
    }
}
