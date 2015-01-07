// ShootControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.impl.ShootController;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ShootControllerTest tests the entire shoot controller.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-19
 */
public class ShootControllerTest {

    /**
     * Saves a shootController.
     */
    private ShootController sc;
    /**
     * Saves first player.
     */
    private Player player1;
    /**
     * Saves second player.
     */
    private Player player2;

    /**
     * Set-Up method.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        player1 = new Player(new Board());
        player2 = new Player(new Board());
        Ship ship1 = new Ship(1, true, 3, 2 );
        Ship ship2 = new Ship(1, false, 1, 1);
        player1.getOwnBoard().addShip(ship1);
        player2.getOwnBoard().addShip(ship2);
        sc = new ShootController(player1, player2);
    }

    /**
     * Test shoot-Method.
     * Hit-/Non-Hit - Test.
     * Ship-Orientation is true.
     */
    @Test
    public final void shootTestOneOriTrue() {
        boolean expResult = true;
        boolean result = sc.shoot(3, 2, false);
        assertEquals(expResult, result);
        expResult = false;
        result = sc.shoot(3, 3, false);
        assertEquals(expResult, result);
    }

    /**
     * Test shoot-Method.
     * Hit-/Non-Hit - Test.
     * Ship-Orientation is true.
     */
    @Test
    public final void shootTestTwoOriTrue() {
        boolean expResult = false;
        boolean result = sc.shoot(3, 2, true); // first Time true, second time false
        result = sc.shoot(3, 2, true);
        assertEquals(expResult, result);
        result = sc.shoot(4, 2, true);
        assertEquals(expResult, result);
        result = sc.shoot(2, 2, true);
        assertEquals(expResult, result);
    }

    /**
     * Test for shoot orientation false.
     */
    @Test
    public final void shootTestOneOriFalse() {
        boolean expResult = true;
        boolean result = sc.shoot(1, 1, true);
        assertEquals(expResult, result);
        expResult = false;
        result = sc.shoot(1, 1, true);
        assertEquals(expResult, result);
    }

    /**
     * Test for shoot orientation false.
     */
    @Test
    public final void shootTestTwoOriFalse() {
        boolean expResult = false;
        boolean result = sc.shoot(1, 0, false);
        assertEquals(expResult, result);
        result = sc.shoot(1, 2, false);
        assertEquals(expResult, result);
        result = sc.shoot(0, 1, false);
        assertEquals(expResult, result);
    }
}