package com.combatcritters.critterspring.battle.battle;

import com.combatcritters.critterspring.battle.payloads.matching.CancelMatchCommand;
import com.combatcritters.critterspring.battle.payloads.matching.MatchCommand;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
import com.combatcritters.critterspring.battle.request.CritterController;
import com.combatcritters.critterspring.battle.request.CritterRequest;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchmakingService;

@CritterController
public class MatchingController {
    
    private final IMatchmakingService matchmakingService;
    private final IPlayerSessionManager playerSessionManager;
    
    public MatchingController(IMatchmakingService matchmakingService, IPlayerSessionManager playerSessionManager) {

        this.matchmakingService = matchmakingService;
        this.playerSessionManager = playerSessionManager;
    }
    
    @CritterRequest("match_command")
    public void handleMatch(IPlayerSession session, MatchCommand payload) {
        //todo multi matchmaking types
        matchmakingService.matchMake(session.getPlayer());
    }
    
    @CritterRequest("cancel_match_command")
    public void handleCancelMatch(IPlayerSession session, CancelMatchCommand payload) {
        playerSessionManager.closePlayerSession(session.getPlayer().getUser());
    }
}
