package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * BundleRenderer.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Provides the view for bundles. Bundles are of the form List<ItemStack<?>>.
 */
public class BundleRenderer extends ItemRenderer<List<ItemStack<?>>>{
    public BundleRenderer(List<ItemStack<?>> items, Context context) {
        super(items,context);
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        BundleViewBuilder builder = new BundleViewBuilder(this.getContext(), parent);
        BundleNameBuilderVisitor bundleVisitor = new BundleNameBuilderVisitor();

        for (ItemStack<?> itemStack : this.getItem()) {
            IItem item = itemStack.getItem();
            item.accept(bundleVisitor);
        }

        builder.setContents(bundleVisitor.getBundleName());
        return builder.getBundleView();
    }
}
