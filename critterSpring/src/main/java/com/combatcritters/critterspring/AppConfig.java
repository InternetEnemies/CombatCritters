package com.combatcritters.critterspring;

import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.UserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.cards.CardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.CardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckManager;
import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckValidator;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckManagerFactory;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckValidatorFactory;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackCatalog;
import com.internetEnemies.combatCritters.Logic.market.IVendorFactory;
import com.internetEnemies.combatCritters.Logic.market.IVendorManagerFactory;
import com.internetEnemies.combatCritters.Logic.market.VendorFactory;
import com.internetEnemies.combatCritters.Logic.market.VendorManager;
import com.internetEnemies.combatCritters.Logic.users.*;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


/**
 * AppConfig.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/28/24
 * 
 * @PURPOSE:    provide global config for the application, (ie any config that is used basically everywhere should be here)
 */
@Configuration
public class AppConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Database getDatabase(@Value("${jdbc.url}") String path) {
        System.out.println(path);
        Main.setDBPathName(path);
        return Database.getInstance();
    }
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PackCardDatabase getPackCardDatabase() {
        return PackCardDatabase.getInstance();
    }
    
    @Bean
    public CardCatalog getCardCatalog(Database database) {
        return new CardCatalog(database.getCardSearch());
    }
    @Bean
    public ICardRegistry getCardRegistry(PackCardDatabase database) {
        return new CardRegistry(database.getCardDB());
    }
    
    @Bean
    public IUserDataFactory getUserDataFactory(Database database) {
        return new UserDataFactory(database);
    }
    
    @Bean
    public IDeckValidatorFactory getDeckValidator() {
        return DeckValidator::deckValidatorFactory;
    }
    
    @Bean
    public IProfileManagerFactory getProfileManagerFactory(IUserDataFactory userDataFactory) {
        return (user) -> new ProfileManager(userDataFactory.getProfilesDB(user));
    }
    
    @Bean
    public IDeckManagerFactory getDeckManagerFactory(IUserDataFactory userDataFactory) {
        return (user) -> new DeckManager(userDataFactory.getDeckInventory(user), userDataFactory.getCardInventory(user),new DeckValidator(userDataFactory.getCardInventory(user)) );
    }
    
    @Bean
    public IFriendsManagerFactory getFriendsManagerFactory(IUserDataFactory userDataFactory) {
        return user -> new FriendsManager(userDataFactory.getFriendsDB(user));
    }
    
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter(){
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
    
    @Bean
    public IPackCatalog getPackCatalog(PackCardDatabase database) {
        return new PackCatalog(database.getPackDB());
    }
    
    @Bean
    public IUserInitializer getUserInitializer(PackCardDatabase packCardDatabase, IUserDataFactory userDataFactory) {
        return new UserInitializer(packCardDatabase.getPackDB(), packCardDatabase.getCardDB(), userDataFactory);
    }
    
    @Bean
    public IVendorManagerFactory getVendorManagerFactory(Database database, IVendorFactory vendorFactory) {
        return user-> new VendorManager(database.getVendorDB(user), vendorFactory);
    }
    
    @Bean
    public IVendorFactory getVendorFactory() {
        return new VendorFactory();
    }
}
