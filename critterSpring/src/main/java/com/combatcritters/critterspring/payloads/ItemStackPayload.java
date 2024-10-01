package com.combatcritters.critterspring.payloads;

public record ItemStackPayload<T>(
        int count,
        T item
) {
}
