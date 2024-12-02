package com.combatcritters.critterspring.battle.payloads.events;

import com.combatcritters.critterspring.battle.payloads.CardStatePayload;

import java.util.List;

public record BoardStateEvent(List<CardStatePayload> slots, String type) {
}
