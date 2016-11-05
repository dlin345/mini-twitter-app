package edu.cpp.cs585.mini_twitter_visitor;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;

/**
 * Concrete visitor for obtaining the total number of 
 * SingleUser under the specified User.
 * 
 * @author delin
 *
 */

public class UserTotalVisitor implements Visitor {

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
		return 1;
	}

	@Override
	public int visitGroupUser(User user) {
		int count = 0;
		
		for (User u : ((GroupUser) user).getGroupUsers().values()) {
			count += visitUser(u);
		}
		
		return count;
	}

}
