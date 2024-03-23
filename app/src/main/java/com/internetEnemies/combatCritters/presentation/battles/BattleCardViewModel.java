package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.battles.CardState;

public class BattleCardViewModel extends ViewModel {
    private final MutableLiveData<CardState> card;
    private OnClick onClick;
    public BattleCardViewModel() {
        this.card = new MutableLiveData<>(null);
    }

    public LiveData<CardState> getCard() {
        return card;
    }

    public void setCard(CardState card) {
        this.card.setValue(card);
    }
    public void setOnClickListener(OnClick onClick) {
        this.onClick = onClick;
    }

    public void onClick(){
        if(onClick != null) {
            onClick.onClick();
        }
    }
}

interface OnClick {
    void onClick();
}