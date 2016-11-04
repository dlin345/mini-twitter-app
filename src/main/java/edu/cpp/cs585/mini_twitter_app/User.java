package edu.cpp.cs585.mini_twitter_app;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * User represents the Component of Composite design pattern.
 * 
 * @author delin
 *
 */

public abstract class User extends DefaultMutableTreeNode {
	
	private String id;
	private int messageCount;
	
	public abstract boolean contains(String id);
	public abstract int getSingleUserCount();
	public abstract int getGroupUserCount();
	
	public User(String id) {
		super(id);
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
