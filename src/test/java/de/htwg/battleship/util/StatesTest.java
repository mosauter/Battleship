// StatesTest.java

package de.htwg.battleship.util;

import org.junit.Assert;
import org.junit.Test;

import static de.htwg.battleship.util.State.HIT;

/**
 * StatesTest tests the states. simple enum
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class StatesTest {

    /**
     * Test of values method, of class State.
     */
    @Test
    public final void testValues() {
        State state = State.valueOf("HIT");
        Assert.assertEquals(state, HIT);
    }
}
