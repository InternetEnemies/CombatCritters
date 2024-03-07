/**
 * DeckValidity.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-07
 *
 * @PURPOSE:    properties of deck validity
 */

package com.internetEnemies.combatCritters.objects;

import java.util.Collections;
import java.util.List;

public class DeckValidity {
    private final boolean isValid;
    private final List<String> issues;

    public DeckValidity(boolean isValid, List<String> issues) {
        this.isValid = isValid;
        this.issues = issues;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getIssues() {
        return Collections.unmodifiableList(issues);
    }
}
