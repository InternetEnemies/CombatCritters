package com.internetEnemies.combatCritters.LogicUnitTests.battles;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Energy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.ArrayList;
import java.util.List;

/**
 * BattleTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-28
 *
 * @PURPOSE:    some tests for battles, admittedly not that comprehensive but the functionality of this class is rather broad despite the small interface surface
 */
public class BattleTest {
    IBattle battle;
    IBattleOrchestrator orchestrator;
    IEventSystem eventSystem;
    IBattleStateObserver uiProvider;
    IBattleOpponent opponent;
    IEnergy energy;
    IBoard board;
    EventFlag flag;
    Card card;
    @Before
    public void setup(){
        this.eventSystem = new EventSystem();
        uiProvider = mock(IBattleStateObserver.class);
        this.opponent = mock(IBattleOpponent.class);
        this.energy = new Energy(5,1);
        card = new CritterCard(0,"","",1, Card.Rarity.COMMON,1,1,new ArrayList<>());
        List<Card> deck = new ArrayList<>();
        deck.add(card);
        deck.add(card);
        deck.add(card);
        BoardRow buffer = new BoardRow(eventSystem,null,5,new IBattleCard[]{null,null,null,null,null});
        BoardRow enemy = new BoardRow(eventSystem,new Health(25,25),5,new IBattleCard[]{null,null,null,null,null});
        BoardRow player = new BoardRow(eventSystem,new Health(25,25),5,new IBattleCard[]{null,null,null,null,null});
        board = new Board(eventSystem, 5,buffer,enemy,player);

        Battle battle = new Battle(eventSystem,uiProvider,new BattleCardFactory(eventSystem),opponent,deck,this.energy,board,()-> flag.fire(),()-> flag.fire());
        this.battle = battle;
        this.orchestrator = battle;
    }

    @Test
    public void test_PlayCard() throws BattleInputException {
        orchestrator.playCard(0,card);
        assertNotNull(board.getPlayer().getCard(0));
    }

    @Test(expected = BattleInputException.class)
    public void test_playCardNotInHand() throws BattleInputException {
        orchestrator.playCard(0, null);
    }

    @Test
    public void test_sacrifice() throws BattleInputException {
        orchestrator.playCard(0,card);
        orchestrator.sacrifice(0);
        assertEquals(1, this.energy.getEnergy());
    }

    @Test
    public void test_endTurn() throws BattleInputException {
        orchestrator.playCard(0,card);
        orchestrator.endTurn();
        assertEquals(24,this.board.getEnemy().getHealth().getHealth());
    }
}
