// CollisionOrientationBothFalseTest.java

package de.htwg.battleship.controller.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CollisionOrientationBothFalseTest tests a implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationBothFalseTest extends AbstractTest {

    /**
     * Saves the implementation.
     */
    private CollisionController cc;
    /**
     * Saves first ship.
     */
    private IShip ship1;
    /**
     * Saves second ship.
     */
    private IShip ship2;
    /**
     * Saves third ship.
     */
    private IShip ship3;

    public CollisionOrientationBothFalseTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        cc = new CollisionOrientationBothFalse();
        ship1 = createShip(3, false, 1, 1);
        ship2 = createShip(5, false, 3, 5);
        ship3 = createShip(3, false, 1, 1);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationBothFalse.
     */
    @Test
    public final void testIsCollision() {
        boolean result = cc.isCollision(ship1, ship2);
        assertFalse(result);
        result = cc.isCollision(ship1, ship3);
        assertTrue(result);
    }

    @Test
    public final void testIsCollisionFalseUnder() {
        IShip sh = createShip(1, false, -1, 1);
        Assert.assertFalse(cc.isCollision(ship1, sh));
    }
}
