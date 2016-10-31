package edu.cpp.cs585.mini_twitter_gui;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;

public class AdminControlPanel extends ControlPanel {

	private static JFrame frame;
	private static AdminControlPanel INSTANCE;
	
    public static void addComponentsToPane() {
    	frame = new JFrame("Mini-Twitter App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        JButton button;

        // Tree Panel test
        User root = new GroupUser("Root");
        ((GroupUser)root).addUser(new SingleUser("Single User 1"));
        ((GroupUser)root).addUser(new SingleUser("Single User 2"));
        User group1 = new GroupUser("Group User 1");
        ((GroupUser)group1).addUser(new SingleUser("Single User 3"));
        ((GroupUser)root).addUser(group1);
        User group = new GroupUser("Group User 2");
        ((GroupUser)group).addUser(new SingleUser("Single User 4"));
        ((GroupUser)group).addUser(new SingleUser("Single User 5"));
        ((GroupUser)group1).addUser(group);
        
        JPanel treePanel = new TreePanel(root);
        addComponent(frame, treePanel, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        JPanel addUserPanel = new AddUserPanel();
        addComponent(frame, addUserPanel, 1, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        JPanel openUserPanel = new OpenUserViewPanel();
        addComponent(frame, openUserPanel, 1, 2, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        JPanel showInfoPanel = new ShowInfoPanel();
        addComponent(frame, showInfoPanel, 1, 4, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
    
	public static AdminControlPanel getInstance() {
		if (INSTANCE == null) {
			synchronized (Driver.class) {
				if (INSTANCE == null) {
					INSTANCE = new AdminControlPanel();
				}
			}
		}
		return INSTANCE;
	}
}
