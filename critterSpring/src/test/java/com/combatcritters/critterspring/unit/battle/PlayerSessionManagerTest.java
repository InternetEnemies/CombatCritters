package com.combatcritters.critterspring.unit.battle;

import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
import com.combatcritters.critterspring.battle.playerSession.PlayerSessionManager;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchmakingService;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.MatchmakingService;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public class PlayerSessionManagerTest {
    IPlayerSessionManager playerSessionManager;
    IMatchmakingService matchmakingService;
    @BeforeEach
    public void setup() {
        matchmakingService = mock(MatchmakingService.class);
        playerSessionManager = new PlayerSessionManager(matchmakingService);
    }

    @Test
    public void test_createSession(){
    }

    @Test
    public void test_getNXSession(){
        WebSocketSession session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("1");
        Assert.isNull(playerSessionManager.getPlayerSession(session),"Session was not null");
    }

    @Test
    public void test_createGetSession(){
        WebSocketSession session = mock(WebSocketSession.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(session.getId()).thenReturn("1");

        IPlayerSession pSession = playerSessionManager.createPlayerSession(user, session);
        Assert.notNull(pSession,"Session should not be null");
        Assert.notNull(playerSessionManager.getPlayerSession(session),"Session should not be null");
    }

    @Test
    public void test_createExisting(){
        WebSocketSession session = mock(WebSocketSession.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(session.getId()).thenReturn("1");

        IPlayerSession pSession = playerSessionManager.createPlayerSession(user, session);
        Assert.isTrue(pSession.equals(playerSessionManager.createPlayerSession(user, session)),"Session should be created and fetched");

    }

    @Test
    public void test_closeSession(){
        WebSocketSession session = mock(WebSocketSession.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(session.getId()).thenReturn("1");

        IPlayerSession pSession = playerSessionManager.createPlayerSession(user, session);
        playerSessionManager.closePlayerSession(user);
        Assert.isNull(playerSessionManager.getPlayerSession(session),"Session should be null");

    }
}
