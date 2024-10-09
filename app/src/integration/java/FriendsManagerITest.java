import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.users.FriendsManager;
import com.internetEnemies.combatCritters.Logic.users.IFriendsManager;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.IFriendsDB;
import com.internetEnemies.combatCritters.data.hsqldb.FriendsHSQLDB;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FriendsManagerITest {
    IFriendsDB friendsDB;
    IFriendsManager friendsManager;
    IUserManager userManager;
    User dummy;
    String path;
    
    @Before
    public void setup() throws IOException {
        path = TestUtils.getDBPath();
        dummy = TestUtils.getDummyUser(path);//owner user
        this.friendsDB = new FriendsHSQLDB(path,dummy);
        this.friendsManager = new FriendsManager(friendsDB);
        this.userManager = new UserManager(new UsersDB(path));
    }
    
    @Test
    public void test_getNoFriends(){
        List<User> friends = friendsManager.getFriends();
        assertEquals(0, friends.size());
    }
    
    @Test
    public void test_addGetFriends(){
        List<User> friends = List.of(
                this.userManager.createUser("a","pass"),
                this.userManager.createUser("b","pass"),
                this.userManager.createUser("c","pass"),
                this.userManager.createUser("d","pass")
        );
        for( User friend : friends) {
            this.friendsManager.addFriend(friend);
            addDummyFriend(friend);
        }
        List<User> actual = friendsManager.getFriends();
        
        assertTrue(actual.containsAll(friends));
        assertEquals(0, friendsManager.getPendingFriends().size());
    }
    
    @Test
    public void test_addGetPending(){
        List<User> friends = List.of(
                this.userManager.createUser("a","pass"),
                this.userManager.createUser("b","pass"),
                this.userManager.createUser("c","pass"),
                this.userManager.createUser("d","pass")
        );
        for( User friend : friends) {
            addDummyFriend(friend);
        }
        List<User> actual = friendsManager.getPendingFriends();

        assertTrue(actual.containsAll(friends));
        assertEquals(0, friendsManager.getFriends().size());
    }
    
    private void addDummyFriend(User user) {
        IFriendsManager manager = new FriendsManager(new FriendsHSQLDB(path,user));
        manager.addFriend(this.dummy);
    }
}
