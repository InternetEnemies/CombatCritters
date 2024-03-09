package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;

/**
 * CurrencyViewBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     09/March/2024
 *
 * @PURPOSE:    Build the view for Currency objects.
 */
public class CurrencyViewBuilder {
    private final View currencyView;

    public CurrencyViewBuilder(View currencyView, int amount) {
        this.currencyView = currencyView;

        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setText(String.valueOf(amount));
    }

    public View getCurrencyView() {
        return currencyView;
    }
}
