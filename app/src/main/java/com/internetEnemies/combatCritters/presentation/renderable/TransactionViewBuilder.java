package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

/**
 * TransactionViewBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Builder for Transaction views
 */
public class TransactionViewBuilder {
    private final Context context;
    private final MarketTransaction transaction;
    private final ViewGroup parent;
    private final ConstraintLayout container;

    public TransactionViewBuilder(ViewGroup parent, Context context, ConstraintLayout container, MarketTransaction transaction) {
        this.context = context;
        this.transaction = transaction;
        this.parent = parent;
        this.container = container;
    }

    /**
     * @return the created view
     */
    public View getView() {
        setItem();
        setCurrencyView();
        setDiscountView();

        return container;
    }

    /**
     * Add view for the inner item of the container. This view can either be a Card view, Pack view,
     * or bundle view.
     */
    private void setItem() {
        FrameLayout itemContainer = container.findViewById(R.id.item_container);

        ItemStack<?> itemStackReceived = transaction.getReceivedFirstItem();

        if(transaction.getReceived().size() == 1) { //It's a card or a pack.
            IItem item = itemStackReceived.getItem();
            RendererVisitor visitor = new RendererVisitor(context, itemContainer);
            item.accept(visitor);
        }
        else {  //It's a bundle!
            View bundleView = new BundleRenderer(transaction.getReceived(), context).getView(null, itemContainer);
            itemContainer.addView(bundleView);
        }
    }

    /**
     * Add the view for the cost of the transaction.
     */
    private void setCurrencyView() {
        Currency cost = transaction.getPrice();

        LinearLayout currencyContainer = container.findViewById(R.id.currency_container);

        CurrencyRenderer currencyRenderer = new CurrencyRenderer(cost, context);
        currencyRenderer.setWidth(50);
        currencyRenderer.setHeight(50);
        currencyRenderer.setTextSize(10);
        View currencyView = currencyRenderer.getView(null, parent);

        currencyContainer.removeAllViews();
        currencyContainer.addView(currencyView);
    }

    /**
     * Add the view for the discount of the transaction. If there is no discount add anything.
     */
    private void setDiscountView() {
        if(transaction.getDiscount() != 0) {
            TextView discount = container.findViewById(R.id.item_discount);
            double percentageOff = transaction.getPercentageOff();
            String formattedPercentage = String.format("%.2f%% off!", percentageOff);
            discount.setText(formattedPercentage);
        }
    }
}
