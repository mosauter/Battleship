// WinPlayerTest.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.controller.impl.MasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * WinPlayerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class WinPlayerTest {

    IPlayer pl;
    Viewer view;
    public WinPlayerTest() {
    }

    @Before
    public void setUp() {
        pl = new Player();
        pl.setName("Moritz");
        view = new WinPlayer(pl, new MasterController(pl, new Player()));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class WinPlayer.
     */
    @Test
    public void testGetString() {
        String st = "Moritz has won!\n\n";
        assert (st.equals(view.getString()));
    }

}