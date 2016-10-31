package edu.cpp.cs585.mini_twitter_gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OpenUserViewPanel extends ControlPanel {

	/**
	 * Create the panel.
	 */
	public OpenUserViewPanel() {
		super();
		addComponentsToPanel();
	}
	
    private void addComponentsToPanel() {
    	JButton button = new JButton("Open User View");
    	button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open user view UI on click
				UserViewPanel userView = new UserViewPanel();
				userView.addComponentsToPane();
			}
		});
        addComponent(this, button, 1, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        // Empty spacer
        JPanel panel = new JPanel();
        addComponent(this, panel, 1, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
	
}
