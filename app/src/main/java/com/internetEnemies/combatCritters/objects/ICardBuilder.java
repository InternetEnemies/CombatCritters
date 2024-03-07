package com.internetEnemies.combatCritters.objects;

import java.util.List;

/**
 * ICardBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    Interface for building Card objects
 */
public interface ICardBuilder {
    /**
     * set the rarity of the card
     * @param rarity rarity to set to
     */
    void setRarity(Card.Rarity rarity);

    /**
     * set the id of the card
     * @param id id to set to
     */
    void setId(int id);

    /**
     * set the name of the card
     * @param name name of the card
     */
    void setName(String name);

    /**
     * set the image for the card
     * @param image image reference
     */
    void setImage(String image);

    /**
     * set the play cost of the card
     * @param cost cost of playing the card
     */
    void setCost(int cost);

    void setType(CardType type);
    /**
     * set the effect id of the card
     * only for ItemCard
     * @param id id of the effect
     */
    void setEffect(int id);

    /**
     * set the damage of the card
     * @param damage damage of the card
     */
    void setDamage(int damage);

    /**
     * set the health of the card
     * @param health health to set
     */
    void setHealth(int health);

    /**
     * add an ability to the card
     * @param abilityId if of the ability to add
     */
    void addAbility(int abilityId);


}
