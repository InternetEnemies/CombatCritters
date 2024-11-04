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


import com.internetEnemies.combatCritters.Logic.market.IVendorRepManagerFactory;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.*;
import com.internetEnemies.combatCritters.data.init.SQLInitializer;
import com.internetEnemies.combatCritters.data.market.*;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * This class is used as a singleton interface to the database
 */
public class Database {
    private static Database INSTANCE;
    
    private final String path;

    private final ICardSearch cardSearch;
    private final IRegistry<Opponent> opponentDB;
    private final IUsersDB usersDB;
    private final User dummyUser; //! will need to be removed when legacy is fully implemented

    private Database() {
        this.path = Main.getDBPathName();
        SQLInitializer initializer = new SQLInitializer(path);
        initializer.initFull();
        this.dummyUser = new User(0, "username", "password");
        
        cardSearch = new CardSearchHSQLDB(path);
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
        return new CardInventoryHSQLDB(path, dummyUser);
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
        return getCurrencyInventory(dummyUser);
    }
    public ICurrencyInventory getCurrencyInventory(User user) {
        return new CurrencyInventoryHSQLDB(path, user);
    }
    public IPackInventory getPackInventory(){
        return getPackInventory(dummyUser);
    }
    public IPackInventory getPackInventory(User user){
        return new PackInventoryHSQLDB(path, user);
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

    public IVendorDB getVendorDB(User user) {
        return new VendorDB(path, user);
    }
    public IVendorOfferDB getVendorOfferDB(VendorDetails details, User user, IVendorRepManagerFactory repManagerFactory) {
        return new VendorOfferDB(path, user, details, repManagerFactory);
    }
    
    public IVendorRepDB getVendorRepDB(User user, VendorDetails details){
        return new VendorRepDB(path, user, details);
    }
}
