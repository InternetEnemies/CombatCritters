package com.combatcritters.critterspring;

import com.combatcritters.critterspring.battle.BattleHandler;
import com.combatcritters.critterspring.battle.battle.BattleCommandController;
import com.combatcritters.critterspring.battle.battle.MatchingController;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
import com.combatcritters.critterspring.battle.request.CritterRequestHandler;
import com.combatcritters.critterspring.battle.request.ICritterRequestHandler;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.MatchmakingService;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Value("${ORIGIN.url}")
    private String origin;
    @Value("${ORIGIN.dev}")
    private String originDev;
    
    @Autowired
    IPlayerSessionManager playerSessionManager;
    @Autowired
    IUserManager userManager;
    @Autowired
    ICardRegistry cardRegistry;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(battleHandler(playerSessionManager, userManager,critterRequestHandler(cardRegistry)), "/ws").setAllowedOrigins(origin, originDev);
    }
    
    @Bean
    public WebSocketHandler battleHandler(IPlayerSessionManager playerSessionManager,
                                          IUserManager userManager,
                                          ICritterRequestHandler critterRequestHandler) {
        return new BattleHandler(playerSessionManager, userManager, critterRequestHandler);
    }

    @Bean
    public ICritterRequestHandler critterRequestHandler(ICardRegistry cardRegistry) {
        ICritterRequestHandler handler = new CritterRequestHandler();
        //register controllers
        handler.register(new BattleCommandController(cardRegistry));
        handler.register(new MatchingController(new MatchmakingService()));
        return handler;
    }
}
