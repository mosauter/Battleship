// StartMenuTest.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.views.StartMenu;
import org.junit.Test;

/**
 * StartMenuTest tests the start menu.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class StartMenuTest {

    /**
     * Test of getString method, of class StartMenu.
     */
    @Test
    public final void testGetString() {
        String sb = "1. Start Game\n" +
                    "2. Options\n" +
                    "Type 'Exit' or 'exit' at any time to close the game.\n" +
                    "\t--->\t";
        Viewer v = new StartMenu();
        assert (v.getString().equals(sb));
    }
}
