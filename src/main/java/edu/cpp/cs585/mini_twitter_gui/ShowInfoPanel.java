package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

public class ShowInfoPanel extends ControlPanel {
	
	private JButton userTotalButton;
	private JButton groupTotalButton;
	private JButton messagesTotalButton;
	private JButton positiveButtonPercentage;

	/**
	 * Create the panel.
	 */
	public ShowInfoPanel() {
		super();
		
		initializeComponents();
		addComponents();
	}
	
    private void addComponents() {
        addComponent(this, userTotalButton, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, groupTotalButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, messagesTotalButton, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, positiveButtonPercentage, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    private void initializeComponents() {
    	userTotalButton = new JButton("Show User Total");
    	groupTotalButton = new JButton("Show Group Total");
    	messagesTotalButton = new JButton("Show Messages Total");
    	positiveButtonPercentage = new JButton("Show Positive Percentage");
    }
    
}
