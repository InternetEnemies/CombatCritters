package com.combatcritters.critterspring.battle.payloads;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

public record CardStatePayload(CardPayload<?> card, int health) {
    public static CardStatePayload from(CardState cardState) {
        return new CardStatePayload(CardPayload.from(cardState.getCard()), cardState.getCurrHealth());
    }

    public static List<CardStatePayload> listFrom(List<CardState> cardStates) {
        return cardStates.stream().map(CardStatePayload::from).toList();
    }
}
