// ShipControllerTest.java

package de.htwg.battleship.controller.impl.childs;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.IBoardValues;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ShipControllerTest tests the entire ship controller.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipControllerTest extends AbstractTest {

    public static final int HEIGHT_LENGTH = 10;
    public static final int MAX_SHIPS = 5;
    /**
     * Saves ShipController.
     */
    private ShipController sc;
    /**
     * Saves Player1.
     */
    private IPlayer player1;
    /**
     * Saves Player2.
     */
    private IPlayer player2;
    /**
     * Saves the first ship.
     */
    private IShip ship1;
    /**
     * Saves the second ship.
     */
    private IShip ship2;
    private IBoardValues boardValues;

    public ShipControllerTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Method to set all objects up.
     */
    @Before
    public final void setUp() {
        boardValues = injector.getInstance(IBoardValues.class);
        boardValues.setBoardSize(HEIGHT_LENGTH);
        boardValues.setMaxShips(MAX_SHIPS);
        player1 = injector.getInstance(IPlayer.class);
        player2 = injector.getInstance(IPlayer.class);
        ship1 = createShip(2, true, 1, 1);
        ship2 = createShip(3, true, 1, 1);
        sc = new ShipController(boardValues.getBoardSize());
    }

    /**
     * Test of placeShip method, of class ShipController.
     */
    @Test
    public final void testPlayer1PlaceShip() {
        checkShipPlacing(player1, ship1, ship2);
    }

    /**
     * Test for the placeShip method. no errors
     */
    @Test
    public final void testPlayer2PlaceShip() {
        checkShipPlacing(player2, ship1, ship2);
    }

    private void checkShipPlacing(IPlayer player, IShip ship1, IShip ship2) {
        boolean result = sc.placeShip(ship1, player);
        assertTrue(result);
        result = sc.placeShip(ship1, player);
        assertFalse(result);
        result = sc.placeShip(ship2, player);
        assertFalse(result);
    }

    /**
     * Test for the placeShipMethod. error for borders
     */
    @Test
    public final void testBorderPlaceShip() {
        boardValues.setBoardSize(2);
        sc = new ShipController(boardValues.getBoardSize());
        IPlayer pl = injector.getInstance(IPlayer.class);
        boolean result = sc.placeShip(ship2, pl);
        assertFalse(result);
    }

    @Test
    public final void testPlayerPlace2Ships() {
        boolean result = sc.placeShip(ship1, player1);
        assertTrue(result);
        IShip sh = createShip(1, true, 5, 5);
        result = sc.placeShip(sh, player1);
        assertTrue(result);
    }
}
