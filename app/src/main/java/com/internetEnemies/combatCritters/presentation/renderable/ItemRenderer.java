package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;

/**
 * ItemRenderer.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    abstract class for renderable items
 */
abstract public class ItemRenderer<T> implements IRenderable {
    private final T item;
    private final Context context;

    public ItemRenderer(T item, Context context){
        this.item = item;
        this.context = context;
    }

    /**
     * get the item from this ItemRenderer
     * @return T item from this ItemRenderer
     */
    public T getItem() {
        return item;
    }

    /**
     * get the context of this ItemRenderer for the ui
     * @return Context of the Renderer
     */
    protected Context getContext(){
        return this.context;
    }
}
