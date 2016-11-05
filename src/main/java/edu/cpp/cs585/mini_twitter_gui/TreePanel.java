package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import edu.cpp.cs585.mini_twitter_app.GroupUser;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.User;

/**
 * Tree view panel for SingleUser and GroupUser.
 * 
 * 	- GroupUser icon will be differentiated from SingleUser icon 
 * 		if it has one or more SingleUsers as its child
 * 
 * @author delin
 *
 */

public class TreePanel extends JPanel {
	
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;	
	private JScrollPane scrollPane;
	
	public TreePanel(DefaultMutableTreeNode root) {
		super(new GridLayout(1,0));
		
		rootNode = root;
		initializeComponents();
		addComponents();
	}
    
	public JTree getTree() {
		return this.tree;
	}
	
	public DefaultMutableTreeNode getRoot() {
		return this.rootNode;
	}

	/** 
	 * Add {@link GroupUser} to the currently selected {@link User}.  The {@link GroupUser} 
	 * is added to its parent {@link GroupUser} if the currently selected {@link User} is 
	 * a {@link SingleUser}. 
	 */
	public void addGroupUser(DefaultMutableTreeNode child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();
		
		// set parent as selected User, set as root if no User is selected
		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
		}
		
		// add to parent GroupUser if selected node is a SingleUser
		if (parentNode.getUserObject().getClass() == SingleUser.class) {
			parentNode = (DefaultMutableTreeNode) parentNode.getParent();
		}
		addUser(parentNode, child, true);
	}
    
	/** 
	 * Add {@link SingleUser} to the currently selected {@link User}.  The {@link SingleUser} 
	 * is added to its parent {@link GroupUser} if the currently selected {@link User} is 
	 * a {@link SingleUser}. 
	 */
	public void addSingleUser(DefaultMutableTreeNode child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();
		
		// set parent as selected User, set as root if no User is selected
		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
		}
		
		// add to parent GroupUser if selected node is a SingleUser
		if (parentNode.getUserObject().getClass() == SingleUser.class) {
			parentNode = (DefaultMutableTreeNode) parentNode.getParent();
		}
		addUser(parentNode, child, true);
	}
	
	/*
	 * Private methods
	 */
    
	/**
	 * Add specified child {@link User} to specified parent {@link User}.
	 */
	private void addUser(DefaultMutableTreeNode parent, DefaultMutableTreeNode child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		
		if (parent == null) {
			parent = rootNode;
		}
		
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		
		if (parent.getClass() != GroupUser.class) {
			parent = (DefaultMutableTreeNode) parent.getUserObject();
		}
		((GroupUser) parent).addUserInGroup((User) child);
	}
    
	private void addComponents() {
		add(scrollPane);
	}
    
	private void initializeComponents() {
		treeModel = new DefaultTreeModel(rootNode);
		treeModel.addTreeModelListener(new MyTreeModelListener());
		
		tree = new JTree(treeModel);
		formatTree();
		
		scrollPane = new JScrollPane(tree);
	}

	private void formatTree() {
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.setSelectionRow(0);
	}

	/**
	 * TreeModelListener for this TreeModel.
	 */
	private class MyTreeModelListener implements TreeModelListener {
		public void treeNodesChanged(TreeModelEvent e) {
		}
		public void treeNodesInserted(TreeModelEvent e) {
		}
		public void treeNodesRemoved(TreeModelEvent e) {
		}
		public void treeStructureChanged(TreeModelEvent e) {
		}
	}

}