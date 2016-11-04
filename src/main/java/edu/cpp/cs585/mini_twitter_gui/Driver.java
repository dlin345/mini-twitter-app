package edu.cpp.cs585.mini_twitter_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Driver extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminControlPanel frame = AdminControlPanel.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
