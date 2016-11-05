package edu.cpp.cs585.mini_twitter_app;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupUserTest {

    private User singleUser1;
    private User singleUser2;
    private User singleUser3;
    private User groupUser1;
    private User groupUser2;
    private User groupUser3;

    @Before
    public void setup() {
        singleUser1 = new SingleUser("000000000");
        singleUser2 = new SingleUser("111111111");
        singleUser3 = new SingleUser("222222222");
        groupUser1 = new GroupUser("333333333");
        groupUser2 = new GroupUser("444444444");
        groupUser3 = new GroupUser("555555555");
    }

    @Test
    public void addUserTest_addSingleUser() {
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);

        Map<String,User> group = ((GroupUser) groupUser1).getGroupUsers();

        Assert.assertEquals(1, group.size());
    }

    @Test
    public void addUserTest_addGroupUser() {
        ((GroupUser) groupUser1).addUserInGroup(groupUser2);

        Map<String,User> group = ((GroupUser) groupUser1).getGroupUsers();

        Assert.assertEquals(1, group.size());
    }

    @Test
    public void getUserTest_addSingleUser() {
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);

        Map<String,User> group = ((GroupUser) groupUser1).getGroupUsers();

        Assert.assertEquals(true, singleUser1 == group.get(singleUser1.getID()));
    }

    @Test
    public void getUserTest_addGroupUser() {
        ((GroupUser) groupUser1).addUserInGroup(groupUser2);

        Map<String,User> group = ((GroupUser) groupUser1).getGroupUsers();

        Assert.assertEquals(true, groupUser2 == group.get(groupUser2.getID()));
    }

    @Test
    public void containsTest_SingleUsers_shouldPass() {
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);
        ((GroupUser) groupUser1).addUserInGroup(singleUser2);
        ((GroupUser) groupUser1).addUserInGroup(singleUser3);

        ((GroupUser) groupUser1).addUserInGroup(groupUser2);

        Assert.assertEquals(true, groupUser1.contains(singleUser3.getID()));
    }

    @Test
    public void containsTest_SingleUsersInGroup_shouldPass() {
        ((GroupUser) groupUser2).addUserInGroup(singleUser1);
        ((GroupUser) groupUser2).addUserInGroup(singleUser2);
        ((GroupUser) groupUser2).addUserInGroup(singleUser3);

        ((GroupUser) groupUser1).addUserInGroup(groupUser2);

        Assert.assertEquals(true, groupUser1.contains(singleUser3.getID()));
    }

    @Test
    public void getSingleUserCountTest_singleUsers() {
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);
        ((GroupUser) groupUser1).addUserInGroup(singleUser2);
        ((GroupUser) groupUser1).addUserInGroup(singleUser3);

        Assert.assertEquals(3, ((GroupUser) groupUser1).getSingleUserCount());
    }

    @Test
    public void getSingleUserCountTest_singleUsersInGroup() {
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);
        ((GroupUser) groupUser1).addUserInGroup(singleUser2);
        ((GroupUser) groupUser2).addUserInGroup(singleUser3);

        ((GroupUser) groupUser2).addUserInGroup(groupUser1);

        Assert.assertEquals(3, ((GroupUser) groupUser2).getSingleUserCount());
    }

    @Test
    public void getGroupUserCountTest_singleUsers() {
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);
        ((GroupUser) groupUser1).addUserInGroup(singleUser2);
        ((GroupUser) groupUser1).addUserInGroup(singleUser3);

        Assert.assertEquals(0, ((GroupUser) groupUser1).getGroupUserCount());
    }

    @Test
    public void getGroupUserCountTest_singleUsersInGroup() {
        ((GroupUser) groupUser3).addUserInGroup(singleUser1);
        ((GroupUser) groupUser3).addUserInGroup(singleUser2);
        ((GroupUser) groupUser3).addUserInGroup(singleUser3);
        ((GroupUser) groupUser2).addUserInGroup(groupUser3);

        ((GroupUser) groupUser1).addUserInGroup(groupUser2);

        Assert.assertEquals(2, ((GroupUser) groupUser1).getGroupUserCount());
    }

    @Test
    public void getMessageCountTest_singleUsers() {
        ((SingleUser) singleUser1).sendMessage("message 1, user 1");
        ((SingleUser) singleUser1).sendMessage("message 2, user 1");
        ((SingleUser) singleUser2).sendMessage("message 1, user 2");
        ((SingleUser) singleUser3).sendMessage("message 1, user 3");

        ((GroupUser) groupUser1).addUserInGroup(singleUser1);
        ((GroupUser) groupUser1).addUserInGroup(singleUser2);
        ((GroupUser) groupUser1).addUserInGroup(singleUser3);

        Assert.assertEquals(4, ((GroupUser) groupUser1).getMessageCount());
    }

    @Test
    public void getMessageCountTest_singleUsersInGroup() {
        ((SingleUser) singleUser1).sendMessage("message 1, user 1");
        ((SingleUser) singleUser1).sendMessage("message 2, user 1");
        ((SingleUser) singleUser2).sendMessage("message 1, user 2");
        ((SingleUser) singleUser3).sendMessage("message 1, user 3");

        ((GroupUser) groupUser1).addUserInGroup(groupUser2);
        ((GroupUser) groupUser1).addUserInGroup(singleUser1);
        ((GroupUser) groupUser2).addUserInGroup(singleUser2);
        ((GroupUser) groupUser2).addUserInGroup(singleUser3);

        Assert.assertEquals(4, ((GroupUser) groupUser1).getMessageCount());
    }

}
