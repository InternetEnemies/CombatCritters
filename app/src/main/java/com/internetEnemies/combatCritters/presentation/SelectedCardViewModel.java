package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.Card;

public class SelectedCardViewModel extends ItemGridViewModel<Card> {
    //todo there is a better solution to this but it requires a big refactor related to ui state
    //I think we might be using view models kinda wrong lmao
}
