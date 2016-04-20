// CollisionOrientationFirstTrueTest.java

package de.htwg.battleship.controller.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CollisionOrientationFirstTrueTest tests an implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationFirstTrueTest extends AbstractTest {

    /**
     * Saves the implementation.
     */
    private CollisionOrientationFirstTrue cc;
    /**
     * Saves the first ship.
     */
    private IShip ship1;
    /**
     * Saves the second ship.
     */
    private IShip ship2;
    /**
     * Saves the third ship.
     */
    private IShip ship3;

    public CollisionOrientationFirstTrueTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        cc = new CollisionOrientationFirstTrue();
        ship1 = createShip(2, true, 4, 4);
        ship2 = createShip(3, false, 4, 5);
        ship3 = createShip(3, false, 5, 4);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationFirstTrue.
     */
    @Test
    public final void testIsCollision() {
        boolean result = cc.isCollision(ship1, ship2);
        assertFalse(result);
        result = cc.isCollision(ship1, ship3);
        assertTrue(result);
    }

    @Test
    public final void testIsCollisionFalse() {
        IShip sh = createShip(1, false, -1, 4);
        assertFalse(cc.isCollision(ship1, sh));
    }
}
