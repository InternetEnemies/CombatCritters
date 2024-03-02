package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * InventoryViewModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    State manager for the InventoryFragment
 */
public class InventoryViewModel extends ViewModel {
    private int selectedIdx;
    private final ICardCatalog cardCatalog;

    private final List<ISelectListener> selectListeners;

    public InventoryViewModel() {
        super();
        this.cardCatalog = new CardCatalog();
        this.selectedIdx = -1;//-1 means no selection here
        this.selectListeners = new ArrayList<>();
    }

    /**
     * set the current selected card
     * @param idx index of the card to select
     */
    public void setSelectedCard(int idx) {
        if(idx == selectedIdx && idx != -1) {
            clearSelection();
        } else {
            selectedIdx = idx;
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
     * get the index of the currently selected card
     * @return int index of the selected card
     */
    public int getSelectedIdx() {
        return selectedIdx;
    }

    /**
     * get the currently selected card
     * @return Card that is selected
     */
    public ItemStack<Card> getSelectedCard(){
        ItemStack<Card> card;
        if(selectedIdx == -1) {
            card = null;
        } else {
            card = getCards().get(selectedIdx);
        }
        return card;
    }


    /**
     * gets the list of cards the inventory is accessing
     * @return list of cards filtered by the filter state
     */
    public List<ItemStack<Card>> getCards(){
        //todo add the filters here
        //todo the above todo are being left in intentionally to be resolved when filtering is added in the filtering branch
        return cardCatalog.getAll();
    }

    /**
     * add a new listener to selection changes
     * @param listener onSelect Handler
     */
    public void addSelectListener(ISelectListener listener) {
        selectListeners.add(listener);
    }

    /**
     * helper function for sending selection change events
     */
    private void fireSelectChangeEvent() {
        for(ISelectListener selectListener : selectListeners) {
            selectListener.onSelect(this.selectedIdx);
        }
    }
}