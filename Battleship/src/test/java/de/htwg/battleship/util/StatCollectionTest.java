// StatCollectionTest.java

package de.htwg.battleship.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * StatCollectionTest tests the utility collection of some methods.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class StatCollectionTest {

    /**
     * Test of isBetween method, of class StatCollection.
     */
    @Test
    public final void testIsBetween() {
        boolean expResult = false;
        int xlow = 3;
        int xupp = 5;
        int x = 6;
        boolean result = StatCollection.isBetween(xupp, xlow, x);
        assertEquals(expResult, result);
        expResult = true;
        x = 5;
        result = StatCollection.isBetween(xupp, xlow, x);
        assertEquals(expResult, result);
        x = 3;
        result = StatCollection.isBetween(xupp, xlow, x);
        assertEquals(expResult, result);
        expResult = false;
        x = 2;
        result = StatCollection.isBetween(xupp, xlow, x);
        assertEquals(expResult, result);
    }
}