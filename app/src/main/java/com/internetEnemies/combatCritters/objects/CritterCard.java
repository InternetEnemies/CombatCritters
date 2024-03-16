/**
 * CritterCard.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-01-30
 *
 * @PURPOSE:    Critter Card class
 */

package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;

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
    public void accept(IItemVisitor visitor){
        visitor.visitCritterCard(this);
    }

    @Override
    public void clone(ICardBuilder builder) {
        builder.setType(CardType.CRITTER);
        super.clone(builder);
        builder.setDamage(this.damage);
        builder.setHealth(this.health);
        if (this.abilities != null) {
            for(int id : this.abilities) {
                builder.addAbility(id);
            }
        }
    }
}
