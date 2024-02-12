package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.Card;

public class SelectedCardViewModel extends ViewModel {
    private final MutableLiveData<Card> selectedCard = new MutableLiveData<>();

    public void setSelectedCard(Card card) {
        selectedCard.setValue(card);
    }

    public LiveData<Card> getSelectedCard() {
        return selectedCard;
    }
}
