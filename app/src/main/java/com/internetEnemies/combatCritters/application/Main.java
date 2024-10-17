package com.internetEnemies.combatCritters.application;

public class Main {

    private static String dbName = "DBInit";

    public static void setDBPathName(final String name) {
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}

