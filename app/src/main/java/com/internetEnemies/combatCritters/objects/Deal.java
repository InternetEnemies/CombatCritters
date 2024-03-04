package com.internetEnemies.combatCritters.objects;

public class Deal {
    private IItem item;
    private int percentageOff;

    public Deal(IItem item, int percentageOff) {
        this.item = item;
        this.percentageOff = percentageOff;
    }

    public IItem getItem() {
        return item;
    }

    public int getPercentageOff() {
        return percentageOff;
    }
}
