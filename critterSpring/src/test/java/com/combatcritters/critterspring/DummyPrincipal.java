package com.combatcritters.critterspring;

import java.security.Principal;

public class DummyPrincipal implements Principal {
    private final String name;
    public DummyPrincipal(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
