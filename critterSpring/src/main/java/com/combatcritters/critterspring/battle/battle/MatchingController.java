package com.combatcritters.critterspring.battle.battle;

import com.combatcritters.critterspring.battle.payloads.matching.CancelMatchCommand;
import com.combatcritters.critterspring.battle.payloads.matching.MatchCommand;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
import com.combatcritters.critterspring.battle.request.CritterController;
import com.combatcritters.critterspring.battle.request.CritterRequest;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchmakingService;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckManager;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckManagerFactory;
import com.internetEnemies.combatCritters.data.exception.NXDeckException;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

@CritterController
public class MatchingController {
    
    private final IMatchmakingService matchmakingService;
    private final IPlayerSessionManager playerSessionManager;
    private final IDeckManagerFactory deckManagerFactory;
    
    public MatchingController(IMatchmakingService matchmakingService, IPlayerSessionManager playerSessionManager, IDeckManagerFactory deckManagerFactory) {

        this.matchmakingService = matchmakingService;
        this.playerSessionManager = playerSessionManager;
        this.deckManagerFactory = deckManagerFactory;
    }
    
    @CritterRequest("match_command")
    public void handleMatch(IPlayerSession session, MatchCommand payload) throws BattleInputException {
        //todo multi matchmaking types
        
        // get deck / check valid (error if invalid)
        List<Card> deck;
        try {
            deck = getDeckCards(payload.deckid(), session.getPlayer().getUser());
        } catch (NXDeckException e) {
            throw new BattleInputException("Deck doesn't exist");
        }
        // start match with deck
        session.getPlayer().setDeck(deck);
        matchmakingService.matchMake(session.getPlayer()); 
    }
    
    @CritterRequest("cancel_match_command")
    public void handleCancelMatch(IPlayerSession session, CancelMatchCommand payload) {
        playerSessionManager.closePlayerSession(session.getPlayer().getUser());
    }
    
    private List<Card> getDeckCards(int deckid, User user) throws BattleInputException {
        IDeckManager deckManager = deckManagerFactory.create(user);
        DeckDetails details = deckManager.getDeckDetails(deckid);
        
        IDeckBuilder builder = deckManager.getBuilder(details);
        if(!builder.validate().isValid()) {
            throw new BattleInputException("Deck is not a valid deck");
        }
        
        return  builder.getCards();
    }
}
