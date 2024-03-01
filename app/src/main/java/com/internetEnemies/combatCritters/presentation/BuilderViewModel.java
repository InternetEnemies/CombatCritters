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

import java.util.ArrayList;
import java.util.List;

/**
 * BuilderViewModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    class that handles state for the BuilderFragment
 */
public class BuilderViewModel extends ViewModel {
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

    /**
     * helper function for getting the builder for the selected deck
     * @return IDeckBuilder for the selected deck
     */
    private IDeckBuilder getDeckBuilder() {
        return deckManager.getBuilder(this.selectedDeck.getValue());
    }

    /**
     * set the currently selected deck, set to null for no selection (use clearDeckSelection for this)
     * @param deckDetails DeckDetails to select
     */
    public void setDeck(DeckDetails deckDetails) {
        this.selectedDeck.setValue(deckDetails);
    }

    /**
     * get the details object for the currently selected deck
     * @return LiveData for the details for the deck
     */
    public LiveData<DeckDetails> getDeckDetails() {
        return this.selectedDeck;
    }

    /**
     * clears the currently selected deck
     */
    public void clearDeckSelection(){
        this.selectedDeck = null;
    }

    //Card in deck

    /**
     * removes the currently selected card from the deck
     * @throws UIException Thrown on invalid ui state for removing the card
     */
    public void removeSelectedCard() throws UIException {
        checkDeckSelected();
        if(!hasSelection()) {
            throw new UIException("No Card Selected");
        }

        //* Note: if this is null there is a problem in the backend
        IDeckBuilder builder = getDeckBuilder();

        builder.removeCard(selected);
    }

    /**
     * adds a card to the selected deck
     * @param card Card to add
     * @throws UIException thrown when the ui is in a bad state to add a card
     */
    public void addCardToDeck(Card card) throws UIException {
        checkDeckSelected();
        if (card == null) {
            throw new UIException("No Card Selected");
        }
        IDeckBuilder builder = getDeckBuilder();
        builder.addCard(card);
    }

    /**
     * get the validity of the currently selected deck
     * @return DeckValidity for the selected deck
     */
    public DeckValidity getValidity(){
        //! DO NOT CATCH THIS
        assert(this.selectedDeck != null); // this state should be unreachable by a user

        return getDeckBuilder().validate();
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

    /**
     * @return true iff there is currently a deck selected
     */
    public boolean hasSelectedDeck() {
        return this.selectedDeck != null;
    }

    // input validation helpers

    /**
     * shorthand for pre checking if there is a deck selected
     * @throws UIException thrown when there is no deck currently selected
     */
    private void checkDeckSelected() throws UIException {
        if (!hasSelectedDeck()) {
            throw new UIException("No Deck Selected");
        }
    }
}
