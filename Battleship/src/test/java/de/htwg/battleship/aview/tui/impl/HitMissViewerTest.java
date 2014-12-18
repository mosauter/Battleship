// HitMissViewerTest.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * HitMissViewerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class HitMissViewerTest {

    Viewer v;
    public HitMissViewerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class HitMissViewer.
     */
    @Test
    public void testGetString() {
        v = new HitMissViewer(true);
        String s = "Your Shot was a Hit!!\n";
        assert (s.equals(v.getString()));
        v = new HitMissViewer(false);
        s = "Your Shot was a Miss\n";
        assert (s.equals(v.getString()));
    }

}