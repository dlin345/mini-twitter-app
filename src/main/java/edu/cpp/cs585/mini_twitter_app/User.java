package edu.cpp.cs585.mini_twitter_app;

public abstract class User {
	
	private String id;
	private int messageCount;

	public abstract boolean contains(String id);
	
	public User(String id) {
		this.id = id;
		this.setMessageCount(0);
	}

	public String getID() {
		return id;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	
}
