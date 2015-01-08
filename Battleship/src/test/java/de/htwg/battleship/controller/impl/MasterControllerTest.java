// MasterControllerTest.java
package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.IObserver;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.END;
import static de.htwg.battleship.util.State.GETNAME1;
import static de.htwg.battleship.util.State.PLACE1;
import static de.htwg.battleship.util.State.PLACE2;
import static de.htwg.battleship.util.State.PLACEERR;
import static de.htwg.battleship.util.State.START;
import static de.htwg.battleship.util.State.WIN1;
import static de.htwg.battleship.util.State.WIN2;
import static de.htwg.battleship.util.State.WRONGINPUT;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * MasterControllerTest tests the MasterController.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class MasterControllerTest {

    /**
     * Saves the MasterController.
     */
    private MasterController master;
    /**
     * Saves the first player.
     */
    private IPlayer player1;
    /**
     * Saves the second player.
     */
    private IPlayer player2;
    /**
     * Saves an util observer object for testing.
     */
    private UtilObserver utilOb;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 3;
        StatCollection.shipNumberMax = 1;
        player1 = new Player();
        player2 = new Player();
        master = new MasterController(player1, player2);
        utilOb = new UtilObserver();
    }

    /**
     * Test of placeShip method, of class MasterController.
     */
    @Test
    public final void testPlaceShip() {
        master.setCurrentState(PLACE1);
        master.placeShip(1, 1, true);
        IShip[] ships = master.getPlayer1().getOwnBoard().getShipList();
        boolean x = false;
        boolean y = false;
        boolean orientation = false;
        boolean size = false;
        int shipCount = 0;
        for (IShip ship : ships) {
            if (ship == null) {
                continue;
            } else {
                shipCount++;
            }
            IShip ship1;
            if (ship.getX() == 1) {
                x = true;
                ship1 = ship;
            } else {
                continue;
            }
            if (ship1.getY() == 1) {
                y = true;
            }
            if (ship1.getSize()
                    == master.getPlayer1().getOwnBoard().getShips() + 1) {
                size = true;
            }
            orientation = ship.isOrientation();
        }
        assertEquals(orientation, true);
        assertEquals(size, true);
        assertEquals(x, true);
        assertEquals(y, true);
        assertEquals(shipCount, 1);
    }

    /**
     * Test of win method, of class MasterController.
     */
    @Test
    public final void testWinPlayer1() {
        master.getPlayer1().getOwnBoard().addShip(new Ship(1, true, 1, 1));
        master.addObserver(utilOb);
        master.win();
        assertEquals(utilOb.util, 2);
    }

    /**
     * Second test of win method, of class MasterController.
     */
    @Test
    public final void testWinPlayer2() {
        master.getPlayer2().getOwnBoard().addShip(new Ship(1, true, 1, 1));
        master.addObserver(utilOb);
        master.win();
        assertEquals(utilOb.util, 3);
    }

    /**
     * Third test of win method.
     */
    @Test
    public final void testWinNull() {
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
    public final void testState() {
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
    public final void testGetPlayer1() {
        assertEquals(master.getPlayer1(), player1);
    }

    /**
     * Test of getPlayer2 method, of class MasterController.
     */
    @Test
    public final void testGetPlayer2() {
        assertEquals(master.getPlayer2(), player2);
    }

    /**
     * Test of setPlayerName method, of class MasterController.
     */
    @Test
    public final void testSetPlayerName() {
        String name = "Moritz";
        master.setCurrentState(State.GETNAME1);
        master.setPlayerName(name);
        assert (name.equals(master.getPlayer1().getName()));
        master.setPlayerName(name);
        assert (name.equals(master.getPlayer2().getName()));
        master.addObserver(utilOb);
        master.setPlayerName(name);
        // Last was a false input
        Assert.assertTrue(utilOb.wrong);
    }

    @Test
    public final void testResetBoard() {
        master.setCurrentState(PLACE1);
        master.placeShip(1, 1, true);
        master.setCurrentState(PLACE2);
        master.placeShip(1, 1, true);
        master.resetBoards();
        int i1 = master.getPlayer1().getOwnBoard().getShips();
        int i2 = master.getPlayer2().getOwnBoard().getShips();
        assertEquals(0, i1);
        assertEquals(0, i2);
    }

    @Test
    public final void testStartGame() {
        master.addObserver(utilOb);
        master.startGame();
        assertEquals(4, utilOb.util);
        master.setCurrentState(END);
        master.startGame();
        assertEquals(5, utilOb.util);
    }

    @Test
    public final void testPlaceShipWrongInput() {
        master.addObserver(utilOb);
        master.placeShip(1, 1, true);
        Assert.assertTrue(utilOb.wrong);
    }

    @Test
    public final void testPlaceShipPlaceErr() {
        master.setCurrentState(PLACE1);
        master.addObserver(utilOb);
        master.placeShip(10, 10, true);
        Assert.assertTrue(utilOb.plerr);
    }

    @Test
    public final void testShootWrongInput() {
        master.addObserver(utilOb);
        master.shoot(1, 1);
        Assert.assertTrue(utilOb.wrong);
    }

    /**
     * Utility observer object for test purposes. implements IObserver
     */
    class UtilObserver implements IObserver {

        /**
         * Utility integer for test purposes.
         */
        private int util = 0;
        private boolean wrong = false;
        private boolean plerr = false;

        @Override
        public void update() {
            if (util == 0) {
                if (master.getCurrentState() == WRONGINPUT) {
                    util = 1;
                    wrong = true;
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
                if (master.getCurrentState() == GETNAME1) {
                    util = 4;
                    return;
                }
                if (master.getCurrentState() == PLACE1) {
                    util = 5;
                    return;
                }
            }
            if (master.getCurrentState() == PLACEERR) {
                plerr = true;
            }
            if (util == 4) {
                if (master.getCurrentState() == PLACE1) {
                    util = 5;
                    return;
                }
            }
        }
    }
}
