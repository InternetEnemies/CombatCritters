package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;

public interface IBattle {
    /**
     * get the board object for this battle
     * @return board for this battle
     */
    IBoard getBoard();

    /**
     * get the energy handler for the battle
     * @return Energy object instance for the battle
     */
    IEnergy getEnergy();

    /**
     * get the players health object
     * @return Health object for the player
     */
    IHealth getPlayerHealth();

    /**
     * get the enemies health object
     * @return health object for the enemy
     */
    IHealth getEnemyHealth();
}
