package edu.cpp.cs585.mini_twitter_app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminControlPanel extends JFrame {

	private JPanel contentPane;
	private static AdminControlPanel INSTANCE;
	private User root;

	/**
	 * Create the frame as Singleton.
	 */
	private AdminControlPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		root = new GroupUser("Root");
	}
	
	public static AdminControlPanel getInstance() {
		if (INSTANCE == null) {
			synchronized (Driver.class) {
				if (INSTANCE == null) {
					INSTANCE = new AdminControlPanel();
				}
			}
		}
		return INSTANCE;
	}
	
}
