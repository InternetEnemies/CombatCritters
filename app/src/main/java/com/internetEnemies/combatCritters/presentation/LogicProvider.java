package com.internetEnemies.combatCritters.presentation;

import com.internetEnemies.combatCritters.Logic.CardDeconstructor;
import com.internetEnemies.combatCritters.Logic.ICardDeconstructor;

public class LogicProvider {
    private static LogicProvider instance;
    private final ICardDeconstructor cardDeconstructor;
    private LogicProvider() {
        cardDeconstructor = new CardDeconstructor();
    }

    public static synchronized LogicProvider getInstance() {
        if (instance == null) {
            instance = new LogicProvider();
        }
        return instance;
    }

    public ICardDeconstructor getCardDeconstructor() {
        return cardDeconstructor;
    }

}
