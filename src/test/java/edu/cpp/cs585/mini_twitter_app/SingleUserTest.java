package edu.cpp.cs585.mini_twitter_app;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleUserTest {
	
	private User singleUser1;
	private User singleUser2;
	private User singleUser3;
	private User groupUser;
	
	@Before
	public void setup() {
		singleUser1 = new SingleUser("000000000");
		singleUser2 = new SingleUser("111111111");
		singleUser3 = new SingleUser("222222222");
		groupUser = new GroupUser("333333333");
	}
	
	@Test
	public void addFollowingTest_addSingleUser_shouldPass() {
		Assert.assertEquals(true, ((SingleUser)singleUser1).addFollowing(singleUser2));
	}

	@Test
	public void addFollowingTest_addGroupUser_shouldFail() {
		try {
			((SingleUser)singleUser1).addFollowing(groupUser);
			Assert.fail("Expect IllegalArgumentException: Cannot follow group");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void getFollowingTest() {
		((SingleUser)singleUser1).addFollowing(singleUser2);
		
		List<User> following = ((SingleUser)singleUser1).getFollowing();
		
		Assert.assertEquals(1, following.size());
		Assert.assertEquals(singleUser2, following.get(0));
	}
	
	@Test
	public void getFollowersTest_singleFollower() {
		((SingleUser)singleUser1).addFollowing(singleUser2);
		
		List<User> followers = ((SingleUser)singleUser2).getFollowers();
		
		// includes self in list of followers since need to include own message in news feed
		Assert.assertEquals(2, followers.size());
		Assert.assertEquals(true, followers.contains(singleUser1));
	}
	
	@Test
	public void getFollowersTest_noFollowers() {
		List<User> followers = ((SingleUser)singleUser1).getFollowers();
		
		// includes self in list of followers since need to include own message in news feed
		Assert.assertEquals(1, followers.size());
		Assert.assertEquals(true, followers.contains(singleUser1));
	}
	
	@Test
	public void addToNewsFeedTest() {
		String message = "Test add to newsfeed";
		((SingleUser)singleUser1).addToNewsFeed(message);
		
		List<String> newsfeed = ((SingleUser)singleUser1).getNewsFeed();
		
		Assert.assertEquals(1, newsfeed.size());
		Assert.assertEquals(message, newsfeed.get(0));
	}
	
	@Test
	public void sendMessageTest_singleUsers() {
		((SingleUser)singleUser2).addFollowing(singleUser1);
		((SingleUser)singleUser3).addFollowing(singleUser1);
		
		String message = "Message from singleUser1";
		((SingleUser)singleUser1).sendMessage(message);
		
		Assert.assertEquals(1, ((SingleUser)singleUser2).getNewsFeed().size());
	}
	
	@Test
	public void sendMessageTest_checkMessageCount() {
		((SingleUser)singleUser2).addFollowing(singleUser1);
		((SingleUser)singleUser3).addFollowing(singleUser1);
		
		String message = "Message from singleUser1";
		((SingleUser)singleUser1).sendMessage(message);
		
		Assert.assertEquals(1, singleUser1.getMessageCount());
	}

}
