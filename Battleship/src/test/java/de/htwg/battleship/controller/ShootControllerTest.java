// ShootControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.controller.impl.ShootController;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ShootControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-19
 */
public class ShootControllerTest {

    ShootController sc;
    Player player1;
    Player player2;
    
    public ShootControllerTest() {
    }

    @Before
    public void setUp() {
        StatCollection.HEIGTH_LENGTH = 10;
        StatCollection.SHIP_NUMBER_MAX = 5;
        player1 = new Player(new Board());
        player2 = new Player(new Board());
        Ship ship1 = new Ship(1, true, 3, 2 );
        Ship ship2 = new Ship(1, false, 1, 1);
        player1.getOwnBoard().addShip(ship1);
        player2.getOwnBoard().addShip(ship2);
        sc = new ShootController(player1, player2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test shoot-Method.
     * Hit-/Non-Hit - Test.
     * Ship-Orientation is true.
     */
    @Test
    public void shootTestOneOriTrue() {
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
    public void shootTestTwoOriTrue() {
        boolean expResult = false;
        boolean result = sc.shoot(3, 2, true); // first Time true, second time false
        result = sc.shoot(3, 2, true);
        assertEquals(expResult, result);
        result = sc.shoot(4, 2, true);
        assertEquals(expResult, result);
        result = sc.shoot(2, 2, true);
        assertEquals(expResult, result);
    }
    
    @Test
    public void shootTestOneOriFalse() {
        boolean expResult = true;
        boolean result = sc.shoot(1, 1, true);
        assertEquals(expResult, result);
        expResult = false;
        result = sc.shoot(1, 1, true);
        assertEquals(expResult, result);
    }
    
    @Test
    public void shootTestTwoOriFalse() {
        boolean expResult = false;
        boolean result = sc.shoot(1, 0, false);
        assertEquals(expResult, result);
        result = sc.shoot(1, 2, false);
        assertEquals(expResult, result);
        result = sc.shoot(0, 1, false);
        assertEquals(expResult, result);
    }
}