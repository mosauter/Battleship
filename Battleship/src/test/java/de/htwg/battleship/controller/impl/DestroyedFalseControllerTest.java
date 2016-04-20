// DestroyedFalseControllerTest.java

package de.htwg.battleship.controller.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * DestroyedFalseControllerTest an implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class DestroyedFalseControllerTest extends AbstractTest {

    /**
     * Saves the implementation.
     */
    private DestroyedController dc;
    /**
     * Saves a ship.
     */
    private IShip ship;
    /**
     * Saves the player.
     */
    private IPlayer player;
    /**
     * Saves the shoot controller.
     */
    private ShootController shoot;

    public DestroyedFalseControllerTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.shipNumberMax = 5;
        StatCollection.heightLenght = 10;
        player = injector.getInstance(IPlayer.class);
        ship = createShip(2, false, 3, 3);
        shoot =
            new ShootController(player, injector.getInstance(IPlayer.class));
        ShipController sc = new ShipController();
        dc = new DestroyedTrueController();
        sc.placeShip(ship, player);
    }

    /**
     * Test of isDestroyed method, of class DestroyedFalseController.
     */
    @Test
    public final void testIsDestroyed() {
        boolean result = dc.responsibility(ship, player);
        assertFalse(result);
        shoot.shoot(3, 3, false);
        shoot.shoot(3, 4, false);
        result = dc.responsibility(ship, player);
        assertTrue(result);
    }
}
