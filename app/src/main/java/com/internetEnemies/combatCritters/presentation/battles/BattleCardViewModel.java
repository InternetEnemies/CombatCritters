package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.battles.CardState;

/**
 * BattleCardViewModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    hold state for a BattleCardFragment
 */
public class BattleCardViewModel extends ViewModel {
    private final MutableLiveData<CardState> card;
    private OnClick onClick;
    public BattleCardViewModel() {
        this.card = new MutableLiveData<>(null);
    }

    /**
     * get the current card
     * @return LiveData for the card
     */
    public LiveData<CardState> getCard() {
        return card;
    }

    /**
     * set the card for the fragment
     * @param card new card to set to
     */
    public void setCard(CardState card) {
        this.card.setValue(card);
    }

    /**
     * set a listener for when the fragment is clicked
     * @param onClick listener
     */
    public void setOnClickListener(OnClick onClick) {
        this.onClick = onClick;
    }

    /**
     * fire a onClick event to the listener
     */
    public void onClick(){
        if(onClick != null) {
            onClick.onClick();
        }
    }
}

interface OnClick {
    void onClick();
}