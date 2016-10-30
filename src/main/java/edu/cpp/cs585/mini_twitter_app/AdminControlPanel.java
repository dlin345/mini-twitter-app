package edu.cpp.cs585.mini_twitter_app;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.BoxLayout;

public class AdminControlPanel extends JFrame {

	private JPanel contentPane;
	private static AdminControlPanel INSTANCE;
	private User root;

	/**
	 * Create the frame as Singleton.
	 */
	private AdminControlPanel() {
		setTitle("Mini-Twitter App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
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
