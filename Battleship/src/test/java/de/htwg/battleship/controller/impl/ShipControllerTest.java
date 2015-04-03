// ShipControllerTest.java

package de.htwg.battleship.controller.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;

/**
 * ShipControllerTest tests the entire ship controller.
 * 
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipControllerTest extends AbstractTest {

    /**
     * Saves ShipController.
     */
    private ShipController sc;
    /**
     * Saves Player1.
     */
    private IPlayer        player1;
    /**
     * Saves Player2.
     */
    private IPlayer        player2;
    /**
     * Saves the first ship.
     */
    private IShip          ship1;
    /**
     * Saves the second ship.
     */
    private IShip          ship2;

    /**
     * Method to set all objects up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        player1 = in.getInstance(IPlayer.class);
        player2 = in.getInstance(IPlayer.class);
        ship1 = createShip(2, true, 1, 1);
        ship2 = createShip(3, true, 1, 1);
        sc = new ShipController();
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
        IPlayer pl = in.getInstance(IPlayer.class);
        boolean result = sc.placeShip(ship2, pl);
        assertEquals(expRes, result);
    }

    @Test
    public final void testPlayerPlace2Ships() {
        boolean expRes = true;
        boolean result = sc.placeShip(ship1, player1);
        assertEquals(expRes, result);
        IShip sh = createShip(1, true, 5, 5);
        result = sc.placeShip(sh, player1);
        assertEquals(expRes, result);
    }
}
