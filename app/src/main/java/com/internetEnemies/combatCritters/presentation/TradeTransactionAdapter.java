package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.presentation.renderable.TradeItemViewVisitor;

import java.util.List;
/**
 * TradeItemAdapter.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     14-March-2024
 *
 * @PURPOSE:     Adapter for recyclerview. Accepts any ItemStack.
 */
public class TradeTransactionAdapter extends RecyclerView.Adapter<TradeTransactionAdapter.ViewHolder> {
    private final List<TradeTransaction> transactions;
    private Context context;

    public TradeTransactionAdapter(List<TradeTransaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.trade_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TradeTransaction transaction = transactions.get(position);


        TradeItemAdapter givenItemsAdapter = new TradeItemAdapter(transaction.getGiven());
        TradeItemAdapter receivedItemsAdapter = new TradeItemAdapter(transaction.getReceived());

        holder.givenRecyclerView.setAdapter(givenItemsAdapter);
        holder.receivedRecyclerView.setAdapter(receivedItemsAdapter);

        holder.givenRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.givenRecyclerView.addItemDecoration(new SpacingItemDecoration(10, 0));

        holder.receivedRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.receivedRecyclerView.addItemDecoration(new SpacingItemDecoration(10, 0));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        RecyclerView givenRecyclerView;
        RecyclerView receivedRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.tradeItemContainerParent);
            givenRecyclerView = itemView.findViewById(R.id.givenRecyclerView);
            receivedRecyclerView = itemView.findViewById(R.id.receivedRecyclerView);
        }
    }
}