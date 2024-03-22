package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.List;
/**
 * TradeTransactionAdapter.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     22-March-2024
 *
 * @PURPOSE:     Adapter for trade trade transactions. Provides a callback for when the deal button
 *               is pressed.
 */
public class TradeTransactionAdapter extends RecyclerView.Adapter<TradeTransactionAdapter.ViewHolder> {
    private final List<TradeTransaction> transactions;
    private Context context;
    private final IDealButtonListener listener;

    public TradeTransactionAdapter(List<TradeTransaction> transactions, IDealButtonListener listener) {
        this.transactions = transactions;
        this.listener = listener;
    }

    /**
     * @PURPOSE:     Callback for handling deal button clicks.
     */
    public interface IDealButtonListener {
        void onDealButtonClicked(TradeTransaction transaction);
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
        holder.givenRecyclerView.setAdapter(givenItemsAdapter);
        holder.givenRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.givenRecyclerView.addItemDecoration(new SpacingItemDecoration(10, 10));

        TradeItemAdapter receivedItemsAdapter = new TradeItemAdapter(transaction.getReceived());
        holder.receivedRecyclerView.setAdapter(receivedItemsAdapter);
        holder.receivedRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.receivedRecyclerView.addItemDecoration(new SpacingItemDecoration(10, 10));

        holder.dealButton.setOnClickListener(view -> {
            if (listener != null) {
                listener.onDealButtonClicked(transaction);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView givenRecyclerView;
        RecyclerView receivedRecyclerView;
        Button dealButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            givenRecyclerView = itemView.findViewById(R.id.givenRecyclerView);
            receivedRecyclerView = itemView.findViewById(R.id.receivedRecyclerView);
            dealButton = itemView.findViewById(R.id.dealButton);
        }
    }
}