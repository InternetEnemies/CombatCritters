package com.internetEnemies.combatCritters.objects;

public class Opponent {
    private String name;
    private String image;
    private String description;
    private int id;

    public Opponent(String name, String image, String description, int id) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.id = id;
    }

    public String getName() {return name;}
    public String getImage() {return image;}
    public String getDescription() {return description;}

    public int getId() {return id;}
}
