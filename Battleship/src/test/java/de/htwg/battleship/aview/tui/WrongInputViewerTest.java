// WrongInputViewerTest.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.WrongInputViewer;
import de.htwg.battleship.aview.tui.Viewer;
import org.junit.Test;

/**
 * WrongInputViewerTest tests the wrong input viewer.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class WrongInputViewerTest {

    /**
     * Test of getString method, of class WrongInputViewer.
     */
    @Test
    public final void testGetString() {
        Viewer view = new WrongInputViewer();
        String s = "Your Input is not recognized and was false "
                + "at the current state of the Game!!\n";
        assert (s.equals(view.getString()));
    }
}