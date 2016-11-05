package edu.cpp.cs585.mini_twitter_gui;

import org.junit.Assert;
import org.junit.Test;

import edu.cpp.cs585.mini_twitter_gui.AdminControlPanel;

public class AdminControlPanelTest {

    @Test
    public void getInstanceTest() {
        AdminControlPanel window1 = AdminControlPanel.getInstance();
        AdminControlPanel window2 = AdminControlPanel.getInstance();

        Assert.assertEquals(true, window1 == window2);
    }
}
