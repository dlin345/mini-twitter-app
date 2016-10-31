package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JTextField;

public class AddUserPanel extends ControlPanel {

	/**
	 * Create the panel.
	 */
	public AddUserPanel() {
		super();
		addComponentsToPanel();
	}
	
	/*
	 * Private methods 
	 */
	
    private void addComponentsToPanel() {
	    JTextField text = new JTextField("User ID");
	    addComponent(this, text, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    JButton button = new JButton("Add User");
	    addComponent(this, button, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    text = new JTextField("Group ID");
	    addComponent(this, text, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("Add Group");
	    addComponent(this, button, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
}


