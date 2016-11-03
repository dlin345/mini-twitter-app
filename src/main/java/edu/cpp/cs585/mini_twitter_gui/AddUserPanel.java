package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;

public class AddUserPanel extends ControlPanel {

	private JPanel treePanel;
	private Map<String, User> allUsers;
	
	private JButton addUserButton;
	private JButton addGroupButton;
	private JTextField userId;
	private JTextField groupId;
	
	/**
	 * Create the panel.
	 */
	public AddUserPanel(JPanel treePanel, Map<String, User> allUsers) {
		super();
		this.treePanel = treePanel;
		this.allUsers = allUsers;
		
		initializeComponents();
		addComponents();
	}
	
	/*
	 * Private methods 
	 */
	
    private void addComponents() {
	    addComponent(this, userId, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    addComponent(this, addUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    addComponent(this, groupId, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    addComponent(this, addGroupButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    private void initializeComponents() {
    	userId = new JTextField("User ID");
    	groupId = new JTextField("Group ID");
    	
    	addUserButton = new JButton("Add User");
    	initializeAddUserButtonActionListener();
    	
    	addGroupButton = new JButton("Add Group");
    	initializeAddGroupButtonActionListener();
    }
    
    private void initializeAddUserButtonActionListener() {
		// TODO: add press enter functionality
    	addUserButton.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// check if user ID already exists
				if (!allUsers.containsKey(userId.getText())) {
					User child = new SingleUser(userId.getText());
					
					allUsers.put(child.getID(), child);
					((TreePanel)treePanel).addSingleUser(child);
				}	
				
			}
		});
	}

	private void initializeAddGroupButtonActionListener() {
		addGroupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// check if user ID already exists
				if (!allUsers.containsKey(groupId.getText())) {
					DefaultMutableTreeNode child = new GroupUser(groupId.getText());
					
					allUsers.put(((User) child).getID(), (User) child);
					((TreePanel)treePanel).addGroupUser(child);
				}
			}
		});
	}

}


