package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;

public class HandPopupViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> cards;
    private final MutableLiveData<Card> selected;

    public HandPopupViewModel(){
        this.cards = new MutableLiveData<>(new ArrayList<>());
        this.selected = new MutableLiveData<>();
    }

    public LiveData<List<Card>> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards){
        assert cards != null;
        this.cards.setValue(cards);
    }

    public LiveData<Card> getSelected() {
        return selected;
    }

    public void setSelected(Card card) {
        this.selected.setValue(card);
    }

    public void clearSelected() {
        this.selected.setValue(null);
    }
}