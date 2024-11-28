package com.combatcritters.critterspring.battle.battle;

import com.combatcritters.critterspring.battle.payloads.commands.EndTurnCommand;
import com.combatcritters.critterspring.battle.request.CritterController;
import com.combatcritters.critterspring.battle.request.CritterRequest;

@CritterController
public class BattleCommandController {
    public BattleCommandController(){

    }
    @CritterRequest("end_turn_command")
    public void handleEndTurn(EndTurnCommand request) {
        System.out.println("YEPYEPYEPYEPYEPYEPYEPYEPYEPYEP");
    }
}
