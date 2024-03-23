package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

public class BattleCardsRowViewModel extends ViewModel {
    private final MutableLiveData<List<CardState>> cardStates;
    private cardClickListener listener;
    public BattleCardsRowViewModel() {
        this.cardStates = new MutableLiveData<>(); // no initial value as it should be setup by the parent
    }

    public LiveData<List<CardState>> getCardList() {
        return cardStates;
    }

    public void setCardStates(List<CardState> cardStates) {
        this.cardStates.setValue(cardStates);
    }
    public void clickedOn(int pos) {
        if(listener != null) {
            listener.onClick(pos);
        }
    }

    public void setListener(cardClickListener listener) {
        this.listener = listener;
    }
}

interface cardClickListener {
    void onClick(int pos);
}
