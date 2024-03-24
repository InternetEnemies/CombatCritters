package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

public class BattleCard {
    private final CritterCard card;
    private final IHealth health;

    public BattleCard(CritterCard card, IHealth health) {
        this.card = card;
        this.health = health;
    }
    public BattleCard(CritterCard card) {
        this(
                card,
                new Health(card.getHealth(),card.getHealth())
        );
    }

    public CardState getCardState() {
        return new CardState(health.getHealth(), card);
    }

    public IHealth getHealth() {
        return this.health;
    }
}
