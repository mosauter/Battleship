// StatCollectionTest.java

package de.htwg.battleship.util;

import java.util.Map;
import java.util.Set;
import org.junit.Assert;
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

    /**
     * Test for the createBorder method.
     */
    @Test
    public final void testCreateBorder() {
        StringBuilder sb = new StringBuilder();
        StatCollection.heightLenght = 1;
        String expResult = "  a";
        sb = StatCollection.createBorder(sb);
        assertEquals(expResult, sb.toString());
    }

    /**
     * Test for the createMap method.
     */
    @Test
    public final void testCreateMap() {
        Map<Integer, Set<Integer>> map = StatCollection.createMap();
        for (int i = 0; i < StatCollection.heightLenght; i++) {
            Assert.assertTrue(map.get(i).isEmpty());
        }
    }
}