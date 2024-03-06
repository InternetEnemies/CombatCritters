package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * MarketplaceViewModel.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Used to store the currently selected offers (what tab is selected), as well as
 *               the position of the selected item in selected offers.
 */
public class MarketplaceViewModel extends ViewModel {
    private final MutableLiveData<List<MarketTransaction>> selectedOffers;
    private final MutableLiveData<Integer> selectedPosition = new MutableLiveData<>();
    private final List<ISelectListener> selectListeners;

    public MarketplaceViewModel() {
        super();
        this.selectListeners = new ArrayList<>();
        this.selectedOffers = new MutableLiveData<>();
        selectedPosition.setValue(-1); //Initially not selected
    }


    /**
     * Set the currently selected offers.
     *
     * @param offers offers to select.
     */
    public void setOffers(List<MarketTransaction> offers) {
        this.selectedOffers.setValue(offers);
    }

    /**
     * Get the selected offers.
     *
     * @return LiveData for the selected offers
     */
    public LiveData<List<MarketTransaction>> getOffers() {
        return this.selectedOffers;
    }

    /**
     * set the currently selected transaction in selectedOffers
     *
     * @param idx position of the Transaction to select
     */
    public void setSelectedTransaction(int idx) {
        if (selectedPosition.getValue() != null && idx == selectedPosition.getValue() && idx != -1) {
            clearSelection();
        } else {
            selectedPosition.setValue(idx);
        } // deselect if the user clicks the same Transaction again

        fireSelectChangeEvent();
    }

    /**
     * @return the currently selected MarketTransaction
     */
    public MarketTransaction getTransaction() {
        if (selectedPosition.getValue() == null || selectedPosition.getValue() == -1) {
            return null;
        } else {
            return selectedOffers.getValue().get(selectedPosition.getValue());
        }
    }

    /**
     * Clear the currently selected Transaction
     */
    public void clearSelection() {
        setSelectedTransaction(-1);
    }

    /**
     * Get the position of the currently selected Transaction
     *
     * @return LiveData of the index of the selected Transaction
     */
    public MutableLiveData<Integer> getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * @return int of the index of the selected Transaction.
     */
    public int getSelectedPositionAsInt() {
        if (selectedPosition.getValue() == null) {
            return -1;
        } else {
            return selectedPosition.getValue();
        }
    }

    /**
     * Add a new observer that is called on changes to the selected value
     *
     * @param listener callback on change
     */
    public void addSelectListener(ISelectListener listener) {
        selectListeners.add(listener);
    }

    /**
     * Helper for firing onSelect events
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