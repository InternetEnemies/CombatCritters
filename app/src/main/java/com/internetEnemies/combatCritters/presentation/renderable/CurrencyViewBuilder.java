package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;

public class CurrencyViewBuilder {
    Context context;
    View currencyView;
    public CurrencyViewBuilder(Context context, View currencyView, int amount) {
        this.context = context;
        this.currencyView = currencyView;
        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setText(String.valueOf(amount));

        ImageView currencySymbolImageView = currencyView.findViewById(R.id.currencySymbolImageView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        currencySymbolImageView.setLayoutParams(params);
        currencySymbolImageView.setImageResource(R.drawable.currency_symbol);
    }

    public View getCurrencyView() {
        return currencyView;
    }
}
