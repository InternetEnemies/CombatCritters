package com.combatcritters.critterspring;

import com.combatcritters.critterspring.wrappers.VendorManagerWrapper;
import com.combatcritters.critterspring.wrappers.VendorWrapperFactory;
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
import com.internetEnemies.combatCritters.Logic.market.*;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandlerFactory;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.participant.IUserParticipantFactory;
import com.internetEnemies.combatCritters.Logic.transaction.participant.SystemParticipant;
import com.internetEnemies.combatCritters.Logic.transaction.participant.UserParticipantFactory;
import com.internetEnemies.combatCritters.Logic.users.*;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDBFactory;
import com.internetEnemies.combatCritters.data.market.IVendorRepDBFactory;
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
    public IVendorManagerFactory getVendorManagerFactory(Database database, IVendorOfferDBFactory vendorOfferDBFactory) {
        return user-> new VendorManagerWrapper(
                database.getVendorDB(user), 
                new VendorWrapperFactory(user, vendorOfferDBFactory)
        );
    }
    
    @Bean
    public IVendorOfferDBFactory getVendorOfferDBFactory(Database database) {
        return database::getVendorOfferDB;
    }
    
    @Bean
    public ITransactionHandlerFactory getTransactionHandlerFactory() {
        return TransactionHandler::new;
    }
    
    @Bean
    public IUserParticipantFactory getUserParticipantFactory(Database database){
        return new UserParticipantFactory(database);
    }
    @Bean
    public IMarketPurchaseHandlerFactory getMarketPurchaseHandlerFactory(
            ITransactionHandlerFactory transactionHandlerFactory,
            IUserParticipantFactory userParticipantFactory,
            IVendorRepDBFactory vendorRepDBFactory) {
        return (user, vendorDetails) -> {
            ITransactionHandler handler = transactionHandlerFactory
                    .getTransactionHandler(userParticipantFactory.createUserParticipant(user), new SystemParticipant());
            IVendorRepManager manager = new VendorRepManager(vendorRepDBFactory.create(user, vendorDetails));
            return new MarketPurchaseHandler(handler, manager);
        };
    }
    
    @Bean
    public IVendorRepDBFactory getVendorRepDBFactory(Database database) {
        return database::getVendorRepDB;
    }
}
