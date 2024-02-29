package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.List;

//todo possible cleanup: the selection logic here could be extracted into a separate class (it's very similar to InventoryViewModel)
// a note for above: the class might belong in the logic layer
public class BuilderViewModel extends ViewModel {//TODO docs for this class
    private final IDeckManager deckManager;
    private DeckDetails selectedDeck; // TODO this should store the current deck builder instead
    private int selected = -1;

    private final List<ISelectListener> selectListeners;

    public BuilderViewModel() {
        super();
        this.deckManager = new DeckManager();
        this.selectListeners = new ArrayList<>();

    }

    // Deck
    public IDeckBuilder getDeckBuilder() {
        return deckManager.getBuilder(this.selectedDeck);
    }

    public void setDeck(DeckDetails deckDetails) {
        this.selectedDeck = deckDetails;
    }

    public void clearDeckSelection(){
        this.selectedDeck = null;
    }

    //Card in deck

    public Card getSelectedCard() {
        if (this.selectedDeck == null || this.selected < 0) {
            return null;
        } else {
            return getDeckBuilder().getCards().get(selected);
        }
    }
    public void removeSelectedCard() throws UIException {
        if(this.selectedDeck == null) {
            throw new UIException("No Deck Selected");
        }
        if(!hasSelection()) {
            throw new UIException("No Card Selected");
        }

        //* Note: if this is null there is a problem in the backend
        IDeckBuilder builder = getDeckBuilder();

        builder.removeCard(selected);
    }
    public void setSelectedCard(int idx) {
        if(idx == selected && idx != -1) {
            clearSelection();
        } else {
            selected = idx;
        } // deselect if the user clicks the same card again

        fireSelectChangeEvent();
    }

    public void clearSelection() {
        setSelectedCard(-1);
    }

    public int getSelectedIdx() {
        return this.selected;
    }

    public void addSelectListener(ISelectListener listener) {
        selectListeners.add(listener);
    }
    private void fireSelectChangeEvent() {
        for(ISelectListener selectListener : selectListeners) {
            selectListener.onSelect(this.selected);
        }
    }

    public DeckDetails getDeckDetails() {
        return this.selectedDeck;
    }

    private boolean hasSelection() {
        return selected >= 0;
    }
}
