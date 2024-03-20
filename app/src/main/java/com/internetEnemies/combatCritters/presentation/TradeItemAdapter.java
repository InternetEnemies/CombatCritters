package com.internetEnemies.combatCritters.presentation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.renderable.TradeItemViewVisitor;

import java.util.List;
/**
 * TradeItemAdapter.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     14-March-2024
 *
 * @PURPOSE:     Adapter for recyclerview. Accepts any IItem.
 */
public class TradeItemAdapter extends RecyclerView.Adapter<TradeItemAdapter.ViewHolder> {
    private final List<ItemStack<?>> itemStacks;
    private LinearLayout cardContainer;
    private LinearLayout currencyContainer;
    private TextView countText;

    public TradeItemAdapter(List<ItemStack<?>> itemStacks) {
        this.itemStacks = itemStacks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trade_item_container, parent, false);

        currencyContainer = view.findViewById(R.id.currencyContainer);
        cardContainer = view.findViewById(R.id.cardContainer);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemStack<?> itemStack = itemStacks.get(position);
        IItem item = itemStack.getItem();
        TradeItemViewVisitor itemViewVisitor = new TradeItemViewVisitor(holder.itemView.getContext(), holder.constraintLayout, itemStack.getAmount());
        item.accept(itemViewVisitor);
        View view = itemViewVisitor.getView();

//        cardContainer.removeAllViews();
//        cardContainer.addView(view);
        cardContainer.removeAllViews();
        cardContainer.addView(view);
    }

    @Override
    public int getItemCount() {
        return itemStacks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.tradeItemContainerParent);
        }
    }
}
