package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DSOHelper {

    public static Card cardFromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final int playCost = rs.getInt("playCost");
        final int rarity = rs.getInt("rarity");
        final String type = rs.getString("type");

        Card card = null;

        List<Integer> abilities = new ArrayList<>();
        Card.Rarity rare = Card.Rarity.values()[rarity];

        switch(type){
            case "critter":
                final int damage = rs.getInt("damage");
                final int health = rs.getInt("health");
                final Integer ability = rs.getInt("abilities");
                abilities.add(ability);
                card = new CritterCard(id, name, image, playCost, rare, damage, health, abilities);
                break;
            case "item":
                final int effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rare, effectId);
                break;
        }
        return card;
    }
}
