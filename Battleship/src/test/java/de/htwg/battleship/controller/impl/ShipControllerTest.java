// ShipControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ShipControllerTest tests the entire ship controller.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipControllerTest {

    /**
     * Saves ShipController.
     */
    private ShipController sc;
    /**
     * Saves Player1.
     */
    private Player player1;
    /**
     * Saves Player2.
     */
    private Player player2;
    /**
     * Saves the first ship.
     */
    private Ship ship1;
    /**
     * Saves the second ship.
     */
    private Ship ship2;

    /**
     * Method to set all objects up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        player1 = new Player(new Board());
        player2 = new Player(new Board());
        ship1 = new Ship(2, true, 1, 1);
        ship2 = new Ship(3, true, 1, 1);
        sc =  new ShipController();
    }

    /**
     * Test of placeShip method, of class ShipController.
     */
    @Test
    public final void testPlayer1PlaceShip() {
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

    /**
     * Test for the placeShip method.
     * no errors
     */
    @Test
    public final void testPlayer2PlaceShip() {
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

    /**
     * Test for the placeShipMethod.
     * error for borders
     */
    @Test
    public final void testBorderPlaceShip() {
        boolean expRes = false;
        StatCollection.heightLenght = 2;
        IPlayer pl = new Player();
        boolean result = sc.placeShip(ship2, pl);
        assertEquals(expRes, result);
    }

    @Test
    public final void testPlayerPlace2Ships() {
        boolean expRes = true;
        boolean result = sc.placeShip(ship1, player1);
        assertEquals(expRes, result);
        Ship sh = new Ship(1, true, 5, 5);
        result = sc.placeShip(sh, player1);
        assertEquals(expRes, result);
    }
}