// HitMissViewerTest.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.HitMissViewer;
import de.htwg.battleship.aview.tui.Viewer;
import org.junit.Test;

/**
 * HitMissViewerTest tests the HitMissViewer.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class HitMissViewerTest {

    /**
     * Test of getString method, of class HitMissViewer.
     */
    @Test
    public final void testGetString() {
        Viewer v = new HitMissViewer(true);
        String s = "Your Shot was a Hit!!\n";
        assert (s.equals(v.getString()));
        v = new HitMissViewer(false);
        s = "Your Shot was a Miss\n";
        assert (s.equals(v.getString()));
    }
}