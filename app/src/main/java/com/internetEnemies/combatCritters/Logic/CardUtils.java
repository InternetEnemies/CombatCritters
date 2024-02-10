package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CardUtils {
    public static Map<Card, Integer> listToMap(List<Card> cardList) {
        Map<Card, Integer> map = new HashMap<>();
        for(Card card: cardList) {
            if(map.get(card) == null) {
                map.put(card, 1);
            }
            else {
                int cardCount = map.get(card);
                map.put(card, cardCount + 1);
            }
        }
        return map;
    }

    public static List<Card> mapToList(Map<Card, Integer> cardMap) {
        List<Card> cardList = new ArrayList<>();
        for (Map.Entry<Card, Integer> entry : cardMap.entrySet()) {
            Card card = entry.getKey();
            Integer count = entry.getValue();
            for (int i = 0; i < count; i++) {
                cardList.add(card);
            }
        }
        return cardList;
    }
}
