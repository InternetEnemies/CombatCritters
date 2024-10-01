package com.combatcritters.critterspring.payloads;

import com.internetEnemies.combatCritters.Logic.visitor.ICardVisitor;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

public class CardPayloadFactory implements ICardVisitor {
    
    private CardPayload<?> payload;

    @Override
    public void visitItemCard(ItemCard card) {
        visitCard(card, new CardItem(card.getEffectId()), CardPayload.TYPE_ITEM);
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        visitCard(card, new CardCritter(card.getDamage(),card.getHealth(), card.getAbilities()),CardPayload.TYPE_CRITTER);
    }
    
    private void visitCard(Card card, Object type_spec, String type){
        this.payload = new CardPayload<>(card.getId(),card.getName(),card.getPlayCost(),card.getRarity().ordinal(),card.getImage(),type,"PLACEHOLDER",type_spec);
    }

    public CardPayload<?> getPayload() {
        return payload;
    }
}
