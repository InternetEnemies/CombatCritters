package com.combatcritters.critterspring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;

public class TestUtils {
    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
    
    public static CritterCard getDummyCritter(int id){
        return new CritterCard(id, "name", "image", 0, Card.Rarity.COMMON,1,1, List.of());
    }
}
