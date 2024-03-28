package com.internetEnemies.combatCritters.presentation;

import com.internetEnemies.combatCritters.R;

/**
 * ImageResourceGetter.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     27/March/24
 *
 * @PURPOSE:    Utility class for mapping IDs to their corresponding Res ID.
 */
public class ImageResourceGetter {
    private static int[] abilityResourceIds = {
            R.drawable.ability_0,
            R.drawable.ability_1,
            R.drawable.ability_2,
            R.drawable.ability_3,
            R.drawable.ability_4
    };
    private ImageResourceGetter() {/*No need for a constructor*/}

    /**
     * @param id ability id
     * @return the Res id for the ability
     */
    public static int getAbilityResourceId(int id) {
        if(id >= 0 && id < abilityResourceIds.length) {
            return abilityResourceIds[id];
        }
        return R.drawable.ability_1; //Default to cookies
    }
}
