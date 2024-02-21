package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;

abstract public class ItemRenderer<T> implements IRenderable {
    private final T item;
    private final Context context;
    public ItemRenderer(T item, Context context){
        this.item = item;
        this.context = context;
    }

    public T getItem() {
        return item;
    }

    protected Context getContext(){
        return this.context;
    }
}
