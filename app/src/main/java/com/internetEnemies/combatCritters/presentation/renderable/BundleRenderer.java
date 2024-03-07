package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
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
        super(items, context);
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        View bundleView = LayoutInflater.from(this.getContext()).inflate(R.layout.bundle, parent, false);
        BundleNameBuilderVisitor bundleVisitor = new BundleNameBuilderVisitor();

        for (ItemStack<?> itemStack : this.getItem()) {
            IItem item = itemStack.getItem();
            item.accept(bundleVisitor);
        }

        setContents(bundleView, bundleVisitor.getBundleName());
        return bundleView;
    }

    /**
     * Sets the String to be displayed in the bundle view.
     *
     * @param bundleView the View object representing the bundle.
     * @param contents the String to be displayed.
     */
    private void setContents(View bundleView, String contents) {
        TextView view = bundleView.findViewById(R.id.bundleContents);
        view.setText(contents);
    }
}
