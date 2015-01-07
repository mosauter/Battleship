// InputMaskPlayer2Test.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.InputMaskPlayer2;
import de.htwg.battleship.aview.tui.Viewer;
import org.junit.Test;

/**
 * InputMaskPlayer2Test tests the input mask for player 2.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class InputMaskPlayer2Test {

    /**
     * Test of getString method, of class InputMaskPlayer2.
     */
    @Test
    public final void testGetString() {
        Viewer v = new InputMaskPlayer2();
        String s = "\nName Player 2:\t";
        assert (s.equals(v.getString()));
    }
}