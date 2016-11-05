package edu.cpp.cs585.mini_twitter_app;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs585.mini_twitter_visitor.Visitor;

/**
 * User represents the Component of Composite design pattern.
 *
 * @author delin
 *
 */

public abstract class User extends DefaultMutableTreeNode implements Observer {

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

    /**
     * Returns the user ID of this User.
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the total number of messages sent by this User.
     */
    public int getMessageCount() {
        return messageCount;
    }

    /**
     * Sets the total number of messages sent by this User
     * to the specified integer.
     */
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    /*
     * Visitor methods
     */

    public abstract void accept(Visitor visitor);

}
