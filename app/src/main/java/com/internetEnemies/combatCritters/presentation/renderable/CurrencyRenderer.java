package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.Currency;

/**
 * CurrencyRenderer.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Provides the view for Currency.
 */
public class CurrencyRenderer extends ItemRenderer<Currency>{
    public CurrencyRenderer(Currency currency, Context context) {
        super(currency, context);
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        ItemViewVisitor itemViewVisitor = new ItemViewVisitor(getContext(), parent);
        this.getItem().accept(itemViewVisitor);
        return itemViewVisitor.getView();
    }
}
