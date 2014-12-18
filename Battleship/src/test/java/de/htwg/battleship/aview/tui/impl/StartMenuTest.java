// StartMenuTest.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * StartMenuTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class StartMenuTest {

    public StartMenuTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class StartMenu.
     */
    @Test
    public void testGetString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Start Game\n");
        sb.append("2. Exit\n");
        sb.append("\t--->\t");
        Viewer v = new StartMenu();
        assert (v.getString().equals(sb.toString()));
    }

}