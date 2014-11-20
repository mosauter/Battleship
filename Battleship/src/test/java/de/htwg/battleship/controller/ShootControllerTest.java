// ShootControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Board;
import de.htwg.battleship.objects.Player;
import de.htwg.battleship.objects.Ship;
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
    Player player;
    
    public ShootControllerTest() {
    }

    @Before
    public void setUp() {
        player = new Player(new Board(), new Board());
        Ship ship1 = new Ship(5, true, 3, 2 );
        Ship ship2 = new Ship(1, false, 6, 6);
        player.getShootBoard().addShip(ship1);
        player.getShootBoard().addShip(ship2);
        sc = new ShootController(player);
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
        boolean result = sc.shoot(3, 2);
        assertEquals(expResult, result);
        expResult = false;
        result = sc.shoot(3, 3);
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
        boolean result = sc.shoot(3, 2); // first Time true, second time false
        result = sc.shoot(3, 2);
        assertEquals(expResult, result);
        result = sc.shoot(9, 2);
        assertEquals(expResult, result);
        result = sc.shoot(2, 2);
        assertEquals(expResult, result);
    }
    
    @Test
    public void shootTestOneOriFalse() {
        boolean expResult = true;
        boolean result = sc.shoot(6, 6);
        assertEquals(expResult, result);
        expResult = false;
        result = sc.shoot(6, 6);
        assertEquals(expResult, result);
    }
    
    @Test
    public void shootTestTwoOriFalse() {
        boolean expResult = false;
        boolean result = sc.shoot(6, 5);
        assertEquals(expResult, result);
        result = sc.shoot(6, 8);
        assertEquals(expResult, result);
        result = sc.shoot(5, 6);
        assertEquals(expResult, result);
    }
}