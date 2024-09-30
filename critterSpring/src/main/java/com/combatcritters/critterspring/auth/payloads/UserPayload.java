package com.combatcritters.critterspring.auth.payloads;

import com.internetEnemies.combatCritters.objects.User;

public record UserPayload(String username, int id) {
    public static UserPayload from(User user){
        return new UserPayload(user.getUsername(), user.getId());
    }
}
