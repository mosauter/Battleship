// InputMaskPlayer2Test.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * InputMaskPlayer2Test
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class InputMaskPlayer2Test {

    Viewer v;
    public InputMaskPlayer2Test() {
    }

    @Before
    public void setUp() {
        v = new InputMaskPlayer2();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class InputMaskPlayer2.
     */
    @Test
    public void testGetString() {
        String s = "\nName Player 2:\t";
        assert (s.equals(v.getString()));
    }

}