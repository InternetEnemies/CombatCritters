package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.Card;

//TODO this is a bandaid solution to what is a larger issue of state management in the presentation layer.
//TODO this second todo should stay here till this is made better
public class ItemGridViewModel<T> extends ViewModel {
    private final MutableLiveData<T> selectedCard = new MutableLiveData<>();

    public void setSelectedItem(T item) {
        selectedCard.setValue(item);
    }

    public LiveData<T> getSelectedItem() {
        return selectedCard;
    }
}
