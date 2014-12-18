// BorderFalseControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BorderFalseControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class BorderFalseControllerTest {

    BorderController bc = new BorderFalseController();
    public BorderFalseControllerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isIn method, of class BorderFalseController.
     */
    @Test
    public void testIsIn() {
        StatCollection.HEIGTH_LENGTH = 2;
        IShip ship = new Ship(4, false, 0, 0);
        assert (!bc.isIn(ship));
        StatCollection.HEIGTH_LENGTH = 2;
        ship = new Ship(4, false, 0, -1);
        assert (!bc.isIn(ship));
    }

}