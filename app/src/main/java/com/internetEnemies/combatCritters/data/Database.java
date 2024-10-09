/**
 * Database.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-01
 *
 * @PURPOSE:    a singleton interface to the database
 *              containing inventories from data layer
 */

package com.internetEnemies.combatCritters.data;


import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.*;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

/**
 * This class is used as a singleton interface to the database
 */
public class Database {
    private static Database INSTANCE;
    
    private final String path;

    private final IPackInventory packInventory;
    private final ICardSearch cardSearch;
    private final ICurrencyInventory currencyInventory;
    private final IRegistry<Opponent> opponentDB;
    private final IUsersDB usersDB;

    private Database() {
        this.path = Main.getDBPathName();
        packInventory = new PackInventoryHSQLDB(path);
        cardSearch = new CardSearchHSQLDB(path);
        currencyInventory = new CurrencyInventoryHSQLDB(path);
        opponentDB = new BattleInfoRegistryHSQLDB(path);
        usersDB = new UsersDB(path);
    }

    public static synchronized Database getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public IDeckInventory getDeckInventory(User user) {
        return new DeckInventoryHSQLDB(path, user);
    }

    public ICardInventory getCardInventory() {
        return new CardInventoryHSQLDB(path, new User(1, "username", "password"));// ! this will have to stay until a full implementation supporting users is finished
    }
    public ICardInventory getCardInventory(User user) {
        return new CardInventoryHSQLDB(path, user);
    }
    public IProfilesDB getProfilesDB(User user) {
        return new ProfilesHSQLDB(path, user);
    }
    

    public ICardSearch getCardSearch() {
        return this.cardSearch;
    }

    public ICurrencyInventory getCurrencyInventory(){
        return currencyInventory;
    }
    public IPackInventory getPackInventory(){
        return packInventory;
    }
    public IRegistry<Opponent> getOpponentDB(){
        return opponentDB;
    }
    public IUsersDB getUsersDB(){
        return usersDB;
    }
    
    public IFriendsDB getFriendsDB(User user){
        return new FriendsHSQLDB(path, user);
    }
}
