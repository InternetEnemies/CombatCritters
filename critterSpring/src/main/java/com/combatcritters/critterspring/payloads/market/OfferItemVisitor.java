package com.combatcritters.critterspring.payloads.market;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.packs.PackPayload;
import com.internetEnemies.combatCritters.Logic.visitor.IItemVisitor;
import com.internetEnemies.combatCritters.objects.*;

/**
 * OfferItemVisitor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    create Offer items from IItems
 */
public class OfferItemVisitor implements IItemVisitor {
    private OfferItemPayload<?> result;
    private final int count;
    public OfferItemVisitor(int count) {
        this.count = count;
    }
    
    @Override
    public void visitCritterCard(CritterCard card) {
        visitCard(card);
    }

    @Override
    public void visitItemCard(ItemCard card) {
        visitCard(card);
    }
    
    private void visitCard(Card card){
        result = new OfferItemPayload<>(count, CardPayload.from(card),OfferItemPayload.TYPE_CARD);
    }

    @Override
    public void visitPack(Pack pack) {
        result = new OfferItemPayload<>(count, PackPayload.from(pack),OfferItemPayload.TYPE_PACK);
    }

    @Override
    public void visitCurrency(Currency currency) {
        result = new OfferItemPayload<>(count,null,OfferItemPayload.TYPE_CURRENCY);
    }
    
    public OfferItemPayload<?> getResult() {
        return result;
    }
}
