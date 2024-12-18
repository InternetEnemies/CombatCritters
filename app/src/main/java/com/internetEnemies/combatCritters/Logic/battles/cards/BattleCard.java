package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.events.CardHealthEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BattleCard.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    implementation of IBattleCard
 */

public class BattleCard implements IBattleCard {
    private final IEventSystem events;
    private final CritterCard card;
    private final IHealth health;
    private IBoardRow parent;
    private IBoardRow opposing;
    int slot; // ideally state like this would only be held in one place

    public BattleCard(IEventSystem events, CritterCard card, IHealth health) {
        this.events = events;
        this.card = card;
        this.health = health;
        this.slot = -1;
        this.parent = null;
        this.opposing = null;
        // setup Events

        this.health.getDamageEvent().subscribe(this::onDamage);
        this.health.getHealEvent().subscribe(this::onHeal);
        this.health.getChangeEvent().subscribe(this::onHealthChange);
    }
    public BattleCard(IEventSystem events, CritterCard card) {
        this(
                events,
                card,
                new Health(card.getHealth(),card.getHealth())
        );
    }

    @Override
    public CardState getCardState() {
        return new CardState(health.getHealth(), card);
    }

    @Override
    public int getHealth() {
        return this.health.getHealth();
    }

    @Override
    public void moveTo(int slot, IBoardRow parent, IBoardRow opposing){
        this.slot = slot;
        this.parent = parent;
        this.opposing = opposing;
    }

    @Override
    public void kill(){
        Logger.getLogger(Battle.BATTLE_LOG).log(Level.INFO, String.format("Killing card (%s) @ pos: %d",this.card.toString(), this.slot));
        //? this function will have more functionality when abilities are implemented (its not just a logging function)
    }

    @Override
    public void damage(int amount){
        assert this.parent != null;
        this.health.damage(amount);
    }

    @Override
    public void heal(int amount){
        assert this.parent != null;
        this.health.heal(amount);
    }

    @Override
    public void attack(){
        this.opposing.attack(this.slot, this.card.getDamage());
    }

    @Override
    public int getDamage() {
        return this.card.getDamage();
    }

    //? Note these fire event triggers shouldn't be moved to heal/damage since health could decide that damage/healing isn't being done
    /**
     * event handler for when the health is damaged
     */
    private void onDamage(int damage) {
        checkParent();
        this.events.getOnCardDamaged().fireEvent(new CardHealthEvent(
                this.slot,
                this.parent,
                this,
                damage
        ));
    }
    /**
     * event handler for when the health is healed
     */
    private void onHeal(int amount) {
        checkParent();
        this.events.getOnCardHealed().fireEvent(new CardHealthEvent(
                this.slot,
                this.parent,
                this,
                amount
        ));
    }

    /**
     * event handler for whenever the health changes
     */
    private void onHealthChange(int health) {
        checkParent();
        if(health <= 0) {
            try {
                this.parent.killCard(this.slot);
            } catch (BattleException e) {
                throw new BattleRuntimeException("Card failed at calling kill on itself, this can only be the result of a bad board state", e);
                //if this is reached something has gone terribly wrong
            }
        }
        //this implementation only cares if the change causes death
    }

    private void checkParent() throws BattleRuntimeException{
        if(this.parent == null){
            throw new BattleRuntimeException("Cards not on a board shouldn't be acted on");
        }
    }
}
