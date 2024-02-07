package com.internetEnemies.combatCritters.objects;

public class DeckValidity {
    private final boolean isValid;
    private final String[] issues;

    public DeckValidity(boolean isValid, String[] issues) {
        this.isValid = isValid;
        this.issues = issues;
    }

    public boolean isValid() {
        return isValid;
    }

    public String[] getIssues() {
        return issues;
    }
}
