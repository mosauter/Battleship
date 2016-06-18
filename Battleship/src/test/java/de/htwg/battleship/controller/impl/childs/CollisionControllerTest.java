// CollisionControllerTest.java

package de.htwg.battleship.controller.impl.childs;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CollisionControllerTest all implementation at once.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionControllerTest extends AbstractTest {

    /**
     * Saves the entire chain.
     */
    private CollisionController cc;
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

    public CollisionControllerTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        cc = new CollisionOrientationBothTrue();
        ship1 = createShip(2, false, 4, 0);
        ship2 = createShip(2, true, 2, 0);
        ship3 = createShip(3, true, 2, 0);
    }

    /**
     * Test of responsibility method, of class CollisionController.
     */
    @Test
    public final void testResponsibility() {
        boolean result = cc.isCollision(ship1, ship2);
        assertFalse(result);
        result = cc.isCollision(ship1, ship3);
        assertTrue(result);
    }
}
