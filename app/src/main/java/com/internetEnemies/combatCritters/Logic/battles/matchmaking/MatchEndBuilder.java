package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.participant.IUserParticipantFactory;
import com.internetEnemies.combatCritters.Logic.transaction.participant.SystemParticipant;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.RewardTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * MatchEndBuilder.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/5/24
 * 
 * @PURPOSE:    builder for match ending function
 */
public class MatchEndBuilder {
    private final IUserParticipantFactory userParticipantFactory;
    private MatchEndType endType;
    private List<ItemStack<?>> rewards;
    
    public MatchEndBuilder(IUserParticipantFactory userParticipantFactory) {
        this.userParticipantFactory = userParticipantFactory;
        this.rewards = new ArrayList<>();
    }
    
    public void execute(IPlayer player) {
        player.getMatchStateObserver().matchEnded(endType, rewards);
        ITransactionHandler handler = new TransactionHandler(new SystemParticipant(),this.userParticipantFactory.createUserParticipant(player.getUser()));
        handler.perform(new RewardTransaction(rewards));
    }
    
    public void addReward(IItem item, int amount) {
        rewards.add(new ItemStack<>(item, amount));
    }
    
    public void setReason(MatchEndType type){
        this.endType = type;
    }
}
