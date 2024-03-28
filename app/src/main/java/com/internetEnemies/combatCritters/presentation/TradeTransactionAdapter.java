package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.List;
/**
 * TradeTransactionAdapter.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     22-March-2024
 *
 * @PURPOSE:     Adapter for trade transactions. Provides a callback for when the deal button
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

        holder.traderImage.setImageResource(context.getResources().getIdentifier(transaction.getImage(),"drawable",context.getPackageName()));
        holder.traderName.setText(transaction.getName());

        setupRecycler(holder.givenRecyclerView, transaction.getGiven());
        setupRecycler(holder.receivedRecyclerView, transaction.getReceived());

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
        TextView traderName;
        ImageView traderImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            givenRecyclerView = itemView.findViewById(R.id.givenRecyclerView);
            receivedRecyclerView = itemView.findViewById(R.id.receivedRecyclerView);
            dealButton = itemView.findViewById(R.id.dealButton);
            traderName = itemView.findViewById(R.id.traderName);
            traderImage = itemView.findViewById(R.id.traderImage);
        }
    }

    private void setupRecycler(RecyclerView recycler, List<ItemStack<?>> items) {
        TradeItemAdapter adapter = new TradeItemAdapter(items);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recycler.addItemDecoration(new SpacingItemDecoration(10, 10));
    }
}