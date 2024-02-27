package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;

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

    public void setSelectedCard(int idx) {
        if(idx == selectedIdx && idx != -1) {
            clearSelection();
        } else {
            selectedIdx = idx;
        } // deselect if the user clicks the same card again

        fireSelectChangeEvent();
    }
    public void clearSelection() {
        setSelectedCard(-1);
    }

    public int getSelectedIdx() {
        return selectedIdx;
    }

    public Card getSelectedCard(){
        Card card;
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
    public List<Card> getCards(){
        //todo add the filters here
        //todo this will be updated following itemstack changes
        //! a warning related to the above todo: this technically doesnt need to maintain order so could lead to some odd bugs
        return new ArrayList<>(cardCatalog.getAll().keySet());
    }

    public void addSelectListener(ISelectListener listener) {
        selectListeners.add(listener);
    }
    private void fireSelectChangeEvent() {
        for(ISelectListener selectListener : selectListeners) {
            selectListener.onSelect(this.selectedIdx);
        }
    }
}