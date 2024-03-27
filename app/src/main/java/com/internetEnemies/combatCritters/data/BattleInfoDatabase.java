package com.internetEnemies.combatCritters.data;

import android.nfc.NfcAdapter;

import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.BattleInfoHelper;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

/**
 * BattleInfoDatabase.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    A fake Database for offers in marketDB and TradeRegistry
 *              relies on PackCardDatabase
 */

public class BattleInfoDatabase {
    private static BattleInfoDatabase INSTANCE;
    private final IRegistry<Opponent> opponentDB;

    public static synchronized BattleInfoDatabase getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BattleInfoDatabase();
        }
        return INSTANCE;
    }

    public IRegistry<Opponent> getOpponentDB(){
        return opponentDB;
    }

    private BattleInfoDatabase(){
        String path = Main.getDBPathName();
        opponentDB = new BattleInfoRegistryHSQLDB(path);
    }
}
