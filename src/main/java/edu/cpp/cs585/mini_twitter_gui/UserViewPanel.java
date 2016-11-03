package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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

	/**
	 * Create the panel.
	 */
	public UserViewPanel() {
		super();
		
		initializeComponents();
		addComponents();
	}
	
	public void setWindowName(String windowName) {
		frame.setTitle(windowName);
	}
	
	/*
	 * Private methods
	 */

    private void addComponents() {
        addComponent(frame, userID, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, followUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponentWithPadding(frame, currentFollowingTextArea, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, constraints.ipady);
        addComponent(frame, tweetMessageScrollPane, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, postTweetButton, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponentWithPadding(frame, newsFeedScrollPane, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, constraints.ipady);
    }
    
    private void initializeComponents() {
    	frame = new JFrame("User View");
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(800, 400);
        frame.setVisible(true);
        
        constraints = new GridBagConstraints();
        constraints.ipady = 100;
        
        userID = new JTextField("User ID");
        followUserButton = new JButton("Follow User");
        
        currentFollowingTextArea = new JTextArea("Current following");
        formatTextArea(currentFollowingTextArea);
        currentFollowingScrollPane = new JScrollPane(currentFollowingTextArea);
        currentFollowingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        tweetMessageTextArea = new JTextArea("Tweet Message");        
        tweetMessageScrollPane = new JScrollPane(tweetMessageTextArea);
        tweetMessageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        postTweetButton = new JButton("Post Tweet");
        
        newsFeedTextArea = new JTextArea("News feed");
        formatTextArea(newsFeedTextArea);        
        newsFeedScrollPane = new JScrollPane(newsFeedTextArea);
        newsFeedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
    
    private void formatTextArea(JTextArea textArea) {
    	textArea.setLineWrap(true);
    	textArea.setWrapStyleWord(true);
    	textArea.setEditable(false);
    }
	
}
