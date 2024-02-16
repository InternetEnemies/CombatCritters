package com.internetEnemies.combatCritters.objects;

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
}
