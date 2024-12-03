package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


import java.util.*;

public class MatchmakingService implements IMatchmakingService {
    private final Map<Integer, IPlayer> matching;
    private final List<IMatch> matches;

    public MatchmakingService() {
        matching = new HashMap<>();
        matches = new ArrayList<>();
    }
    @Override
    public synchronized void matchMake(IPlayer player) {
        if (matching.get(player.getUser().getId()) != null) return; // already matching do nothing
        // match player or add to matching pool
        if (matching.isEmpty()) {
            matching.put(player.getUser().getId(), player);
        } else {
            Integer userid = matching.keySet().iterator().next();
            IPlayer player2 = matching.remove(userid); // totally real matchmaking algorithm
            createMatch(player,player2);
        }
    }

    private synchronized void createMatch(IPlayer player, IPlayer player2) {
        matches.add(new Match(player, player2));
    }

    @Override
    public synchronized void removePlayer(IPlayer player) {
        matching.remove(player.getUser().getId());
        //this should also close any active matches
        List<IMatch> toRemove = getPlayerMatch(player);
        for (IMatch match : toRemove) {
            for (IPlayer p : match.getPlayers()) {
                matching.remove(p.getUser().getId());
            }
            match.endMatch();
        }
    }

    private List<IMatch> getPlayerMatch(IPlayer player) {
        List<IMatch> pMatches = new ArrayList<>();
        // find player matches (should only ever be 1)
        for(IMatch match : matches) {
            for(IPlayer p : match.getPlayers()) {
                if(p.getUser().getId() == player.getUser().getId()) {
                    pMatches.add(match);
                }
            }
        }
        return pMatches;
    }
}
