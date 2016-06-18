// BorderTrueControllerTest.java

package de.htwg.battleship.controller.impl.childs;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import org.junit.Test;

/**
 * BorderTrueControllerTest tests one implementation of the chain.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class BorderTrueControllerTest extends AbstractTest {

    public static final int HEIGHT_LENGTH = 2;
    /**
     * Saves a BorderController.
     */
    private final BorderController bc = new BorderTrueController(HEIGHT_LENGTH);

    public BorderTrueControllerTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Test of isIn method, of class BorderTrueController.
     */
    @Test
    public final void testIsIn() {
        IShip ship = createShip(4, true, 0, 0);
        assert (!bc.isIn(ship));
        ship = createShip(4, true, -1, 0);
        assert (!bc.isIn(ship));
    }
}
