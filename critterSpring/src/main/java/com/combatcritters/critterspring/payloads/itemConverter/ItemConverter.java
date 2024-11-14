package com.combatcritters.critterspring.payloads.itemConverter;

import com.combatcritters.critterspring.payloads.market.OfferCreationItemPayload;
import com.combatcritters.critterspring.payloads.market.OfferCreatorPayload;
import com.combatcritters.critterspring.payloads.market.OfferItemPayload;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackCatalog;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * ItemConverter.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    convert item payloads to item stacks
 */
public class ItemConverter implements IItemConverter {

    private final IPackCatalog packRegistry;
    private final ICardRegistry cardRegistry;

    public ItemConverter(IPackCatalog packCatalog, ICardRegistry cardRegistry) {
        this.packRegistry = packCatalog;
        this.cardRegistry = cardRegistry;
    }
    @Override
    public ItemStack<?> getItem(OfferCreationItemPayload itemPayload) {
        ItemStack<?> stack = switch(itemPayload.type()){
            case OfferItemPayload.TYPE_PACK -> doForPack(itemPayload);
            case OfferItemPayload.TYPE_CARD -> doForCard(itemPayload);
            case OfferItemPayload.TYPE_CURRENCY -> doForCurrency(itemPayload);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Item Type");
        };
        if(stack.getItem() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Item ID");
        return stack;
    }

    private ItemStack<?> doForCurrency(OfferCreationItemPayload itemPayload) {
        return new ItemStack<>(new Currency(itemPayload.count()),1);
    }

    private ItemStack<?> doForCard(OfferCreationItemPayload itemPayload) {
        return new ItemStack<IItem>(cardRegistry.getCard(itemPayload.id()), itemPayload.count());
    }

    private ItemStack<?> doForPack(OfferCreationItemPayload itemPayload) {
        return new ItemStack<>(packRegistry.getPack(itemPayload.id()), itemPayload.count());
    }
}
