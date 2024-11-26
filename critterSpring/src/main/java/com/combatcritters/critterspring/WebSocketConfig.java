package com.combatcritters.critterspring;

import com.combatcritters.critterspring.battle.BattleHandler;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
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


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(battleHandler(playerSessionManager, userManager), "/ws").setAllowedOrigins(origin, originDev);
    }
    
    @Bean
    public WebSocketHandler battleHandler(IPlayerSessionManager playerSessionManager, IUserManager userManager) {
        return new BattleHandler(playerSessionManager, userManager);
    }
}
