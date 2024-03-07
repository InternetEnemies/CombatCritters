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

/**
 * CurrencyRenderer.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Provides the view for Currency.
 */
public class CurrencyRenderer extends ItemRenderer<Currency>{
    private final Currency currency;
    private final static int IMAGE_HEIGHT_WIDTH = 50;

    public CurrencyRenderer(Currency currency, Context context) {
        super(currency, context);
        this.currency = currency;
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        View currencyView = inflater.inflate(R.layout.currency, parent, false);

        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setText(String.valueOf(currency.getAmount()));

        ImageView currencySymbolImageView = currencyView.findViewById(R.id.currencySymbolImageView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(IMAGE_HEIGHT_WIDTH, IMAGE_HEIGHT_WIDTH);
        currencySymbolImageView.setLayoutParams(params);
        currencySymbolImageView.setImageResource(R.drawable.currency_symbol);

        return currencyView;
    }
}
