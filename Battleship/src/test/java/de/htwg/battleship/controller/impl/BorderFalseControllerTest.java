// BorderFalseControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.Test;

/**
 * BorderFalseControllerTest tests one implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class BorderFalseControllerTest {

    /**
     * Saves a BorderController.
     */
    private final BorderController bc = new BorderFalseController();

    /**
     * Test of isIn method, of class BorderFalseController.
     */
    @Test
    public final void testIsIn() {
        StatCollection.heightLenght = 2;
        IShip ship = new Ship(4, false, 0, 0);
        assert (!bc.isIn(ship));
        StatCollection.heightLenght = 2;
        ship = new Ship(4, false, 0, -1);
        assert (!bc.isIn(ship));
    }
}