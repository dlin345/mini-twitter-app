package edu.cpp.cs585.mini_twitter_app;

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
//					Driver frame = new Driver();
					AdminControlPanel frame = AdminControlPanel.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
