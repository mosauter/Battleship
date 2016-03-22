// DestroyedTrueControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * DestroyedTrueControllerTest tests an implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class DestroyedTrueControllerTest extends AbstractTest {

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
        player = in.getInstance(IPlayer.class);
        ship = createShip(2, true, 3, 3);
        shoot = new ShootController(player, in.getInstance(IPlayer.class));
        ShipController sc = new ShipController();
        dc = new DestroyedTrueController();
        sc.placeShip(ship, player);
    }

    /**
     * Test of isDestroyed method, of class DestroyedTrueController.
     */
    @Test
    public final void testIsDestroyed() {
        boolean result = dc.responsibility(ship, player);
        assertFalse(result);
        shoot.shoot(3, 3, false);
        shoot.shoot(4, 3, false);
        result = dc.responsibility(ship, player);
        assertTrue(result);
    }
}
