package edu.cpp.cs585.mini_twitter_app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleUserTest {

    private Observer singleUser1;
    private Observer singleUser2;
    private Observer singleUser3;
    private Observer groupUser;

    @Before
    public void setup() {
        singleUser1 = new SingleUser("000000000");
        singleUser2 = new SingleUser("111111111");
        singleUser3 = new SingleUser("222222222");
        groupUser = new GroupUser("333333333");
    }

    @Test
    public void addUserToFollowTest_addSingleUser_shouldPass() {
        ((SingleUser) singleUser1).attach(singleUser2);

        Assert.assertEquals(singleUser2,
                ((SingleUser) singleUser1).getFollowers().get(((User) singleUser2).getID()));
    }

    @Test
    public void addUserToFollowTest_addGroupUser_shouldFail() {
        try {
            ((SingleUser) singleUser1).attach((Observer) groupUser);
            Assert.fail("Expect ClassCastException: Cannot follow group");
        } catch (ClassCastException e) {

        }
    }

    @Test
    public void getFollowingTest() {
        ((SingleUser) singleUser2).attach(singleUser1);

        List<Subject> following = new ArrayList<Subject>(((SingleUser) singleUser1).getFollowing().values());

        Assert.assertEquals(1, following.size());
        Assert.assertEquals(singleUser2, following.get(0));
    }

    @Test
    public void getFollowersTest_singleFollower() {
        ((SingleUser) singleUser2).attach(singleUser1);

        List<Observer> followers = new ArrayList<Observer>(((SingleUser) singleUser2).getFollowers().values());

        // includes self in list of followers since need to include own message in news feed
        Assert.assertEquals(2, followers.size());
        Assert.assertEquals(true, followers.contains(singleUser1));
    }

    @Test
    public void getFollowersTest_noFollowers() {
        List<Observer> followers = new ArrayList<Observer>(((SingleUser) singleUser1).getFollowers().values());

        // includes self in list of followers since need to include own message in news feed
        Assert.assertEquals(1, followers.size());
        Assert.assertEquals(true, followers.contains(singleUser1));
    }

    @Test
    public void sendMessageTest_singleUsers() {
        ((SingleUser) singleUser1).attach(singleUser2);
        ((SingleUser) singleUser1).attach(singleUser3);

        String message = "Message from singleUser1";
        ((SingleUser) singleUser1).sendMessage(message);

        System.out.println(((SingleUser) singleUser2).getNewsFeed());
        Assert.assertEquals(1, ((SingleUser) singleUser2).getNewsFeed().size());
        Assert.assertEquals(((User) singleUser1).getID() + ": " + message,
                ((SingleUser) singleUser2).getNewsFeed().get(0));
    }

    @Test
    public void sendMessageTest_checkMessageCount() {
        ((SingleUser) singleUser1).attach(singleUser2);
        ((SingleUser) singleUser1).attach(singleUser3);

        String message = "Message from singleUser1";
        ((SingleUser) singleUser1).sendMessage(message);

        Assert.assertEquals(1, ((User) singleUser1).getMessageCount());
    }

}
