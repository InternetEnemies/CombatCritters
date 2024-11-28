package com.combatcritters.critterspring.battle.battle;

import com.combatcritters.critterspring.battle.payloads.commands.EndTurnCommand;
import com.combatcritters.critterspring.battle.payloads.commands.PlayCardCommand;
import com.combatcritters.critterspring.battle.payloads.commands.SacrificeCommand;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.battle.request.CritterController;
import com.combatcritters.critterspring.battle.request.CritterRequest;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.objects.Card;

@CritterController
public class BattleCommandController {
    private final ICardRegistry cardRegistry;
    public BattleCommandController(ICardRegistry cardRegistry) {
        this.cardRegistry = cardRegistry;
    }
    @CritterRequest("end_turn_command")
    public void handleEndTurn(IPlayerSession session, EndTurnCommand request) throws BattleInputException {
        session.getPlayer().getOrchestrator().endTurn();
    }

    @CritterRequest("sacrifice_command")
    public void handleSacrifice(IPlayerSession session, SacrificeCommand request) throws BattleInputException {
        session.getPlayer().getOrchestrator().sacrifice(request.pos());
    }

    @CritterRequest("play_card_command")
    public void handlePlayCard(IPlayerSession session, PlayCardCommand request) throws BattleInputException {
        Card card = cardRegistry.getCard(request.id());
        session.getPlayer().getOrchestrator().playCard(request.pos(), card);
    }
}
