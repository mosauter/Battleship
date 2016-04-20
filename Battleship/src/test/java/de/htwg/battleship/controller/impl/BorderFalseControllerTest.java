// BorderFalseControllerTest.java

package de.htwg.battleship.controller.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import org.junit.Test;

/**
 * BorderFalseControllerTest tests one implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class BorderFalseControllerTest extends AbstractTest {

    private static final int HEIGHT_LENGTH = 2;

    public BorderFalseControllerTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Saves a BorderController.
     */
    private final BorderController bc =
        new BorderFalseController(HEIGHT_LENGTH);

    /**
     * Test of isIn method, of class BorderFalseController.
     */
    @Test
    public final void testIsIn() {
        IShip ship = new Ship(4, false, 0, 0);
        assert (!bc.isIn(ship));
        ship = new Ship(4, false, 0, -1);
        assert (!bc.isIn(ship));
    }
}
