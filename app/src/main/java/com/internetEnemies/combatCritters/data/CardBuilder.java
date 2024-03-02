package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardType;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ICardBuilder;
import com.internetEnemies.combatCritters.objects.ItemCard;

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
    private List abilityId;

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
    public void addAbility(List abilityId) {
        this.abilityId = abilityId;
    }

    public Card build(){
        Card card;
        switch(this.type){
            case CardType.Critter:
                card = new CritterCard(id, name, image, cost, rarity, damage, health, abilityId);
                break;
            case CardType.Item:
                card = new ItemCard(id, name, image, cost, rarity, effectId);
                break;
            default:
                throw new InvalidCard(); //or something idk lol todo
        }
        return card;
    }
}
