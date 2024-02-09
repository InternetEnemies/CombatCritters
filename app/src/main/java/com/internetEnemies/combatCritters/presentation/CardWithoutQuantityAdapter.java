package com.internetEnemies.combatCritters.presentation;
import android.content.Context;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;


public class CardWithoutQuantityAdapter extends CardAdapter {
    public CardWithoutQuantityAdapter(Context context, List<Card> cardList) {
        super(context, cardList);
    }
}