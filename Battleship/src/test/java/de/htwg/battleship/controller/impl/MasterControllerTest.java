// MasterControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.IObserver;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.START;
import static de.htwg.battleship.util.State.WIN1;
import static de.htwg.battleship.util.State.WIN2;
import static de.htwg.battleship.util.State.WRONGINPUT;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * MasterControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class MasterControllerTest {

    IMasterController master;
    IPlayer player1;
    IPlayer player2;
    UtilObserver utilOb;

    public MasterControllerTest() {
    }

    @Before
    public void setUp() {
        player1 = new Player();
        player2 = new Player();
        master = new MasterController(player1, player2);
        utilOb = new UtilObserver();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shoot method, of class MasterController.
     */
    @Test
    public void testShoot() {
    }

    /**
     * Test of placeShip method, of class MasterController.
     */
    @Test
    public void testPlaceShip() {
    }

    /**
     * Test of win method, of class MasterController.
     */
    @Test
    public void testWinPlayer1() {
        master.getPlayer1().getOwnBoard().addShip(new Ship(1, true, 1, 1));
        master.addObserver(utilOb);
        master.win();
        assertEquals(utilOb.util, 2);
    }

    @Test
    public void testWinPlayer2() {
        master.getPlayer2().getOwnBoard().addShip(new Ship(1, true, 1, 1));
        master.addObserver(utilOb);
        master.win();
        assertEquals(utilOb.util, 3);
    }

    @Test
    public void testWinNull() {
        master.getPlayer1().getOwnBoard().addShip(new Ship(1, true, 1, 1));
        master.getPlayer2().getOwnBoard().addShip(new Ship(1, true, 1, 1));
        master.addObserver(utilOb);
        master.win();
        assertEquals(master.getCurrentState(), START);
    }

    /**
     * Test of setState method, of class MasterController.
     */
    @Test
    public void testState() {
        State st = START;
        assertEquals(master.getCurrentState(), st);
        st = State.HIT;
        master.setCurrentState(st);
        assertEquals(master.getCurrentState(), st);
        master.setCurrentState(State.WRONGINPUT);
        assertEquals(master.getCurrentState(), st);
        master.setCurrentState(State.PLACEERR);
        assertEquals(master.getCurrentState(), st);
    }

    /**
     * Test of getPlayer1 method, of class MasterController.
     */
    @Test
    public void testGetPlayer1() {
        assertEquals(master.getPlayer1(), player1);
    }

    /**
     * Test of getPlayer2 method, of class MasterController.
     */
    @Test
    public void testGetPlayer2() {
        assertEquals(master.getPlayer2(), player2);
    }

    /**
     * Test of setPlayerName method, of class MasterController.
     */
    @Test
    public void testSetPlayerName() {
        String name = "Moritz";
        master.setCurrentState(State.GETNAME1);
        master.setPlayerName(name);
        assert (name.equals(master.getPlayer1().getName()));
        master.setPlayerName(name);
        assert (name.equals(master.getPlayer2().getName()));
        master.addObserver(utilOb);
        master.setPlayerName(name);
        assertEquals(utilOb.util, 5);
    }

    class UtilObserver implements IObserver {

        private int util = 0;
        @Override
        public void update() {
            if (master.getCurrentState() == WRONGINPUT) {
                util = 1;
                return;
            }
            if (master.getCurrentState() == WIN1) {
                util = 2;
                return;
            }
            if (master.getCurrentState() == WIN2) {
                util = 3;
                return;
            }
            if (util != 0) {
                return;
            }
        }
    }
}