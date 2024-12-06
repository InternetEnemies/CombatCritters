package com.combatcritters.critterspring.battle.payloads.matching;

import com.combatcritters.critterspring.payloads.market.OfferItemPayload;

import java.util.List;

public record MatchEndedEvent(String type, List<OfferItemPayload<?>> rewards) {
}
