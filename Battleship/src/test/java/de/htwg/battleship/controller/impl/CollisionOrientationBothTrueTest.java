// CollisionOrientationBothTrueTest.java

package de.htwg.battleship.controller.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CollisionOrientationBothTrueTest tests an implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationBothTrueTest extends AbstractTest {

    /**
     * Saves the implementation.
     */
    private CollisionOrientationBothTrue cc;
    /**
     * Saves first IShip.
     */
    private IShip ship1;
    /**
     * Saves second IShip.
     */
    private IShip ship2;
    /**
     * Saves third IShip.
     */
    private IShip ship3;

    public CollisionOrientationBothTrueTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        cc = new CollisionOrientationBothTrue();
        ship1 = createShip(3, true, 4, 4);
        ship2 = createShip(3, true, 4, 5);
        ship3 = createShip(3, true, 6, 4);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationBothTrue.
     */
    @Test
    public final void testIsCollision() {
        boolean result = cc.isCollision(ship1, ship2);
        assertFalse(result);
        result = cc.isCollision(ship1, ship3);
        assertTrue(result);
    }
}
