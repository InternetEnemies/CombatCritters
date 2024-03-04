package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

public class BundleRenderer extends ItemRenderer<List<ItemStack<?>>>{
    public BundleRenderer(List<ItemStack<?>> items, Context context) {
        super(items,context);
    }
    @Override
    public View getView(View view, ViewGroup parent) {
        BundleViewBuilder builder = new BundleViewBuilder(this.getContext(), parent);
        BundleNameVisitor bundleVisitor = new BundleNameVisitor();

        for (ItemStack<?> itemStack : this.getItem()) {
            IItem item = itemStack.getItem();
            item.accept(bundleVisitor);
        }

        builder.setContents(bundleVisitor.getBundleName());
        return builder.getBundleView();
    }
}
