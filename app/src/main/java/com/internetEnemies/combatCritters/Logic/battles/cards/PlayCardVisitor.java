package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.ICardVisitor;
import com.internetEnemies.combatCritters.Logic.battles.IBattle;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

public class PlayCardVisitor implements ICardVisitor {
    private final int pos;
    private final IBattle battle;
    private final IEventSystem eventSystem;
    private CardAction action;
    public PlayCardVisitor(IEventSystem eventSystem,int pos, IBattle battle){
        this.eventSystem = eventSystem;
        this.pos = pos;
        this.battle = battle;
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        // playCard throws a checked exception so we have to move the playCard call to another function so it can be handled elsewhere
        this.action = () -> battle.getBoard().getPlayer().playCard(this.pos, new BattleCard(eventSystem,card));
    }

    @Override
    public void visitItemCard(ItemCard card) {
        //todo
        // should execute the effect of the item card at the position
        // this will involve getting the effect from the effect registry (and thus adding the effect registry)
    }

    public void execute() throws BattleException {
        action.exec();
    }
}

interface CardAction {
    void exec() throws BattleException;
}
