package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.ICardVisitor;
import com.internetEnemies.combatCritters.Logic.battles.IBattle;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

/**
 * PlayCardVisitor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    play a card
 */
public class PlayCardVisitor implements ICardVisitor {
    private final int pos;
    private final IBattle battle;
    private final IBattleCardFactory cardFactory;
    private CardAction action;
    public PlayCardVisitor(IBattleCardFactory cardFactory,int pos, IBattle battle){
        this.cardFactory = cardFactory;
        this.pos = pos;
        this.battle = battle;
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        // playCard throws a checked exception so we have to move the playCard call to another function so it can be handled elsewhere
        this.action = () -> battle.getBoard().getPlayer().playCard(this.pos, this.cardFactory.getCard(card));
    }

    @Override
    public void visitItemCard(ItemCard card) {
        throw new UnsupportedOperationException("Items currently not supported in battles");// !this should change before release (rip if it doesn't)
    }

    /**
     * execute the playCard operation
     * @throws BattleException thrown if there is an error playing the card
     */
    public void execute() throws BattleException {
        action.exec();
    }
}

interface CardAction {
    void exec() throws BattleException;
}
