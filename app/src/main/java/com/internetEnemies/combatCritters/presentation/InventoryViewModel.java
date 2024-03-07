package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
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
    private static final boolean DEFAULT_SHOW_ALL = false;
    private static final CardOrder DEFAULT_ORDER = CardOrder.ID;
    private static final Card.Rarity DEFAULT_RARITY = null;


    private int selectedIdx;
    private final ICardCatalog cardCatalog;
    private final List<ISelectListener> selectListeners;
    private final MutableLiveData<Boolean> showAll;
    private final MutableLiveData<CardOrder> cardOrder;
    private final MutableLiveData<Card.Rarity> rarity;

    public InventoryViewModel() {
        super();
        this.cardCatalog = new CardCatalog();
        this.selectedIdx = -1;//-1 means no selection here
        this.selectListeners = new ArrayList<>();

        this.showAll = new MutableLiveData<>(DEFAULT_SHOW_ALL);
        this.cardOrder = new MutableLiveData<>(DEFAULT_ORDER);
        this.rarity = new MutableLiveData<>(DEFAULT_RARITY);
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
    public ItemStack<Card> getSelectedCard() throws UIException{
        if(this.selectedIdx < 0) throw new UIException("No Card Selected");
        ItemStack<Card> card;
        card = getCards().get(selectedIdx);
        return card;
    }


    /**
     * gets the list of cards the inventory is accessing
     * @return list of cards filtered by the filter state
     */
    public List<ItemStack<Card>> getCards(){
        List<Card.Rarity> rarities = new ArrayList<>();
        if (this.rarity.getValue() != null) {
            rarities.add(this.rarity.getValue());
        }
        CardFilter filter = new CardFilter(
                rarities.size() > 0, // if we hava a rarity we should whitelist
                rarities,
                this.showAll.getValue() == null || ! this.showAll.getValue(),
                null, // for now the front end doesn't support the filter by cost
                false
        );

        List<CardOrder> orders = new ArrayList<>();
        orders.add(this.cardOrder.getValue());

        return cardCatalog.get(filter,orders);
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


    /**
     * get the showAll boolean
     * @return Mutable boolean data for showAll state
     */
    public MutableLiveData<Boolean> getShowAll() {
        return showAll;
    }

    /**
     * get the current cardorder
     * @return LiveData of the CardOrder
     */
    public LiveData<CardOrder> getCardOrder() {
        return cardOrder;
    }

    /**
     * get the rarity filter state (null for no filter)
     * @return LiveData of the Card.Rarity filter
     */
    public LiveData<Card.Rarity> getRarity() {
        return rarity;
    }

    /**
     * set the ordering from a string
     * @param order string order type
     */
    public void setCardOrder(String order) {
        switch (order){
            case "Name":
                this.cardOrder.setValue(CardOrder.NAME);
                break;
            case "Rarity":
                this.cardOrder.setValue(CardOrder.RARITY);
                break;
            case "Play Cost":
                this.cardOrder.setValue(CardOrder.PLAY_COST);
                break;
            default: // default to default
            case "Default":
                this.cardOrder.setValue(CardOrder.ID);
                break;
        }
    }

    /**
     * set the rarity filter from a string
     * @param rarity string rarity filter type
     */
    public void setRarity(String rarity) {
        switch(rarity) {
            case "Common":
                this.rarity.setValue(Card.Rarity.COMMON);
                break;
            case "Uncommon":
                this.rarity.setValue(Card.Rarity.UNCOMMON);
                break;
            case "Rare":
                this.rarity.setValue(Card.Rarity.RARE);
                break;
            case "Epic":
                this.rarity.setValue(Card.Rarity.EPIC);
                break;
            case "Legendary":
                this.rarity.setValue(Card.Rarity.LEGENDARY);
                break;
            default: // default to all
            case "All":
                this.rarity.setValue(null);
                break;
        }
    }
}