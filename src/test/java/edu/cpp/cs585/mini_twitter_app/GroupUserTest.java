package edu.cpp.cs585.mini_twitter_app;

import java.util.List;
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
	
	@Before
	public void setup() {
		singleUser1 = new SingleUser("000000000");
		singleUser2 = new SingleUser("111111111");
		singleUser3 = new SingleUser("222222222");
		groupUser1 = new GroupUser("333333333");
		groupUser2 = new GroupUser("444444444");
	}
	
	@Test
	public void addUserTest_addSingleUser() {
		((GroupUser)groupUser1).addUser(singleUser1);
		
		Map<String,User> group = ((GroupUser)groupUser1).getGroupUsers();
		
		Assert.assertEquals(1, group.size());
	}
	
	@Test
	public void addUserTest_addGroupUser() {
		((GroupUser)groupUser1).addUser(groupUser2);
		
		Map<String,User> group = ((GroupUser)groupUser1).getGroupUsers();
		
		Assert.assertEquals(1, group.size());
	}
	
	@Test
	public void getUserTest_addSingleUser() {
		((GroupUser)groupUser1).addUser(singleUser1);
		
		Map<String,User> group = ((GroupUser)groupUser1).getGroupUsers();
		
		Assert.assertEquals(true, singleUser1 == group.get(singleUser1.getID()));
	}
	
	@Test
	public void getUserTest_addGroupUser() {
		((GroupUser)groupUser1).addUser(groupUser2);
		
		Map<String,User> group = ((GroupUser)groupUser1).getGroupUsers();
		
		Assert.assertEquals(true, groupUser2 == group.get(groupUser2.getID()));
	}

	@Test
	public void containsTest_SingleUsers_shouldPass() {
		((GroupUser)groupUser1).addUser(singleUser1);
		((GroupUser)groupUser1).addUser(singleUser2);
		((GroupUser)groupUser1).addUser(singleUser3);
		
		Assert.assertEquals(true, groupUser1.contains(singleUser3.getID()));
	}
	
	@Test
	public void containsTest_SingleUsersInGroup_shouldPass() {
		((GroupUser)groupUser2).addUser(singleUser1);
		((GroupUser)groupUser2).addUser(singleUser2);
		((GroupUser)groupUser2).addUser(singleUser3);
		((GroupUser)groupUser1).addUser(groupUser2);
		
		Assert.assertEquals(true, groupUser1.contains(singleUser2.getID()));
	}
	
}
