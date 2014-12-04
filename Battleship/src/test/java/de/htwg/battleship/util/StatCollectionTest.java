// StatCollectionTest.java

package de.htwg.battleship.util;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * StatCollectionTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class StatCollectionTest {

    public StatCollectionTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isBetween method, of class StatCollection.
     */
    @Test
    public void testIsBetween() {
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