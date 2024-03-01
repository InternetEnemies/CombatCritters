package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class CritterCard extends Card{
    private final int damage;
    private final int health;
    private final List<Integer> abilities;

    public CritterCard(int id,
                       String name,
                       String image,
                       int playCost,
                       Rarity rarity,
                       int damage,
                       int health,
                       List<Integer> abilities
    ) {
        super(id, name, image, playCost, rarity);
        this.damage = damage;
        this.health = health;
        this.abilities = abilities;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public List<Integer> getAbilities() {
        return abilities;
    }

    @Override
    public void clone(ICardBuilder builder) {
        super.clone(builder);
        builder.setType(CardType.CRITTER);
        builder.setDamage(this.damage);
        builder.setHealth(this.health);
        if (this.abilities != null) {
            for(int id : this.abilities) {
                builder.addAbility(id);
            }
        }
    }

}
