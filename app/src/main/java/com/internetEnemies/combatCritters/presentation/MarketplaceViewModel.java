package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

public class MarketplaceViewModel extends ViewModel {
    private final MutableLiveData<List<MarketTransaction>> selectedOffers;
    private final MutableLiveData<Integer> selectedPosition = new MutableLiveData<>();

    private final List<ISelectListener> selectListeners;

    public MarketplaceViewModel() {
        super();
        this.selectListeners = new ArrayList<>();
        this.selectedOffers = new MutableLiveData<>();
        selectedPosition.setValue(-1);
    }


    /**
     * set the currently selected deck, set to null for no selection (use clearDeckSelection for this)
     *
     * @param offers DeckDetails to select
     */
    public void setOffers(List<MarketTransaction> offers) {
        this.selectedOffers.setValue(offers);
    }

    /**
     * get the details object for the currently selected deck
     *
     * @return LiveData for the details for the deck
     */
    public LiveData<List<MarketTransaction>> getOffers() {
        return this.selectedOffers;
    }

    /**
     * set the currently selected card
     *
     * @param idx position of the card to select
     */
    public void setSelectedTransaction(int idx) {
        if (selectedPosition.getValue() != null && idx == selectedPosition.getValue() && idx != -1) {
            clearSelection();
        } else {
            selectedPosition.setValue(idx);
        } // deselect if the user clicks the same card again

        fireSelectChangeEvent();
    }

    public MarketTransaction getTransaction() {
        if (selectedPosition.getValue() == null || selectedPosition.getValue() == -1) {
            return null;
        } else {
            return selectedOffers.getValue().get(selectedPosition.getValue());
        }
    }

    /**
     * clear the currently selected card
     */
    public void clearSelection() {
        setSelectedTransaction(-1);
    }

    /**
     * get the position of the currently selected card
     *
     * @return integer index of the selected card
     */
    public MutableLiveData<Integer> getSelectedPosition() {
        return selectedPosition;
    }

    public int getSelectedPositionAsInt() {
        if (selectedPosition.getValue() == null) {
            return -1;
        } else {
            return selectedPosition.getValue();
        }
    }

    /**
     * add a new observer that is called on changes to the selected value
     *
     * @param listener callback on change
     */
    public void addSelectListener(ISelectListener listener) {
        selectListeners.add(listener);
    }

    /**
     * helper for firing onSelect events
     */
    private void fireSelectChangeEvent() {
        for (ISelectListener selectListener : selectListeners) {
            if (selectedPosition.getValue() == null) {
                selectListener.onSelect(-1);
            } else {
                selectListener.onSelect(selectedPosition.getValue());
            }
        }
    }

}