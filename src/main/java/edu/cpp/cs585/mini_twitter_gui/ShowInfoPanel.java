package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

public class ShowInfoPanel extends ControlPanel {

	/**
	 * Create the panel.
	 */
	public ShowInfoPanel() {
		super();
		addComponentsToPanel();
	}
	
    private void addComponentsToPanel() {
    	JButton button = new JButton("Show User Total");
        addComponent(this, button, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton("Show Group Total");
        addComponent(this, button, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton("Show Messages Total");
        addComponent(this, button, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton("Show Positive Percentage");
        addComponent(this, button, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
}
