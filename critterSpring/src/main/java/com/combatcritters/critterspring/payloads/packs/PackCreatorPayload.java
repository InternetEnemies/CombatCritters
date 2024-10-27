package com.combatcritters.critterspring.payloads.packs;

import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public record PackCreatorPayload(List<Integer> contents, PackPayload pack_details, List<CardSlotPayload> slots) {
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
