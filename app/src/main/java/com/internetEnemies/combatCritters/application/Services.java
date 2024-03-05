package com.cook_ebook.application;

import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.RecipeTagPersistence;
import com.cook_ebook.persistence.hsqldb.RecipePersistenceHSQLDB;
import com.cook_ebook.persistence.hsqldb.RecipeTagPersistenceHSQLDB;
import com.cook_ebook.persistence.stubs.RecipePersistenceStub;
import com.cook_ebook.persistence.stubs.RecipeTagPersistenceStub;


public class Services {
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

    /**
     * clean
     *
     * Reset all services so to be reloaded from scratch next time they are referenced
     */
    public static synchronized void clean() {
        recipePersistence = null;
        recipeTagPersistence = null;
    }
}

