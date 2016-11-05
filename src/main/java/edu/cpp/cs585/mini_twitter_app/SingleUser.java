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
 *  - a message is declared positive if it contains one or more words from
 *      a predetermined set of words
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

    public SingleUser(String id) {
        super(id);
        followers = new HashMap<String, Observer>();
        followers.put(this.getID(), this);
        following = new HashMap<String, Subject>();
        newsFeed = new ArrayList<String>();
    }

    /**
     * Returns the SingleUser followers of this User.
     */
    public Map<String, Observer> getFollowers() {
        return followers;
    }

    /**
     * Returns the SingleUsers this User is following.
     */
    public Map<String, Subject> getFollowing() {
        return following;
    }

    /**
     * Returns the news feed of this User.
     */
    public List<String> getNewsFeed() {
        return newsFeed;
    }

    /**
     * Sends specified message to the news feeds of the
     * followers of this User, checks if message is positive,
     */
    public void sendMessage(String message) {
        this.latestMessage = message;
        this.setMessageCount(this.getMessageCount() + 1);

        if (isPositiveMessage(message)) {
            ++positiveMessageCount;
        }

        notifyObservers();
    }

    /**
     * Returns the most recent message sent by this User.
     */
    public String getLatestMessage() {
        return this.latestMessage;
    }

    /**
     * Returns the number of positive messages sent by
     * this User.
     */
    public int getPositiveMessageCount() {
        return positiveMessageCount;
    }

    /*
     * Composite methods
     */

    /**
     * Returns true if specified user ID matches
     * the user ID of this User.
     */
    @Override
    public boolean contains(String id) {
        return this.getID().equals(id);
    }

    /**
     * Returns the total number of GroupUsers contained
     * in this User.
     */
    @Override
    public int getGroupUserCount() {
        return 0;
    }

    /**
     * Returns the total number of SingleUsers contained
     * in this User.
     */
    @Override
    public int getSingleUserCount() {
        return 1;
    }

    /*
     * Observer methods
     */

    /**
     * Updates the news feed of this User with the most recent
     * message sent by the specified subject User.
     */
    @Override
    public void update(Subject subject) {
        newsFeed.add(0, (((SingleUser) subject).getID() + ": " + ((SingleUser) subject).getLatestMessage()));
    }

    /*
     * Subject methods
     */

    /**
     * Adds the specified observer User as a follower of
     * this subject User.
     */
    @Override
    public void attach(Observer observer) {
        addFollower(observer);
    }

    /**
     * Updates the observer Users that are followers
     * of this subject User.
     */
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
     */
    private void addFollower(Observer user) {
        this.getFollowers().put(((User) user).getID(), user);
        ((SingleUser) user).addUserToFollow(this);
    }

    /**
     * Adds specified {@link User} (not user group) to follow.
     */
    private void addUserToFollow(Subject toFollow){
        if (toFollow.getClass() == SingleUser.class) {
            getFollowing().put(((User) toFollow).getID(), toFollow);
        }
    }

    /**
     * Returns true if the specified message is positive.
     */
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
