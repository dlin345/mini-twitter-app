package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs585.mini_twitter_app.Observer;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;
import edu.cpp.cs585.mini_twitter_gui.ControlPanel;
import edu.cpp.cs585.mini_twitter_gui.TreePanel;

/**
 * Panel on main UI containing button to open UserViewPanel.
 * 
 * 	- UserViewPanel is only opened if a SingleUser is selected 
 * 		in the TreePanel
 * 	- one UserViewPanel is opened per SingleUser
 * 
 * @author delin
 *
 */

public class OpenUserViewPanel extends ControlPanel {
	
	private JButton openUserViewButton;
	private JPanel spacerPanel;
	
	private JPanel treePanel;
	private Map<String, Observer> allUsers;
	private Map<String, JPanel> openPanels;
	
	/**
	 * Create the panel.  Assume can only open UserViewPanel for SingleUser.
	 */
	public OpenUserViewPanel(JPanel treePanel, Map<String, Observer> allUsers) {
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
		addComponent(this, openUserViewButton, 1, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(this, spacerPanel, 1, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
    
	private void initializeComponents() {
		openPanels = new HashMap<String, JPanel>();
		
		openUserViewButton = new JButton("Open User View");
		initializeOpenUserViewActionListener();
		
		// Empty spacer
		spacerPanel = new JPanel();
	}
	
	/*
	 * Action Listeners
	 */

	private void initializeOpenUserViewActionListener() {
		openUserViewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// get User selected in TreePanel
				JTree tree = ((TreePanel)treePanel).getTree();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				if (!((TreePanel)treePanel).getRoot().equals(selectedNode)) {
					selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
				}
				
				// open user view UI on click, only open one window per User
				if (!openPanels.containsKey(((User) selectedNode).getID()) && selectedNode.getClass() == SingleUser.class) {
					UserViewPanel userView = new UserViewPanel(allUsers, openPanels, selectedNode);
					openPanels.put(((User)selectedNode).getID(), userView);
				}
			}
		});
	}
	
}
