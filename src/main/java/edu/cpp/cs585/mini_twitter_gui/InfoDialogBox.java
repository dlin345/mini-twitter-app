package edu.cpp.cs585.mini_twitter_gui;

import javax.swing.JOptionPane;

/**
 * Message dialog for displaying user-specific information.
 * 
 * @author delin
 *
 */

public class InfoDialogBox {
	
	/**
	 * Create the panel.
	 */
	public InfoDialogBox(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

}
