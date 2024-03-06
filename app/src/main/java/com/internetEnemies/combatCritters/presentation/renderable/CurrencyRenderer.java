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

public class CurrencyRenderer extends ItemRenderer<Currency>{

    private final Currency currency;
    private int width;
    private int height;

    public CurrencyRenderer(Currency currency, Context context) {
        super(currency, context);
        this.currency = currency;

        width = 100;   //Default image width
        height = 100;  //Default image height
    }

    // Setters for width and height
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View currencyView = inflater.inflate(R.layout.currency, parent, false);

        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        ImageView currencySymbolImageView = currencyView.findViewById(R.id.currencySymbolImageView);

        currencyTextView.setText(String.valueOf(currency.getAmount()));
        currencyTextView.setTextSize(width/2);

        // Adjust ImageView size
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(this.width, this.height);
        currencySymbolImageView.setLayoutParams(params);
        currencySymbolImageView.setImageResource(R.drawable.currency_symbol); // Make sure the drawable exists

        return currencyView;
    }
}
