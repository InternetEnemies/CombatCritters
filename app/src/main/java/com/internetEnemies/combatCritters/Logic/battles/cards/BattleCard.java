package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

public class BattleCard implements IBattleCard {
    private final IEventSystem events; // todo when abilities are added we need to clear any listeners a card creates
    private final CritterCard card;
    private final IHealth health;
    private IBoardRow parent;
    private IBoardRow opposing;
    int slot; // ideally state like this would only be held in one place //todo maybe store the slot in a map in the parent

    public BattleCard(IEventSystem events, CritterCard card, IHealth health) {
        this.events = events;
        this.card = card;
        this.health = health;
        this.slot = -1;
        this.parent = null;
        this.opposing = null;
    }
    public BattleCard(IEventSystem events, CritterCard card) {
        this(
                events,
                card,
                new Health(card.getHealth(),card.getHealth())
        );
    }

    @Override
    public CardState getCardState() {
        return new CardState(health.getHealth(), card);
    }

    @Override
    public IHealth getHealth() {
        return this.health;
    }

    @Override
    public void moveTo(int slot, IBoardRow parent, IBoardRow opposing){
        this.slot = slot;
        this.parent = parent;
        this.opposing = opposing;
    }
}
