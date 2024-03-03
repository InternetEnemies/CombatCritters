package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.DeckValidity;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MarketplaceViewModel extends ViewModel {
    private final MutableLiveData<List<Transaction>> selectedOffers;
    private int selected = -1;
    private final List<ISelectListener> selectListeners;

    public MarketplaceViewModel() {
        super();
        this.selectListeners = new ArrayList<>();
        this.selectedOffers = new MutableLiveData<>();
    }
    

    /**
     * set the currently selected deck, set to null for no selection (use clearDeckSelection for this)
     * @param deckDetails DeckDetails to select
     */
    public void setOffers(List<Transaction> offers) {
        this.selectedOffers.setValue(offers);
    }

    /**
     * get the details object for the currently selected deck
     * @return LiveData for the details for the deck
     */
    public LiveData<List<Transaction>> getOffers() {
        return this.selectedOffers;
    }

    /**
     * set the currently selected card
     * @param idx position of the card to select
     */
    public void setSelectedTransaction(int idx) {
        if(idx == selected && idx != -1) {
            clearSelection();
        } else {
            selected = idx;
        } // deselect if the user clicks the same card again

        fireSelectChangeEvent();
    }

    /**
     * clear the currently selected card
     */
    public void clearSelection() {
        setSelectedTransaction(-1);
    }

    /**
     * get the position of the currently selected card
     * @return integer index of the selected card
     */
    public int getSelectedIdx() {
        return this.selected;
    }

    /**
     * add a new observer that is called on changes to the selected value
     * @param listener callback on change
     */
    public void addSelectListener(ISelectListener listener) {
        selectListeners.add(listener);
    }

    /**
     * helper for firing onSelect events
     */
    private void fireSelectChangeEvent() {
        for(ISelectListener selectListener : selectListeners) {
            selectListener.onSelect(this.selected);
        }
    }


    /**
     * @return true iff there is a card currently selected
     */
    private boolean hasSelection() {
        return selected >= 0;
    }
}
