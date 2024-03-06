package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public interface IItemStackExtractor {
    List<Card> getCards();
    List<Pack> getPacks();
}
