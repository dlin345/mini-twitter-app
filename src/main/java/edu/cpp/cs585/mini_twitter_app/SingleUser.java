package edu.cpp.cs585.mini_twitter_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cpp.cs585.mini_twitter_visitor.Visitor;

/**
 * SingleUser represents the Leaf of the Composite design pattern.
 * SingleUser represents both Subject and Observer of Observer design pattern.
 * SingleUser accepts visitors of Visitor design pattern.
 * 
 * @author delin
 *
 */

public class SingleUser extends User implements Subject {
	
	private static final List<String> POSITIVE_WORDS = Arrays.asList("good", "great", "excellent", "awesome");
	
	private Map<String, Observer> followers;
	private Map<String, Subject> following;
	private List<String> newsFeed;
	
	private String latestMessage;
	private int positiveMessageCount;
	
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

	public void sendMessage(String message) {
		this.latestMessage = message;
		this.setMessageCount(this.getMessageCount() + 1);
		
		if (isPositiveMessage(message)) {
			++positiveMessageCount;
		}
		
		notifyObservers();
	}
	
	public String getLatestMessage() {
		return this.latestMessage;
	}
	
	public int getPositiveMessageCount() {
		return positiveMessageCount;
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
		newsFeed.add(0, (((SingleUser) subject).getID() + ": " + ((SingleUser) subject).getLatestMessage()));
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
	 * Visitor methods
	 */
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visitSingleUser(this);
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
	
	private boolean isPositiveMessage(String message) {
		boolean positive = false;
		message = message.toLowerCase();
		for (String word : POSITIVE_WORDS) {
			if (message.contains(word)) {
				positive = true;
			}
		}
		return positive;
	}

}
