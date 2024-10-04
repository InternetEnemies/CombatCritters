package com.combatcritters.critterspring;

import com.internetEnemies.combatCritters.Logic.inventory.cards.CardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.CardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


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
    public Database getDatabase() {
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
}
