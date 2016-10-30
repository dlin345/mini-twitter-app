package edu.cpp.cs585.mini_twitter_app;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class AdminControlPanelTest {
	
	@Test
	public void getInstanceTest() {
		AdminControlPanel window1 = AdminControlPanel.getInstance();
		AdminControlPanel window2 = AdminControlPanel.getInstance();
		
		Assert.assertEquals(true, window1 == window2);
	}
}
