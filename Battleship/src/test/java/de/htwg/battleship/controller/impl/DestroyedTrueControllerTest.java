// DestroyedTrueControllerTest.java

package de.htwg.battleship.controller.impl;

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
 * DestroyedTrueControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class DestroyedTrueControllerTest {

    DestroyedController dc;
    IShip ship;
    IPlayer player;
    ShipController sc;
    ShootController shoot;

    public DestroyedTrueControllerTest() {
    }

    @Before
    public void setUp() {
        player = new Player(new Board());
        ship = new Ship(2, true, 3, 3);
        shoot = new ShootController(player, new Player(new Board()));
        sc = new ShipController();
        dc = new DestroyedTrueController();
        sc.placeShip(ship, player);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isDestroyed method, of class DestroyedTrueController.
     */
    @Test
    public void testIsDestroyed() {
        boolean expRes = false;
        boolean result = dc.responsibility(ship, player);
        assertEquals(expRes, result);
        shoot.shoot(3, 3, false);
        shoot.shoot(4, 3, false);
        expRes = true;
        result = dc.responsibility(ship, player);
        assertEquals(expRes, result);
    }

}