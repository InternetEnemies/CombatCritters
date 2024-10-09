package com.combatcritters.critterspring.payloads;

import com.combatcritters.critterspring.payloads.decks.DeckDetailsPayload;
import com.internetEnemies.combatCritters.objects.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * ProfilePayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    payload for profile model
 */
public record ProfilePayload(DeckDetailsPayload featured_deck) {
    public UserProfile toUserProfile() {
        int deckId;
        if (featured_deck != null) {
            if(featured_deck.deckid().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            deckId = featured_deck.deckid().get();
        } else {
            deckId = 0;
        }
        return new UserProfile(deckId);
    }
}
