package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.presentation.ItemViewVisitor;

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
