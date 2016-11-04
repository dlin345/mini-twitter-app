package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs585.mini_twitter_app.Observer;
import edu.cpp.cs585.mini_twitter_app.SingleUser;
import edu.cpp.cs585.mini_twitter_app.Subject;
import edu.cpp.cs585.mini_twitter_app.User;
import edu.cpp.cs585.mini_twitter_gui.ControlPanel;

/**
 * User view UI.
 * 
 * 	- assume can only open for SingleUser
 * 	- assume can only open one panel per SingleUser
 * 
 * @author delin
 *
 */

public class UserViewPanel extends ControlPanel {

	private static JFrame frame;
	private GridBagConstraints constraints;
	
	private JTextField userID;
	
	private JTextArea tweetMessageTextArea;
	private JTextArea currentFollowingTextArea;
	private JTextArea newsFeedTextArea;
	
	private JScrollPane tweetMessageScrollPane;
	private JScrollPane currentFollowingScrollPane;
	private JScrollPane newsFeedScrollPane;
	
	private JButton followUserButton;
	private JButton postTweetButton;
	
	private Subject user;
	private Map<String, Observer> allUsers;
	private Map<String, JPanel> allPanels;

	/**
	 * Create the panel.
	 */
	public UserViewPanel(Map<String, Observer> allUsers, Map<String, JPanel> allPanels, DefaultMutableTreeNode user) {
		super();

		this.user = (Subject) user;
		this.allUsers = allUsers;
		this.allPanels = allPanels;
		initializeComponents();
		addComponents();
	}

	/*
	 * Private methods
	 */
	
	private void addComponents() {
		addComponent(frame, userID, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, followUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, currentFollowingTextArea, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, tweetMessageScrollPane, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, postTweetButton, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(frame, newsFeedScrollPane, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
    
	private void initializeComponents() {
		frame = new JFrame("User View");
		formatFrame();
		
		constraints = new GridBagConstraints();
		constraints.ipady = 100;
		
		userID = new JTextField("User ID");
		followUserButton = new JButton("Follow User");
		initializeFollowUserButtonActionListener();
		
		currentFollowingTextArea = new JTextArea("Current Following: ");
		formatTextArea(currentFollowingTextArea);
		currentFollowingScrollPane = new JScrollPane(currentFollowingTextArea);
		currentFollowingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tweetMessageTextArea = new JTextArea("Tweet Message");
		tweetMessageScrollPane = new JScrollPane(tweetMessageTextArea);
		tweetMessageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		postTweetButton = new JButton("Post Tweet");
		initializePostTweetButtonActionListener();
		
		newsFeedTextArea = new JTextArea("News Feed: ");
		formatTextArea(newsFeedTextArea);        
		newsFeedScrollPane = new JScrollPane(newsFeedTextArea);
		newsFeedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private void formatTextArea(JTextArea textArea) {
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setRows(8);
		textArea.setEditable(false);
	}
	
	private void formatFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(800, 400);
		frame.setVisible(true);
		frame.setTitle(((User) user).getID());
	}
	
	private void updateNewsFeedTextArea() {
		String list = "News Feed: \n";

		for (String news : ((SingleUser) user).getNewsFeed()) {
			list += " - " + news + "\n";
		}
		// show most recent message at top of news feed
		newsFeedTextArea.setText(list);
		newsFeedTextArea.setCaretPosition(0);
	}
	
	/*
	 * Action Listeners
	 */

	private void initializePostTweetButtonActionListener() {
		postTweetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((SingleUser) user).sendMessage(tweetMessageTextArea.getText());

				for (JPanel panel : allPanels.values()) {
					((UserViewPanel) panel).updateNewsFeedTextArea();
				}
			}
		});
	}

	private void initializeFollowUserButtonActionListener() {
		followUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Subject toFollow = (Subject) allUsers.get(userID.getText());
				
				if (allUsers.containsKey(userID.getText())) {
					toFollow.attach((Observer) user);
				}

				// show current following as list
				String list = "Current Following: \n";
				for (String following : ((SingleUser) user).getFollowing().keySet()) {
					list += " - " + following + "\n";
				}
				currentFollowingTextArea.setText(list);
				currentFollowingTextArea.setCaretPosition(0);
			}
		});
	}
    
}
