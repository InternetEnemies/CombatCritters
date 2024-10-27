package com.combatcritters.critterspring.payloads.packs;

import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * PackCreatorPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/27/24
 * 
 * @PURPOSE:    represent pack creation payload for rest
 */
public record PackCreatorPayload(List<Integer> contents, PackPayload pack_details, List<CardSlotPayload> slots) {
    /**
     * create a pack from the pack creation payload
     * !!! this does not push to the database, it just creates an object that can be used to create the pack using PackCatalog
     * @param cardRegistry registry for cards in the pack
     * @return pack from the payload with id 0 (null)
     */
    public Pack toPack(ICardRegistry cardRegistry) {
        try {
            PackBuilder packBuilder = new PackBuilder();
            packBuilder.setImage(pack_details.image());
            packBuilder.setName(pack_details.name());
            packBuilder.addSetList(cardRegistry.getCards(contents));
            List<CardSlot> cardSlots = slots.stream().map(CardSlotPayload::toCardSlot).toList();
            for(CardSlot cardSlot : cardSlots){
                packBuilder.addSlot(cardSlot);
            }
            return packBuilder.build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid pack creator");
        }
    }
}
