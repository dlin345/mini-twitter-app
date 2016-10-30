package edu.cpp.cs585.mini_twitter_app;

import java.util.HashMap;
import java.util.Map;

public class GroupUser extends User {
	
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

	public void addUser(User user) {
		this.groupUsers.put(user.getID(), user);
	}
	
	public boolean contains(String id) {
		boolean contains = false;
		for (User user : groupUsers.values()) {
			if (user.contains(id)) {
				contains = true;
			}
		}
		return contains;
	}

}
