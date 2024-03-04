package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public class BundleRenderer extends ItemRenderer<List<ItemStack<?>>>{
    public BundleRenderer(List<ItemStack<?>> items, Context context) {
        super(items,context);
    }
    @Override
    public View getView(View view, ViewGroup parent) {
        BundleViewBuilder builder = new BundleViewBuilder(this.getContext(), parent);

        StringBuilder bundleName = new StringBuilder();
        for (ItemStack<?> itemStack : this.getItem()) {
            IItem item = itemStack.getItem();
            bundleName.append("- ");
            if (item instanceof Card) {
                bundleName.append(((Card) item).getName());
            } else if (item instanceof Pack) {
                bundleName.append(((Pack) item).getName());
            }
            bundleName.append("\n");
        }
        builder.setContents(bundleName.toString());
        return builder.getBundleView();
    }
}
