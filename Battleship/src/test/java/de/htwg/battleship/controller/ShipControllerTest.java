// ShipControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.controller.impl.ShipController;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
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
        StatCollection.HEIGTH_LENGTH = 10;
        StatCollection.SHIP_NUMBER_MAX = 5;
        player1 = new Player(new Board());
        player2 = new Player(new Board());
        ship1 = new Ship(2, true, 1, 1);
        ship2 = new Ship(3, true, 1, 1);
        sc =  new ShipController();
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
        boolean result = sc.placeShip(ship1, player1);
        assertEquals(expRes, result);
        expRes = false;
        result = sc.placeShip(ship1, player1);
        assertEquals(expRes, result);
        expRes = false;
        result = sc.placeShip(ship2, player1);
        assertEquals(expRes, result);
    }
    
    @Test
    public void testPlayer2PlaceShip() {
        boolean expRes = true;
        boolean result = sc.placeShip(ship1, player2);
        assertEquals(expRes, result);
        expRes = false;
        result = sc.placeShip(ship1, player2);
        assertEquals(expRes, result);
        expRes = false;
        result = sc.placeShip(ship2, player2);
        assertEquals(expRes, result);
    }

}