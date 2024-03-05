package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

public class BuyViewModel extends ViewModel {
    private final MutableLiveData<MarketTransaction> selectedTransaction;
    private int selected = -1;
    private final List<ISelectListener> selectListeners;

    public BuyViewModel() {
        super();
        this.selectListeners = new ArrayList<>();
        this.selectedTransaction = new MutableLiveData<>();
    }

    public LiveData<MarketTransaction> getTransaction() {
        return this.selectedTransaction;
    }

    public void clearTransaction(){
//        setDeck(null);
    }

    /**
     * set the currently selected card
     * @param idx position of the card to select
     */
    public void setSelectedCard(int idx) {
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
        setSelectedCard(-1);
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
