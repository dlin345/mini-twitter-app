package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;

public class TreePanel extends ControlPanel {

	private DefaultMutableTreeNode rootNode;
	private User rootGroupUser;
	private JTree tree;
	
	/**
	 * Create the panel.
	 */
	public TreePanel(User rootGroupUser) {
		super();
		this.rootNode = new DefaultMutableTreeNode("Root");
		this.rootGroupUser = rootGroupUser;
		addComponentsToPanel();
	}
	
	/*
	 * Private methods
	 */
	
    private void addComponentsToPanel() {
    	updateTree();
    	
    	tree.setShowsRootHandles(true);
        JScrollPane treeScrollPane = new JScrollPane(tree);
        addComponent(this, treeScrollPane, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    private void updateTree() {
    	rootNode = convertToTree(((GroupUser)rootGroupUser).getGroupUsers(), rootNode);
    	tree = new JTree(rootNode);
    }
    
    private DefaultMutableTreeNode convertToTree(Map<String, User> rootGroup, DefaultMutableTreeNode rootNode) {
    	for (User user : rootGroup.values()) {
    		if (user.getClass() == SingleUser.class) {
    			rootNode.add(new DefaultMutableTreeNode(user.getID()));
    		} else {
    			DefaultMutableTreeNode group = new DefaultMutableTreeNode(user.getID());
    			group = convertToTree(((GroupUser)user).getGroupUsers(), group);
    			rootNode.add(group);
    		}
    	}
    	return rootNode;
    }
 
}
