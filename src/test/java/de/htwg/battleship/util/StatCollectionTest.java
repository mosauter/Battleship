// StatCollectionTest.java

package de.htwg.battleship.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StatCollectionTest tests the utility collection of some methods.
 *
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
        int xlow = 3;
        int xupp = 5;
        int x = 6;
        boolean result = StatCollection.isBetween(xupp, xlow, x);
        assertFalse(result);
        x = 5;
        result = StatCollection.isBetween(xupp, xlow, x);
        assertTrue(result);
        x = 3;
        result = StatCollection.isBetween(xupp, xlow, x);
        assertTrue(result);
        x = 2;
        result = StatCollection.isBetween(xupp, xlow, x);
        assertFalse(result);
    }

    /**
     * Test for the createBorder method.
     */
    @Test
    public final void testCreateBorder() {
        StringBuilder sb = new StringBuilder();
        String expResult = "  a";
        sb = StatCollection.createBorder(sb, 1);
        assertEquals(expResult, sb.toString());
    }

    /**
     * Test for the createMap method.
     */
    @Test
    public final void testCreateMap() {
        int heightLenght = 10;
        Map<Integer, Set<Integer>> map = StatCollection.createMap(heightLenght);
        for (int i = 0; i < heightLenght; i++) {
            Assert.assertTrue(map.get(i).isEmpty());
        }
    }
}
