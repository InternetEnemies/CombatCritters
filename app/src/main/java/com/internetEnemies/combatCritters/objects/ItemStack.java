package com.internetEnemies.combatCritters.objects;

import java.util.Objects;

import java.io.Serializable;

/**
  * ItemStack.java
  * COMP 3350 A02
  * @Project     Combat Critters
  * @created     2024-03-02
  *
  * @PURPOSE:    represent a stack of items
  * @param <T> type of item to store
  */
public class ItemStack<T extends IItem> implements Serializable {
    private static final int START_AMOUNT = 0;

    private final T item;
    private final int amount;

     /**
      * default amount is zero
      * @param item item to store
      */
    public ItemStack(T item){
        this(item,START_AMOUNT);
    }

     /**
      * @param item item to store
      * @param startAmount amount of that item (defaults to zero if omitted)
      */
    public ItemStack(T item, int startAmount){
        this.item = item;
        this.amount = startAmount;
    }

     /**
      * @return the item stored in this stack
      */
    public T getItem() {
        return item;
    }

     /**
      * @return amount of items in this stack
      */
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStack)) return false;
        ItemStack<?> itemStack = (ItemStack<?>) o;
        return amount == itemStack.amount && item.equals(itemStack.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, amount);
    }
}
