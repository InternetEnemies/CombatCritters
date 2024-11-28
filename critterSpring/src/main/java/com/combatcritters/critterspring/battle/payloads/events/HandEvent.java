package com.combatcritters.critterspring.battle.payloads.events;

import com.combatcritters.critterspring.payloads.CardPayload;

import java.util.List;

public record HandEvent(List<CardPayload<?>> cards) {
}
