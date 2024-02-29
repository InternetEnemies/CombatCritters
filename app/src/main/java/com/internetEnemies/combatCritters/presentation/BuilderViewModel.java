package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class BuilderViewModel extends ViewModel {//TODO docs for this class
    private final IDeckManager deckManager;
    private MutableLiveData<DeckDetails> selectedDeck;
    private int selected = -1;

    private final List<ISelectListener> selectListeners;

    public BuilderViewModel() {
        super();
        this.deckManager = new DeckManager();
        this.selectListeners = new ArrayList<>();
        this.selectedDeck = new MutableLiveData<>();
    }

    // Deck
    private IDeckBuilder getDeckBuilder() {
        return deckManager.getBuilder(this.selectedDeck.getValue());
    }

    public void setDeck(DeckDetails deckDetails) {
        this.selectedDeck.setValue(deckDetails);
    }

    public LiveData<DeckDetails> getDeckDetails() {
        return this.selectedDeck;
    }

    public void clearDeckSelection(){
        this.selectedDeck = null;
    }

    //Card in deck

    public void removeSelectedCard() throws UIException {
        checkDeckSelected();
        if(!hasSelection()) {
            throw new UIException("No Card Selected");
        }

        //* Note: if this is null there is a problem in the backend
        IDeckBuilder builder = getDeckBuilder();

        builder.removeCard(selected);
    }

    public void addCardToDeck(Card card) throws UIException {
        checkDeckSelected();
        if (card == null) {
            throw new UIException("No Card Selected");
        }
        IDeckBuilder builder = getDeckBuilder();
        builder.addCard(card);
    }

    public DeckValidity getValidity(){
        //! DO NOT CATCH THIS
        assert(this.selectedDeck != null); // this state should be unreachable by a user

        return getDeckBuilder().validate();
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


    private boolean hasSelection() {
        return selected >= 0;
    }

    public boolean hasSelectedDeck() {
        return this.selectedDeck != null;
    }

    // input validation helpers
    private void checkDeckSelected() throws UIException {
        if (this.selectedDeck == null) {
            throw new UIException("No Deck Selected");
        }
    }
}
