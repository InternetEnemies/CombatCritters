package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

public class BattleCard {
    private final CritterCard card;
    private final IHealth health;
    private IBoardRow parent;
    private IBoardRow opposing;
    int slot; // ideally state like this would only be held in one place //todo maybe store the slot in a map in the parent

    public BattleCard(CritterCard card, IHealth health) {
        this.card = card;
        this.health = health;
        this.slot = -1;
        this.parent = null;
        this.opposing = null;
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

    /**
     * move the card to a position in a row with an opposing row
     * @param slot slot card is played at
     * @param parent row this card belongs to
     * @param opposing row this card is against (can be null)
     */
    public void moveTo(int slot, IBoardRow parent, IBoardRow opposing){
        this.slot = slot;
        this.parent = parent;
        this.opposing = opposing;
    }
}
