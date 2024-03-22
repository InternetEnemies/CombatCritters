package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;

public class HandPopupViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> cards;

    public HandPopupViewModel(){
        this.cards = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Card>> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards){
        assert cards != null;
        this.cards.setValue(cards);
    }
}