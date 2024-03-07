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
    private int imageWidth;   //Image width in px
    private int imageHeight;  //Image height in px
    private int textSize;     //Text size in px

    public CurrencyRenderer(Currency currency, Context context) {
        super(currency, context);
        this.currency = currency;
        imageWidth = 100;   //Default image width
        imageHeight = 100;  //Default image height
        textSize = 25;      //Default text size
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        View currencyView = inflater.inflate(R.layout.currency, parent, false);

        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setText(String.valueOf(currency.getAmount()));
        currencyTextView.setTextSize(textSize);

        ImageView currencySymbolImageView = currencyView.findViewById(R.id.currencySymbolImageView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(this.imageWidth, this.imageHeight);
        currencySymbolImageView.setLayoutParams(params);
        currencySymbolImageView.setImageResource(R.drawable.currency_symbol);

        return currencyView;
    }

    /**
     * @param width width to set the image to.
     */
    public void setWidth(int width) {
        this.imageWidth = width;
    }

    /**
     * @param height height to set the image to.
     */
    public void setHeight(int height) {
        this.imageHeight = height;
    }

    /**
     * @param size size to set set the text.
     */
    public void setTextSize(int size) {this.textSize = size;}
}
