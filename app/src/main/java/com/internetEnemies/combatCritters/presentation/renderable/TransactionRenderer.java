package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRenderer extends ItemRenderer<Transaction>{
    private final Transaction transaction;
    public TransactionRenderer(Transaction transaction, Context context) {
        super(transaction, context);
        this.transaction = transaction;
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.item_market_container,parent,false);
        FrameLayout inner = container.findViewById(R.id.item_container);

        ItemStack<?> itemStackReceived = transaction.getReceivedFirstItem();
        ItemStack<?> itemStackGiven = transaction.getGivenFirstItem();

        if(itemStackReceived != null && itemStackGiven != null && itemStackGiven.getItem() instanceof Currency) {
            IItem item = itemStackReceived.getItem();           //Item for sale in transaction
            Currency cost = (Currency)itemStackGiven.getItem(); //Cost of item

            if(transaction.isCard()) {
                View cardView = new CardRenderer((Card)item,this.getContext()).getView(null,inner);
                inner.addView(cardView);
            }
            else if(transaction.isPack()) {
                View packView = new PackRenderer((Pack)item,this.getContext()).getView(null,inner);
                inner.addView(packView);
            }
            else if(transaction.isBundle()) {
                View bundleView = new BundleRenderer(transaction.getReceived(),this.getContext()).getView(null,inner);
                inner.addView(bundleView);
            }
            else if(transaction.isDeal()) {
                if(item instanceof Card) {
                    View cardView = new CardRenderer((Card)item,this.getContext()).getView(null,inner);
                    inner.addView(cardView);
                }
                else {
                    View packView = new PackRenderer((Pack)item,this.getContext()).getView(null,inner);
                    inner.addView(packView);
                }
            }
            TextView amount = container.findViewById(R.id.item_cost);
            amount.setText(cost.getAmount() + "CC");
        }
        return container;
    }

    /**
     * helper function for getting cards from card renderers
     * @param transactions list of transactions
     * @param context context for the view
     * @return List of TransactionRenderers
     */
    public static List<ItemRenderer<Transaction>> getRenderers( List<Transaction> transactions , Context context) {
        List<ItemRenderer<Transaction>> renderers = new ArrayList<>();
        for( Transaction transaction : transactions ){
            renderers.add(new TransactionRenderer(transaction, context));
        }
        return renderers;
    }
}
