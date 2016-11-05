package edu.cpp.cs585.mini_twitter_gui;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.Observer;
import edu.cpp.cs585.mini_twitter_app.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import java.awt.GridBagConstraints;

/**
 * Panel for main UI.
 * Contains TreePanel, AddUserPanel, OpenUserViewPanel, ShowInfoPanel.
 * 
 * @author delin
 *
 */

public class AdminControlPanel extends ControlPanel {

	private static AdminControlPanel INSTANCE;
	
	private static JFrame frame;
	private JPanel treePanel;
	private JPanel addUserPanel;
	private JPanel openUserViewPanel;
	private JPanel showInfoPanel;
	
	private DefaultMutableTreeNode root;
	private Map<String, Observer> allUsers;
	
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
	
	/*
	 * Private methods
	 */
	
	private AdminControlPanel() {
		super();
		
		initializeComponents();
		addComponents();
	}
	
	private void addComponents() {
		addComponent(frame, treePanel, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, addUserPanel, 1, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, openUserViewPanel, 1, 2, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, showInfoPanel, 1, 4, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
    
	private void initializeComponents() {
		frame = new JFrame("Mini-Twitter App");
		formatFrame();
		
		allUsers = new HashMap<String, Observer>();
		root = new GroupUser("Root");
		allUsers.put(((User)root).getID(), (Observer) this.root);
		
		treePanel = new TreePanel(root);
		addUserPanel = new AddUserPanel(treePanel, allUsers);
		openUserViewPanel = new OpenUserViewPanel(treePanel, allUsers);
		showInfoPanel = new ShowInfoPanel(treePanel);
	}

	private void formatFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(800, 400);
		frame.setVisible(true);
	}
    
}
