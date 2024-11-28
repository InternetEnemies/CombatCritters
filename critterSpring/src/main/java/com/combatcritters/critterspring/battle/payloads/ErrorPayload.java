package com.combatcritters.critterspring.battle.payloads;

public record ErrorPayload(String message, int code) {
    public static final int BAD_REQUEST = 400;
    public static final int SERVER_ERROR = 500;
}
