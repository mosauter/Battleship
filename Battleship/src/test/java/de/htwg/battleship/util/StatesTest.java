// StatesTest.java

package de.htwg.battleship.util;

import static de.htwg.battleship.util.State.HIT;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * StatesTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class StatesTest {

    State state;
    public StatesTest() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class State.
     */
    @Test
    public void testValues() {
        state = State.valueOf("HIT");
        Assert.assertEquals(state, HIT);
    }
}