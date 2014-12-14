// DestroyedControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IShipController;
import de.htwg.battleship.controller.IShootController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * DestroyedControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class DestroyedControllerTest {

    DestroyedController dc;
    IShip ship;
    IPlayer player;
    IShipController sc;
    IShootController shoot;

    public DestroyedControllerTest() {
    }

    @Before
    public void setUp() {
        player = new Player(new Board());
        ship = new Ship(2, false, 3, 3);
        shoot = new ShootController(player, new Player(new Board()));
        sc = new ShipController(player, new Player(new Board()));
        dc = new DestroyedTrueController();
        sc.placeShip(ship, true);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isDestroyed method, of class DestroyedController.
     */
    @Test
    public void testIsDestroyed() {
    }
}