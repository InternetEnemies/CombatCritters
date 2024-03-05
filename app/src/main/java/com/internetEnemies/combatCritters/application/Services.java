package com.internetEnemies.combatCritters.application;


public class Services {
    /*
    private static RecipePersistence recipePersistence = null;
    private static RecipeTagPersistence recipeTagPersistence = null;

    public static synchronized RecipeTagPersistence getRecipeTagPersistence(boolean forProduction) {
        if(recipeTagPersistence == null) {
            if (forProduction) {
                recipeTagPersistence = new RecipeTagPersistenceHSQLDB(Main.getDBPathName());
            } else {
                recipeTagPersistence = new RecipeTagPersistenceStub();
            }
        }
        return recipeTagPersistence;
    }

    public static synchronized RecipePersistence getRecipePersistence(boolean forProduction) {
        if(recipePersistence == null) {
            if (forProduction) {
                recipePersistence = new RecipePersistenceHSQLDB(getRecipeTagPersistence(true), Main.getDBPathName());
            } else {
                recipePersistence = new RecipePersistenceStub();
            }
        }
        return recipePersistence;
    }

    public static synchronized void clean() {
        recipePersistence = null;
        recipeTagPersistence = null;
    }
    */
}

