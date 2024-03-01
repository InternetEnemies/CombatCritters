package com.internetEnemies.combatCritters.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLDatabase extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "CombatCritters.db";
    private static final int DATABASE_VERSION = 1;
    public SQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables i think
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
