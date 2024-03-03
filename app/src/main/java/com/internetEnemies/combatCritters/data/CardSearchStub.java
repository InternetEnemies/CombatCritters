package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.data.cardOrder.ById;
import com.internetEnemies.combatCritters.data.cardOrder.ByName;
import com.internetEnemies.combatCritters.data.cardOrder.ByPlayCost;
import com.internetEnemies.combatCritters.data.cardOrder.ByRarity;
import com.internetEnemies.combatCritters.data.cardOrder.ICardComparator;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardSearchStub implements ICardSearch{

    public static final CardOrder DEFAULT_ORDER = CardOrder.ID;
    private final ICardInventory inventory;
    private final IRegistry<Card> cards;

    public CardSearchStub(ICardInventory inventory, IRegistry<Card> cards){
        this.inventory = inventory;
        this.cards = cards;
    }


    @Override
    public List<ItemStack<Card>> get(List<CardOrder> orders, CardFilter filter) {
        assert orders != null;
        assert orders.size() > 0;
        assert filter != null;

        List<ItemStack<Card>> result = getAll();
        CardFilterer filterer = new CardFilterer(this.inventory,filter);
        filterer.filter(result); // filter from full set

        //order the set
        for(CardOrder order : orders) {
            this.orderCards(result,order);
        }
        return result;
    } //! note that the way this is done here kinda sucks, SQL will be better

    /**
     * get map of all cards and owned quantities so we can filter and order them
     *
     * @return list of all cards and owned quantities
     */
    private List<ItemStack<Card>> getAll(){
        List<Card> all = new ArrayList<>(cards.getAll()); // get all cards
        List<ItemStack<Card>> inv = inventory.getCards(); // get owned cardstacks
        List<Card> invCards = inv.stream().map(ItemStack::getItem).collect(Collectors.toList());//convert card stacks to cards
        all.removeAll(invCards); // remove owned cards from all cards
        //convert all cards into card stacks
        List<ItemStack<Card>> result = new ArrayList<>();
        for(Card card : all) {
            result.add(new ItemStack<>(card));
        }
        result.addAll(inv); // add the cards that were removed
        return result;
    }

    private void orderCards(List<ItemStack<Card>> result, CardOrder order){
        ICardComparator comparator;
        switch (order) {
            case ID:
                comparator = new ById();
                break;
            case NAME:
                comparator = new ByName();
                break;
            case PLAY_COST:
                comparator = new ByPlayCost();
                break;
            case RARITY:
                comparator = new ByRarity();
                break;
            default:
                throw new RuntimeException("Invalid Order (how?????)");
        }
        result.sort(comparator);
    }
}
