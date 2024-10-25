package com.combatcritters.critterspring.payloads.packs;

import com.internetEnemies.combatCritters.objects.Pack;

/**
 * PackPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/25/24
 * 
 * @PURPOSE:    json pack payload representation
 */
public record PackPayload(int packid, String name, String image) {
    public static PackPayload from(Pack pack) {
        return new PackPayload(pack.getId(), pack.getName(), pack.getImage());
    }
}
