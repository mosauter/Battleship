// PlaceErrorViewerTest.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.PlaceErrorViewer;
import de.htwg.battleship.aview.tui.Viewer;
import org.junit.Test;

/**
 * PlaceErrorViewerTest tests the placeerror viewer.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class PlaceErrorViewerTest {

    /**
     * Test of getString method, of class PlaceErrorViewer.
     */
    @Test
    public final void testGetString() {
        Viewer v = new PlaceErrorViewer();
        String s = "Cannot place the Ship because there was a collision or"
                + " the ship ends out of the field. Try again!\n";
        assert (v.getString().equals(s));
    }

}