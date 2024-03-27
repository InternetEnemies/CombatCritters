package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Energy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.ArrayList;
import java.util.List;

/**
 * BattleRegistry.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE: provide battles from the set of battles
 */
public class BattleRegistry implements IBattleRegistry {
    @Override
    public Battle getBattle(IBattleStateObserver uiProvider, int id, List<Card> deck) {
        //todo actually implement this

        IEventSystem eventSystem = new EventSystem();
        CritterCard card = new CritterCard(
                0,
                "TestName",
                "",
                1,
                Card.Rarity.COMMON,
                5,
                10,
                new ArrayList<>()
        );
        BattleCard[] cards = new BattleCard[]{
                null,
                new BattleCard(eventSystem, card),
                new BattleCard(eventSystem, card),
                null,
                new BattleCard(eventSystem, card)
        };
        BattleCard[] cards1 = new BattleCard[]{
                null,
                new BattleCard(eventSystem, card),
                new BattleCard(eventSystem, card),
                null,
                new BattleCard(eventSystem, card)
        };
        BattleCard[] cards2 = new BattleCard[]{
                null,
                new BattleCard(eventSystem, card),
                new BattleCard(eventSystem, card),
                null,
                new BattleCard(eventSystem, card)
        };
        Board board = new Board(
                eventSystem,
                new Health(25,25),
                new Health(25,25),
                5,
                cards,
                cards1,
                cards2
        );
        return new Battle(eventSystem,uiProvider, deck, new Energy(5,1), board);
    }
}
