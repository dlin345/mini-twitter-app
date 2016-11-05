package edu.cpp.cs585.mini_twitter_app;

import java.util.HashMap;
import java.util.Map;

import edu.cpp.cs585.mini_twitter_visitor.Visitor;

/**
 * GroupUser is the Composite of Composite design pattern.
 * GroupUser is an Observer of Observer design pattern.
 * GroupUser accepts visitors of Visitor design pattern.
 *
 *  - assume cannot follow GroupUser
 *
 * @author delin
 *
 */

public class GroupUser extends User {

    private Map<String,User> groupUsers;

    /**
     * GroupUser icon is only distinguished from SingleUser after a User
     * is added to the GroupUser.
     */
    public GroupUser(String id) {
        super(id);
        groupUsers = new HashMap<String,User>();
    }

    public Map<String,User> getGroupUsers() {
        return groupUsers;
    }

    /**
     * Adds {@link User} to list of {@link User}s in this {@link GroupUser}
     * if not already present.
     */
    public User addUserInGroup(User user) {
        if (!this.contains(user.getID())) {
            this.groupUsers.put(user.getID(), user);
        }
        return this;
    }

    /*
     * Composite methods
     */

    /**
     * Checks if this {@link GroupUser} contains specified User ID.
     */
    @Override
    public boolean contains(String id) {
        boolean contains = false;
        for (User user : groupUsers.values()) {
            if (user.contains(id)) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Returns number of {@link SingleUser}s in the {@link GroupUser}.
     */
    @Override
    public int getSingleUserCount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            count += user.getSingleUserCount();
        }
        return count;
    }

    /**
     * Returns number of {@link GroupUser}s that are descendants of
     * this {@link GroupUser}.  Group count does not include {@code this}
     * {@link GroupUser}.
     */
    @Override
    public int getGroupUserCount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                ++count;
                count += user.getGroupUserCount();
            }
        }
        return count;
    }

    /**
     * Returns total number of messages sent by members of this {@link GroupUser}.
     */
    @Override
    public int getMessageCount() {
        int msgCount = 0;
        for (User user : this.groupUsers.values()) {
            msgCount += user.getMessageCount();
        }
        return msgCount;
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
        for (User user : groupUsers.values()) {
            ((Observer) user).update(subject);
        }
    }

    /*
     * Visitor methods
     */

    @Override
    public void accept(Visitor visitor) {
        for (User user : groupUsers.values()) {
            user.accept(visitor);
        }
        visitor.visitGroupUser(this);
    }

    /*
     * Private methods
     */

    /**
     * Returns true if this GroupUser contains one or more
     * GroupUser as a descendant.
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
