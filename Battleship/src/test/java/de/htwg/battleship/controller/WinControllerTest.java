// WinControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.controller.impl.ShipController;
import de.htwg.battleship.controller.impl.ShootController;
import de.htwg.battleship.controller.impl.WinController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
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

    IWinLooseController wc;
    IShipController shipC;
    IShootController shootC;
    IPlayer player1;
    IPlayer player2;
    IShip ship1;
    IShip ship2;
    
    public WinControllerTest() {
    }

    @Before
    public void setUp() {
        player1 = new Player(new Board());
        player2 = new Player(new Board());
        ship1 = new Ship(2, true, 4, 4);
        ship2 = new Ship(1, true, 1, 1);
        shipC = new ShipController(player1, player2);
        shipC.placeShip(ship1, true);
        shipC.placeShip(ship2, false);
        shootC = new ShootController(player1, player2);
        wc = new WinController(player1, player2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of win method, of class WinController.
     */
    @Test
    public void testNullWin() {
        IPlayer expRes = null;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }

    /**
     * Test of win method, result player1.
     */
    @Test
    public void testPlayer1Win() {
        IPlayer expRes = player1;
        shootC.shoot(1, 1, true);
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }
    
    /**
     * Test of win method, result player2.
     */
    @Test
    public void testPlayer2Win() {
        IPlayer expRes = player2;
        shootC.shoot(4, 4, false);
        shootC.shoot(5, 4, false);
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }
}