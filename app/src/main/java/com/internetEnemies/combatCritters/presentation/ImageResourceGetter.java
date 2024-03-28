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
    private ImageResourceGetter() {/*No need for a constructor*/}

    /**
     * @param id ability id
     * @return the Res id for the ability
     */
    public static int getAbilityResourceId(int id) {
        switch(id){
            case 0: return R.drawable.ability_0;
            case 1: return R.drawable.ability_1;
            case 2: return R.drawable.ability_2;
            case 3: return R.drawable.ability_3;
            case 4: return R.drawable.ability_4;
        }
        return id;
    }
}
