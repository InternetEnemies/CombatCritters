/**
 * ItemCard.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-01-30
 *
 * @PURPOSE:    Properties of Item card
 */

package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.visitor.ICardVisitor;
import com.internetEnemies.combatCritters.Logic.visitor.IItemVisitor;

public class ItemCard extends Card{

    private final int effectId;

    public ItemCard(int id,
                    String name,
                    String image,
                    int playCost,
                    Rarity rarity,
                    int effectId
    ) {
        this(id, name, image, playCost, rarity, "", effectId);
    }
    
    public ItemCard(int id,
                    String name,
                    String image,
                    int playCost,
                    Rarity rarity,
                    String description,
                    int effectId
    ) {
        super(id, name, image, playCost, rarity, description);
        this.effectId = effectId;
    }

    public int getEffectId() {
        return effectId;
    }

    @Override
    public void clone(ICardBuilder builder) {
        builder.setType(CardType.ITEM);
        super.clone(builder);
        builder.setEffect(this.effectId);
    }

    @Override
    public void accept(ICardVisitor visitor) {
        visitor.visitItemCard(this);
    }

    @Override
    public void accept(IItemVisitor visitor) {
        visitor.visitItemCard(this);
    }
}
