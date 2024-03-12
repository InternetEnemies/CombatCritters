/**
 * PackCardDatabase.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-03
 *
 * @PURPOSE:    fake database for packs and cards
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

public class PackCardDatabase {
    private static PackCardDatabase INSTANCE;

    private final IRegistry<Pack> packDB;
    private final IRegistry<Card> cardDB;

    private PackCardDatabase() {
        String path = Main.getDBPathName();
        cardDB = new RegistryCardHSQLDB(path);
        packDB = new RegistryPackHSQLDB(path);
    }

    public static synchronized PackCardDatabase getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PackCardDatabase();
        }
        return INSTANCE;
    }
    public IRegistry<Pack> getPackDB(){
        return packDB;
    }
    public IRegistry<Card> getCardDB(){
        return cardDB;
    }

}
