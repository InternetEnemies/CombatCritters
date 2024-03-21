package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.battles.CardState;

public class BattleCardViewModel extends ViewModel {
    private final MutableLiveData<CardState> card;
    public BattleCardViewModel() {
        this.card = new MutableLiveData<>(null);// todo create a null object to render an empty slot
    }

    public LiveData<CardState> getCard() {
        return card;
    }

    public void setCard(CardState card) {
        this.card.setValue(card);
    }
}