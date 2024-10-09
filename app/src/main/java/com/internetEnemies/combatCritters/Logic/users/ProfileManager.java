package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.data.IProfilesDB;
import com.internetEnemies.combatCritters.objects.UserProfile;

public class ProfileManager implements IProfileManager{
    private final IProfilesDB profilesDB;

    public ProfileManager(IProfilesDB profilesDB) {
        this.profilesDB = profilesDB;
    }
    
    @Override
    public UserProfile getProfile() {
        return profilesDB.getProfile();
    }

    @Override
    public void updateProfile(UserProfile profile) {
        profilesDB.updateProfile(profile);
    }
}
