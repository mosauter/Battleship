// BorderTrueControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.Test;

/**
 * BorderTrueControllerTest tests one implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class BorderTrueControllerTest {

    /**
     * Saves a BorderController.
     */
    private final BorderController bc = new BorderTrueController();

    /**
     * Test of isIn method, of class BorderTrueController.
     */
    @Test
    public final void testIsIn() {
        StatCollection.heightLenght = 2;
        IShip ship = new Ship(4, true, 0, 0);
        assert (!bc.isIn(ship));
        StatCollection.heightLenght = 2;
        ship = new Ship(4, true, -1, 0);
        assert (!bc.isIn(ship));
    }
}