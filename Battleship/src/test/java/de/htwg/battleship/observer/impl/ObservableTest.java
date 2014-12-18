// ObservableTest.java

package de.htwg.battleship.observer.impl;

import de.htwg.battleship.observer.IObserver;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ObservableTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class ObservableTest {

    public ObservableTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of notifyObserver method, of class Observable.
     */
    @Test
    public void testObserver() {
        ObservableTestObject oto = new ObservableTestObject();
        ObserverObject oo = new ObserverObject();
        oto.addObserver(oo);
        assertEquals(1, oo.getTestInt());
        oto.notifyObserver();
        assertEquals(0, oo.getTestInt());
    }

    class ObservableTestObject extends Observable {
    }
    
    class ObserverObject implements IObserver {

        private int testInt = 1;
        @Override
        public void update() {
            testInt = 0;
        }

        public int getTestInt() {
            return testInt;
        }
    }
}