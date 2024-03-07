/**
 * IPackBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-19
 *
 * @PURPOSE:    interface of building pack
 */

package com.internetEnemies.combatCritters.objects;
import java.util.List;

public interface IPackBuilder {
    /**
     * ID setter
     * @param id the integer to be used for the ID
     */
    void setId(int id);

    /**
     * Image setter
     * @param Image the filename of the image without the file extension
     */
    void setImage(String Image);
    /**
     * Name setter
     * @param Name The name of the pack.
     */
    void setName(String Name);
    /**
     * Adds a list of cards to the pack.
     * @param cards A list of possible cards to be obtained within the pack
     */
    void addSetList(List<Card> cards);

    /**
     * Adds a CardSlot to the pack to determine the rarity of card being pulled
     * @param cardSlot An instance of CardSlot
     */
    void addSlot(CardSlot cardSlot);
    /**
     * Creates a new instance of Pack to from class variables.
     * @return Pack object.
     */
    Pack build();
    /**
     * Resets all class instance variables.
     */
    void reset();

}
