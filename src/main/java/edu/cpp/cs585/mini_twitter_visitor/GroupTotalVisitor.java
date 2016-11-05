package edu.cpp.cs585.mini_twitter_visitor;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;

/**
 * Concrete visitor for obtaining total number of GroupUser
 * under the specified User.  Excludes selected User in
 * total count.
 *
 * @author delin
 *
 */

public class GroupTotalVisitor implements Visitor {

    @Override
    public int visitUser(User user) {
        int count = 0;

        if (user.getClass() == SingleUser.class) {
            count += visitSingleUser(user);
        } else if (user.getClass() == GroupUser.class) {
            count += visitGroupUser(user);
        }

        return count;
    }

    @Override
    public int visitSingleUser(User user) {
        return 0;
    }

    @Override
    public int visitGroupUser(User user) {
        int count = 0;

        for (User u : ((GroupUser) user).getGroupUsers().values()) {
            if (u.getClass() == GroupUser.class) {
                ++count;
            }
            count += visitUser(u);
        }

        return count;
    }

}
