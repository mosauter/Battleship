// PlaceErrorViewerTest.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * PlaceErrorViewerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class PlaceErrorViewerTest {

    public PlaceErrorViewerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class PlaceErrorViewer.
     */
    @Test
    public void testGetString() {
        Viewer v = new PlaceErrorViewer();
        String s = "Cannot place the Ship because there was a collision or"
                + " the ship ends out of the field. Try again!\n";
        assert (v.getString().equals(s));
    }

}