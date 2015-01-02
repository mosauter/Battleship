// StatesTest.java

package de.htwg.battleship.util;

import static de.htwg.battleship.util.State.HIT;
import org.junit.Assert;
import org.junit.Test;

/**
 * StatesTest tests the states.
 * simple enum
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class StatesTest {

    /**
     * Saves a state.
     */
    private State state;

    /**
     * Test of values method, of class State.
     */
    @Test
    public final void testValues() {
        state = State.valueOf("HIT");
        Assert.assertEquals(state, HIT);
    }
}