package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

/**
 * BattleCardsRowViewModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    state holder for BattleCardsRow
 */
public class BattleCardsRowViewModel extends ViewModel {
    private final MutableLiveData<List<CardState>> cardStates;
    private cardClickListener listener;
    public BattleCardsRowViewModel() {
        this.cardStates = new MutableLiveData<>(); // no initial value as it should be setup by the parent
    }

    /**
     * get the live data of the card states
     */
    public LiveData<List<CardState>> getCardList() {
        return cardStates;
    }

    /**
     * set new card states
     * @param cardStates list of new card states
     */
    public void setCardStates(List<CardState> cardStates) {
        this.cardStates.setValue(cardStates);
    }
    public void clickedOn(int pos) {
        if(listener != null) {
            listener.onClick(pos);
        }
    }

    /**
     * set the listener for when a card is clicked
     */
    public void setListener(cardClickListener listener) {
        this.listener = listener;
    }
}

interface cardClickListener {
    void onClick(int pos);
}
