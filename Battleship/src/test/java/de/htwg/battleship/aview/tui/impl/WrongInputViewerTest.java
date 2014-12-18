// WrongInputViewerTest.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * WrongInputViewerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class WrongInputViewerTest {

    Viewer view;
    public WrongInputViewerTest() {
    }

    @Before
    public void setUp() {
        view = new WrongInputViewer();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class WrongInputViewer.
     */
    @Test
    public void testGetString() {
        String s = "Your Input is not recognized and was false "
                + "at the current state of the Game!!\n";
        assert (s.equals(view.getString()));
    }

}