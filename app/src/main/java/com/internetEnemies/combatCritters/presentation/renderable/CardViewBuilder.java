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

/**
 * CardViewBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    Builder for Card UI components
 */
public class CardViewBuilder implements ICardBuilder {
    private final View cardView;
    private final Context context;

    public CardViewBuilder(Context context, ViewGroup parent){
        this.context = context;
        this.cardView = LayoutInflater.from(this.context).inflate(R.layout.card, parent, false);
    }

    /**
     * get the UI View for the card built by this builder
     * @return View for the card
     */
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
        setText(R.id.cardName,context.getString(R.string.critter_card, name));
    }

    @Override
    public void setImage(String image) {
        int imageResourceId = context.getResources().getIdentifier(image,"drawable",context.getPackageName());
        ImageView cardImage = cardView.findViewById(R.id.cardImage);
        cardImage.setImageResource(imageResourceId);
    }

    @Override
    public void setCost(int cost) {
        setText(R.id.cardCost, context.getString(R.string.card_cost, cost));
    }

    @Override
    public void setType(CardType type) {
        //TODO
    }

    @Override
    public void setEffect(int id) {
        setText(R.id.cardEffect, context.getString(R.string.card_effect, id));
    }

    @Override
    public void setDamage(int damage) {
        setText(R.id.cardAttack, context.getString(R.string.card_attack, damage));
    }

    @Override
    public void setHealth(int health) {
        setText(R.id.cardHealth, context.getString(R.string.card_health, health));
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

    /**
     * helper function for getting the related color for a card from its rarity
     * @param rarity rarity of the card
     * @return color for the card
     */
    private int getBackgroundColor(Card.Rarity rarity){
        int color;
        switch (rarity) {
            case COMMON:
                color = ContextCompat.getColor(context, R.color.common);
                break;
            case UNCOMMON:
                color = ContextCompat.getColor(context, R.color.uncommon);
                break;
            case RARE:
                color = ContextCompat.getColor(context, R.color.rare);
                break;
            case EPIC:
                color = ContextCompat.getColor(context, R.color.epic);
                break;
            case LEGENDARY:
                color = ContextCompat.getColor(context, R.color.legendary);
                break;
            default:
                color = ContextCompat.getColor(context, R.color.common);
        }
        return color;
    }
}