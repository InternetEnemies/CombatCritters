package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
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

        TransactionViewBuilder builder = new TransactionViewBuilder(parent, this.getContext(), container, transaction);

        return builder.getView();
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
