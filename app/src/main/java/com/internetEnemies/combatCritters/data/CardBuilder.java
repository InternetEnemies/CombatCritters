package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardType;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ICardBuilder;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.List;

public class CardBuilder implements ICardBuilder {
    private Card.Rarity rarity;
    private int id;
    private String name;
    private String image;
    private int cost;
    private CardType type;
    private int effectId;
    private int damage;
    private int health;
    private int abilityId;

    @Override
    public void setRarity(Card.Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name; 
    }

    @Override
    public void setImage(String image) {
        this.image = image; 
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public void setEffect(int id) {
        this.effectId = id; 
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void addAbility(int abilityId) {
        this.abilityId = abilityId;
    }

    public Card build() throws InvalidCardException{
        List<Integer> abilities = new ArrayList<>();
        abilities.add(abilityId);
        Card card;
        switch(this.type){
            case Critter:
                card = new CritterCard(id, name, image, cost, rarity, damage, health, abilities);
                break;
            case Item:
                card = new ItemCard(id, name, image, cost, rarity, effectId);
                break;
            default:
                throw new InvalidCardException();
        }
        return card;
    }

    public class InvalidCardException extends Exception {
        public InvalidCardException() {
            super();
        }

        public InvalidCardException(String message) {
            super(message);
        }
    }
}
