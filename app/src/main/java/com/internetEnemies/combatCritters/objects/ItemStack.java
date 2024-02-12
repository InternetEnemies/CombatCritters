package com.internetEnemies.combatCritters.objects;

/**
 * stores an object and the amount of that object
 * @param <T> type of object to store
 */
public class ItemStack<T> {
    private static final int START_AMOUNT = 0;

    private final T item;
    private final int amount;

    public ItemStack(T item){
        this(item,START_AMOUNT);
    }
    public ItemStack(T item, int startAmount){
        this.item = item;
        this.amount = startAmount;
    }

    public T getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
