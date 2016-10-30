package edu.cpp.cs585.mini_twitter_app;

public abstract class User {
	
	private String id;

	public abstract boolean contains(String id);
	
	public User(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}
	
}
