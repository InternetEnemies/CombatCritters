package com.internetEnemies.combatCritters.LogicUnitTests.battles.opponents;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
import com.internetEnemies.combatCritters.Logic.battles.opponents.RandomOpponent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.ArrayList;
import java.util.List;

public class RandomOpponentTest {
    @Test
    public void test_play() throws BattleException {
        List<CritterCard> deck = new ArrayList<>();
        CritterCard card = mock(CritterCard.class);
        deck.add(card);
        IEventSystem eventSystem = new EventSystem();
        IBoardRow buffer = new BoardRow(eventSystem,null, 2, new IBattleCard[]{null,null});
        IBoardRow enemy = new BoardRow(eventSystem,null, 2, new IBattleCard[]{null,null});
        IBoardRow player = new BoardRow(eventSystem,null, 2, new IBattleCard[]{null,null});
        Board board = new Board(new EventSystem(),2,buffer,enemy,player );
        IBattleOpponent opponent = new RandomOpponent(new BattleCardFactory(new EventSystem()), deck,2);
        opponent.play(board);
        assertTrue(buffer.getCard(0) != null || buffer.getCard(1)!=null);
    }
}
