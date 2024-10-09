package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.users.IProfileManager;
import com.internetEnemies.combatCritters.Logic.users.ProfileManager;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.data.IProfilesDB;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.ProfilesHSQLDB;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.UserProfile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ProfileManagerITest {
    IProfileManager profileManager;
    IProfilesDB profilesDB;
    IDeckInventory deckInventory;
    
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        this.profilesDB = new ProfilesHSQLDB(path, dummy);
        this.profileManager = new ProfileManager(this.profilesDB);
        this.deckInventory = new DeckInventoryHSQLDB(path, dummy);
    }
    
    @Test
    public void test_getEmptyProfile(){
        UserProfile profile = this.profileManager.getProfile();
        assertNotNull(profile);
        assertEquals(0, profile.deckId());
    }
    
    @Test
    public void test_updateAndGet(){
        DeckDetails details = this.deckInventory.createDeck("deckname").getInfo();
        UserProfile profile = new UserProfile(details.getId());
        this.profileManager.updateProfile(profile);
        UserProfile actual = this.profileManager.getProfile();
        assertNotNull(actual);
        assertEquals(profile, actual);
    }
}
