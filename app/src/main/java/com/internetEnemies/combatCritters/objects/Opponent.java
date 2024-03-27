package com.internetEnemies.combatCritters.objects;

public class Opponent {
    private String name;
    private String image;
    private String description;

    public Opponent(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {return name;}
    public String getImage() {return image;}
    public String getDescription() {return description;}
}
