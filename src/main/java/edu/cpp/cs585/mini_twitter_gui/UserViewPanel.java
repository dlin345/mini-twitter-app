package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.JTextComponent;

import edu.cpp.cs585.mini_twitter_app.User;

public class UserViewPanel extends ControlPanel {

private static JFrame frame;

	/**
	 * Create the panel.
	 */
	public UserViewPanel() {
	
	}

    public static void addComponentsToPane() {
    	frame = new JFrame("User View");
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        JButton button;
        JTextComponent text;

        text = new JTextField("User ID");
        addComponent(frame, text, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        button = new JButton("Follow User");
        addComponent(frame, button, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 100;
        button = new JButton("Current following");
        addComponentWithPadding(frame, button, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, c.ipady);
        
        text = new JTextArea("Tweet Message");
        ((JTextArea)text).setLineWrap(true);
        ((JTextArea)text).setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        addComponent(frame, scrollPane, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        button = new JButton("Post Tweet");
        addComponent(frame, button, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        button = new JButton("News feed");
        addComponentWithPadding(frame, button, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, c.ipady);
       
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
	
}
