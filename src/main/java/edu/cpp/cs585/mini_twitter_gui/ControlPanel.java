package edu.cpp.cs585.mini_twitter_gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public abstract class ControlPanel extends JPanel {
	
	private static final Insets insets = new Insets(0, 0, 0, 0);
    
    public ControlPanel() {
    	super(new GridBagLayout());
    }

	public static void addComponentWithPadding(Container container, Component component, int gridx, int gridy,
    		int gridwidth, int gridheight, int anchor, int fill, int ipadx, int ipady) {
	    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
	        anchor, fill, insets, ipadx, ipady);
	    container.add(component, gbc);
	}
	
	public static void addComponent(Container container, Component component, int gridx, int gridy,
    		int gridwidth, int gridheight, int anchor, int fill) {
	    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
	        anchor, fill, insets, 0, 0);
	    container.add(component, gbc);
	}

}
