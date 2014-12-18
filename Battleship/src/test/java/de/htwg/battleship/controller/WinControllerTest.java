// WinControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.controller.impl.WinController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * WinControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class WinControllerTest {

    public WinControllerTest() {
    }

    @Before
    public void setUp() {
        StatCollection.HEIGTH_LENGTH = 2;
        StatCollection.SHIP_NUMBER_MAX = 1;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of win method, of class WinController.
     */
    @Test
    public void testNullWin() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        IShip ship = new Ship(1, true, 0, 0);
        player1.getOwnBoard().addShip(ship);
        player2.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer expRes = null;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }

    /**
     * Test of win method, result player1.
     */
    @Test
    public void testPlayer1Win() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        IShip ship = new Ship(1, true, 0, 0);
        player1.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer expRes = player1;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }
    
    /**
     * Test of win method, result player2.
     */
    @Test
    public void testPlayer2Win() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        IShip ship = new Ship(1, true, 0, 0);
        player2.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer expRes = player2;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }
}