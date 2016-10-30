package edu.cpp.cs585.mini_twitter_app;

import java.util.ArrayList;
import java.util.List;

public class SingleUser extends User {
	
	// Assume groups cannot follow or be followed
	private List<User> followers;
	private List<User> following;
	private List<String> newsFeed;

	public SingleUser (String id) {
		super(id);
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		newsFeed = new ArrayList<String>();
	}

	public List<User> getFollowers() {
		return followers;
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	/**
	 * Adds specified {@link User} (not user group) to follow.
	 * @param following
	 */
	public boolean addFollowing(User following) throws IllegalArgumentException {
		boolean added = false;
		if (following.getClass() != GroupUser.class) {
			added = getFollowing().add(following);
			((SingleUser)following).addFollower(this);
		} else {
			throw new IllegalArgumentException("Cannot follow group");
		}
		return added;
	}
	
	public List<String> getNewsFeed() {
		return newsFeed;
	}

	public void addToNewsFeed(String news) {
		this.newsFeed.add(news);
	}
	
	/**
	 * Adds specified {@link User} as follower.
	 * @param follower
	 */
	private void addFollower(User follower) {
		this.getFollowers().add(follower);
	}

	@Override
	public boolean contains(String id) {
		return this.getID().equals(id);
	}

}
