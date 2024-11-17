package com.combatcritters.critterspring.payloads.market;

import com.combatcritters.critterspring.payloads.itemConverter.IItemConverter;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OfferCreator.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    creation object for offers
 */
public record OfferCreatorPayload(int level, OfferCreationItemPayload recv_item, List<OfferCreationItemPayload> send_items) {
    /**
     * get a VendorOfferCreator object from the payload
     */
    public VendorOfferCreator toOfferCreator(IItemConverter converter){
        ItemStack<?> recv = converter.getItem(recv_item);
        List<ItemStack<?>> send = send_items.stream().map(converter::getItem).collect(Collectors.toUnmodifiableList());
        
        return new VendorOfferCreator(recv, send, level);
    }
}
