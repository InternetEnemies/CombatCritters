package com.combatcritters.critterspring;

import com.combatcritters.critterspring.battle.BattleHandler;
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


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(battleHandler(), "/ws").setAllowedOrigins(origin, originDev);
    }
    
    @Bean
    public WebSocketHandler battleHandler() {
        return new BattleHandler();
    }
}
