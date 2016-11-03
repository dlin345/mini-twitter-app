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

import edu.cpp.cs585.mini_twitter_app.User;

public class OpenUserViewPanel extends ControlPanel {
	
	private JButton openUserViewButton;
	private JPanel spacerPanel;
	
	private JPanel treePanel;
	private Map<String, JPanel> openPanels;

	/**
	 * Create the panel.
	 */
	public OpenUserViewPanel(JPanel treePanel) {
		super();
		
		this.treePanel = treePanel;
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

	private void initializeOpenUserViewActionListener() {
		openUserViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open user view UI on click
				UserViewPanel userView = new UserViewPanel();
				
				// open UI for User selected in TreePanel
				JTree tree = ((TreePanel)treePanel).getTree();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				if (!((TreePanel)treePanel).getRoot().equals(selectedNode)) {
					selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
				}
				openPanels.put(((User)selectedNode).getID(), userView);
				userView.setWindowName(((User)selectedNode).getID());
			}
		});
	}
	
}
