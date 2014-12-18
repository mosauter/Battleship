// InputMaskPlayer1Test.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * InputMaskPlayer1Test
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class InputMaskPlayer1Test {

    Viewer v;
    public InputMaskPlayer1Test() {
    }

    @Before
    public void setUp() {
        v = new InputMaskPlayer1();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class InputMaskPlayer1.
     */
    @Test
    public void testGetString() {
        String s = "\nName Player 1:\t";
        assert (s.equals(v.getString()));
    }

}