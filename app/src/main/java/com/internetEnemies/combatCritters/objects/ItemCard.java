package com.internetEnemies.combatCritters.objects;

public class ItemCard extends Card{
    private final int effectId;

    public ItemCard(int id,
                    String name,
                    String image,
                    int playCost,
                    Rarity rarity,
                    int effectId // TODO this should probably be a class
    ) {
        super(id, name, image, playCost, rarity);
        this.effectId = effectId;
    }
}
