package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRenderer extends ItemRenderer<MarketTransaction>{
    private final MarketTransaction transaction;
    public TransactionRenderer(MarketTransaction transaction, Context context) {
        super(transaction, context);
        this.transaction = transaction;
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.item_market_container,parent,false);
        FrameLayout inner = container.findViewById(R.id.item_container);

        ItemStack<?> itemStackReceived = transaction.getReceivedFirstItem();

        if(transaction.getReceived().size() == 1) {
            IItem item = itemStackReceived.getItem();
            RendererVisitor visitor = new RendererVisitor(this.getContext(), inner);
            item.accept(visitor);
        }
        else {
            View bundleView = new BundleRenderer(transaction.getReceived(), this.getContext()).getView(null, inner);
            inner.addView(bundleView);
        }

        Currency cost = transaction.getPrice();

        LinearLayout currencyContainer = container.findViewById(R.id.currency_container);

        Log.d("here", String.valueOf(currencyContainer.getHeight()));

        CurrencyRenderer currencyRenderer = new CurrencyRenderer(cost, getContext());
        currencyRenderer.setWidth(30);
        currencyRenderer.setHeight(30);
        View currencyView = currencyRenderer.getView(null, parent); // parent is your ViewGroup

        currencyContainer.removeAllViews(); // Clear any existing views
        currencyContainer.addView(currencyView);



        if(transaction.getDiscount() != 0) {
            TextView discount = container.findViewById(R.id.item_discount);
            discount.setText(String.valueOf(transaction.getPercentageOff()) + "% off!");
        }

        return container;
    }

    /**
     * helper function for getting cards from card renderers
     * @param transactions list of transactions
     * @param context context for the view
     * @return List of TransactionRenderers
     */
    public static List<ItemRenderer<MarketTransaction>> getRenderers( List<MarketTransaction> transactions , Context context) {
        List<ItemRenderer<MarketTransaction>> renderers = new ArrayList<>();
        for( MarketTransaction transaction : transactions ){
            renderers.add(new TransactionRenderer(transaction, context));
        }
        return renderers;
    }
}
