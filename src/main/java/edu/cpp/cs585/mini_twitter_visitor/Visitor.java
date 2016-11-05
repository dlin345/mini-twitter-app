package edu.cpp.cs585.mini_twitter_visitor;

import edu.cpp.cs585.mini_twitter_app.User;

/**
 * Interface to represent visitor for derived classes
 * of User for Visitor design pattern.
 *
 * @author delin
 *
 */

public interface Visitor {

    public int visitUser(User user);
    public int visitSingleUser(User user);
    public int visitGroupUser(User user);

}
