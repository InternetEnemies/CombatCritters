package com.combatcritters.critterspring.payloads;

import java.util.List;

public record CardCritter(int damage, int health, List<Integer> abilities) {
}
