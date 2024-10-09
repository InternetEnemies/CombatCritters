package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.users.IProfileManager;
import com.internetEnemies.combatCritters.Logic.users.ProfileManager;
import com.internetEnemies.combatCritters.data.IProfilesDB;
import com.internetEnemies.combatCritters.objects.UserProfile;
import org.junit.Before;
import org.junit.Test;

public class ProfileManagerTest {
    private IProfileManager profileManager;
    private IProfilesDB profilesDB;
    @Before
    public void setup(){
        profilesDB = mock(IProfilesDB.class);
        profileManager = new ProfileManager(profilesDB);
    }
    
    @Test
    public void test_getProfile(){
        when(profilesDB.getProfile()).thenReturn(new UserProfile(0));
        assertNotNull(profileManager.getProfile());
    }
    
    @Test
    public void test_updateProfile(){
        UserProfile userProfile = new UserProfile(15);
        profileManager.updateProfile(userProfile);
        verify(profilesDB).updateProfile(userProfile);
        when(profilesDB.getProfile()).thenReturn(userProfile);
        assertEquals(userProfile, profileManager.getProfile());
    }
}
