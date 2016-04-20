// CollisionOrientationFirstFalseTest.java

package de.htwg.battleship.controller.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CollisionOrientationFirstFalseTest tests an implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationFirstFalseTest extends AbstractTest {

    /**
     * Saves the implementation.
     */
    private final CollisionOrientationFirstFalse cc;
    /**
     * Saves first ship.
     */
    private final IShip ship1;
    /**
     * Saves second ship.
     */
    private final IShip ship2;
    /**
     * Saves third ship.
     */
    private final IShip ship3;

    /**
     * Public Constructor.
     */
    public CollisionOrientationFirstFalseTest(AbstractModule module) {
        this.createInjector(module);
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        cc = new CollisionOrientationFirstFalse();
        ship1 = createShip(2, false, 4, 0);
        ship2 = createShip(2, true, 2, 0);
        ship3 = createShip(3, true, 2, 0);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationFirstFalse.
     */
    @Test
    public final void testIsCollisionFalseUnder() {
        boolean result = cc.isCollision(ship1, ship2);
        assertFalse(result);
    }

    /**
     * Test of isCollision method.
     */
    @Test
    public final void testIsCollisionFalseUpper() {
        IShip sh = createShip(1, true, 5, 0);
        boolean result = cc.isCollision(ship1, sh);
        assertFalse(result);
    }

    /**
     * Test of isCollison method.
     */
    @Test
    public final void testIsCollisionTrue() {
        boolean result = cc.isCollision(ship1, ship3);
        assertTrue(result);
    }

    /**
     * Test of isCollision method.
     */
    @Test
    public final void testIsCollisionFalseYUnder() {
        IShip sh = createShip(1, true, 4, -1);
        boolean result = cc.isCollision(ship1, sh);
        assertFalse(result);
    }
}
