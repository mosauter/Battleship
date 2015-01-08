// InputMaskPlayer1Test.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.InputMaskPlayer1;
import de.htwg.battleship.aview.tui.Viewer;
import org.junit.Test;

/**
 * InputMaskPlayer1Test tests the InputMask for player 1.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class InputMaskPlayer1Test {

    /**
     * Test of getString method, of class InputMaskPlayer1.
     */
    @Test
    public final void testGetString() {
        Viewer v = new InputMaskPlayer1();
        String s = "\nName Player 1:\t";
        assert (s.equals(v.getString()));
    }
}