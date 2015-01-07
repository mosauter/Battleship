// DestroyedTrueControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * DestroyedTrueControllerTest tests an implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class DestroyedTrueControllerTest {

    /**
     * Saves the implementation.
     */
    private DestroyedController dc;
    /**
     * Saves a ship.
     */
    private IShip ship;
    /**
     * Saves a player.
     */
    private IPlayer player;
    /**
     * Saves the ship controller.
     */
    private ShipController sc;
    /**
     * Saves the shoot controller.
     */
    private ShootController shoot;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        player = new Player(new Board());
        ship = new Ship(2, true, 3, 3);
        shoot = new ShootController(player, new Player(new Board()));
        sc = new ShipController();
        dc = new DestroyedTrueController();
        sc.placeShip(ship, player);
    }

    /**
     * Test of isDestroyed method, of class DestroyedTrueController.
     */
    @Test
    public final void testIsDestroyed() {
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