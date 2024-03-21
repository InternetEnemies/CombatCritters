package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.ArrayList;
import java.util.List;

public class BattleCardsRowViewModel extends ViewModel {
    private final MutableLiveData<List<CardState>> cardStates;
    public BattleCardsRowViewModel() {
        this.cardStates = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<CardState>> getCardList() {
        return cardStates;
    }

    public void setCardStates(List<CardState> cardStates) {
        this.cardStates.setValue(cardStates);
    }
}