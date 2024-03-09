package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * TransactionRenderer.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Provides the view for a MarketTransactions.
 */
public class TransactionRenderer extends ItemRenderer<MarketTransaction>{
    private final MarketTransaction transaction;

    public TransactionRenderer(MarketTransaction transaction, Context context) {
        super(transaction, context);
        this.transaction = transaction;
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.item_market_container,parent,false);

        setItem(container);
        setCurrencyView(container, parent);
        setDiscountView(container);
        return container;
    }

    /**
     * Add view for the inner item of the container. This view can either be a Card view, Pack view,
     * or bundle view.
     */
    private void setItem(ConstraintLayout container) {
        FrameLayout itemContainer = container.findViewById(R.id.item_container);

        ItemStack<?> itemStackReceived = transaction.getReceivedFirstItem();

        if(transaction.getReceived().size() == 1) { //It's a card or a pack.
            ItemViewVisitor itemViewVisitor = new ItemViewVisitor(getContext(), itemContainer);
            itemStackReceived.getItem().accept(itemViewVisitor);
            itemContainer.addView(itemViewVisitor.getView());
        }
        else {  //It's a bundle!
            View bundleView = new BundleRenderer(transaction.getReceived(), this.getContext()).getView(null, itemContainer);
            itemContainer.addView(bundleView);
        }
    }

    /**
     * Add the view for the cost of the transaction.
     */
    private void setCurrencyView(ConstraintLayout container, ViewGroup parent) {
        Currency cost = transaction.getPrice();

        LinearLayout currencyContainer = container.findViewById(R.id.currency_container);

        CurrencyRenderer currencyRenderer = new CurrencyRenderer(cost, this.getContext());
        View currencyView = currencyRenderer.getView(null, parent);

        currencyContainer.removeAllViews();
        currencyContainer.addView(currencyView);
    }

    /**
     * Add the view for the discount of the transaction. If there is no discount add anything.
     */
    private void setDiscountView(ConstraintLayout container) {
        if(transaction.getDiscount() != 0) {
            TextView discount = container.findViewById(R.id.item_discount);
            double percentageOff = transaction.getPercentageOff();
            String formattedPercentage = String.format("%.0f%% off!", percentageOff);
            discount.setText(formattedPercentage);
        }
    }

    /**
     * helper function for getting MarketTransaction from MarketTransaction renderers
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
