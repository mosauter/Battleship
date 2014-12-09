// ShipControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.Board;
import de.htwg.battleship.model.Player;
import de.htwg.battleship.model.Ship;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ShipControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipControllerTest {
    
    ShipController sc;
    Player player1;
    Player player2;
    Ship ship1;
    Ship ship2;

    public ShipControllerTest() {
    }

    @Before
    public void setUp() {
        player1 = new Player(new Board());
        player2 = new Player(new Board());
        ship1 = new Ship(2, true, 4, 4);
        ship2 = new Ship(3, true, 3, 4);
        sc =  new ShipController(player1, player2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of placeShip method, of class ShipController.
     */
    @Test
    public void testPlayer1PlaceShip() {
        boolean expRes = true;
        boolean result = sc.placeShip(ship1, true);
        assertEquals(expRes, result);
        expRes = false;
        result = sc.placeShip(ship1, true);
        assertEquals(expRes, result);
        expRes = false;
        result = sc.placeShip(ship2, true);
        assertEquals(expRes, result);
    }

}