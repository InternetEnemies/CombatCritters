package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

public class BattleRegistry {
    public Battle getBattle(int id, List<Card> deck) {
        //todo actually implement this
        return new Battle(deck);
    }
}
