package com.internetEnemies.combatCritters.presentation.renderable;

abstract public class ItemRenderer<T> implements IRenderable {
    private final T item;
    public ItemRenderer(T item){
        this.item = item;
    }

    protected T getItem() {
        return item;
    }
}
