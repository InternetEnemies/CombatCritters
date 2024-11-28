package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.User;

import java.util.*;

public class MatchmakingService implements IMatchmakingService {
    private final Map<Integer, IPlayer> matching;
    private final List<IMatch> matches;

    public MatchmakingService() {
        matching = new HashMap<>();
        matches = new ArrayList<>();
    }
    @Override
    public synchronized IPlayer getPlayer(User user, IBattleStateObserver observer) {
        //create player
        IPlayer player = new Player(observer, user);
        // match player or add to matching pool
        if (matching.isEmpty()) {
            matching.put(user.getId(), player);
        } else {
            Integer userid = matching.keySet().iterator().next();
            IPlayer player2 = matching.remove(userid); // totally real matchmaking algorithm
            createMatch(player,player2);
        }
        return player;
    }

    private void createMatch(IPlayer player, IPlayer player2) {
        matches.add(new Match(player, player2));
    }

    @Override
    public synchronized void removePlayer(User user) {
        matching.remove(user.getId());
    }
}
