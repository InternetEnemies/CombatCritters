package com.combatcritters.critterspring.battle.payloads;

import com.combatcritters.critterspring.payloads.CardPayload;

public record CardStatePayload(CardPayload<?> card, int health) {
}
