package edu.cpp.cs585.mini_twitter_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SingleUser represents the Leaf of the Composite design pattern.
 * SingleUser is both Subject and Observer of Observer design pattern.
 * 
 * @author delin
 *
 */

public class SingleUser extends User implements Subject, Observer {
	
	private Map<String, Observer> followers;
	private Map<String, Subject> following;
	private List<String> newsFeed;
	
	private String latestMessage;
	
	public SingleUser (String id) {
		super(id);
		followers = new HashMap<String, Observer>();
		followers.put(this.getID(), this);
		following = new HashMap<String, Subject>();
		newsFeed = new ArrayList<String>();
	}

	public Map<String, Observer> getFollowers() {
		return followers;
	}
	
	public Map<String, Subject> getFollowing() {
		return following;
	}
	
	public List<String> getNewsFeed() {
		return newsFeed;
	}

	public void addToNewsFeed(String news) {
		this.newsFeed.add(news);
	}
	
	public void sendMessage(String message) {
		this.latestMessage = message;
		this.setMessageCount(this.getMessageCount() + 1);
		notifyObservers();
	}
	
	public String getLatestMessage() {
		return this.latestMessage;
	}
	
	/*
	 * Composite methods
	 */
	
	@Override
	public boolean contains(String id) {
		return this.getID().equals(id);
	}
	
	@Override
	public int getGroupUserCount() {
		return 0;
	}

	@Override
	public int getSingleUserCount() {
		return 1;
	}
	
	/*
	 * Observer methods
	 */
	
	@Override
	public void update(Subject subject) {
		getNewsFeed().add(0, (((SingleUser) subject).getID() + ": " + ((SingleUser) subject).getLatestMessage()));
	}
	
	/*
	 * Subject methods
	 */

	@Override
	public void attach(Observer observer) {
		addFollower(observer);
	}
	
	@Override
	public void notifyObservers() {
		for (Observer obs : followers.values()) {
			obs.update(this);
		}
	}
	
	/*
	 * Private methods
	 */
	
	/**
	 * Adds specified {@link User} as follower.
	 * @param follower
	 */
	private void addFollower(Observer user) {
		this.getFollowers().put(((User) user).getID(), user);
		((SingleUser) user).addUserToFollow(this);
	}
	
	/**
	 * Adds specified {@link User} (not user group) to follow.
	 * @param following
	 */
	private void addUserToFollow(Subject toFollow){
		if (toFollow.getClass() == SingleUser.class) {
			getFollowing().put(((User) toFollow).getID(), toFollow);
		}
	}

}
