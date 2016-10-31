package edu.cpp.cs585.mini_twitter_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminControlPanel frame = AdminControlPanel.getInstance();
					frame.addComponentsToPane();
					
//					UserViewPanel frame1 = new UserViewPanel();
//					frame1.addComponentsToPane();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
