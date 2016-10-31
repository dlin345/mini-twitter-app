package edu.cpp.cs585.mini_twitter_app;

import java.util.HashMap;
import java.util.Map;

public class GroupUser extends User {
	
	// Assume groups cannot follow or be followed
	private Map<String,User> groupUsers;
	
	public GroupUser(String id) {
		super(id);
		groupUsers = new HashMap<String,User>();
	}

	public Map<String,User> getGroupUsers() {
		return groupUsers;
	}
	
	public User getUser(int index) {
		return groupUsers.get(index);
	}

	/**
	 * Adds {@link User} to {@link GroupUser} if not already present.
	 * @param user
	 */
	public void addUser(User user) {
		if (!this.contains(user.getID())) {
			this.groupUsers.put(user.getID(), user);
		}
	}
	
	public boolean contains(String id) {
		boolean contains = false;
		for (User user : groupUsers.values()) {
			if (user.getID().equals(id)) {
				contains = true;
			} else if (user.getClass() == GroupUser.class) {
				if (user.contains(id)) {
					contains = true;
				}
			}
		}
		return contains;
	}
	
	public int getSingleUserCount() {
		int count = 0;
		for (User user : this.groupUsers.values()) {
			if (user.getClass() == SingleUser.class) {
				++count;
			} else {
				count += ((GroupUser)user).getSingleUserCount();
			}
		}
		return count;
	}
	
	/**
	 * Group count does not include {@code this} {@link GroupUser}.
	 * @return
	 */
	public int getGroupUserCount() {
		int count = 0;
		for (User user : this.groupUsers.values()) {
			if (user.getClass() == GroupUser.class) {
				++count;
				if (((GroupUser)user).containsGroupUser()) {
					count += ((GroupUser)user).getGroupUserCount();
				}
			}
		}
		return count;
	}
	
	/*
	 * Private methods
	 */
	
	private boolean containsGroupUser() {
		boolean containsGroup = false;
		for (User user : this.groupUsers.values()) {
			if (user.getClass() == GroupUser.class) {
				containsGroup = true;
			}
		}
		return containsGroup;
	}

}
