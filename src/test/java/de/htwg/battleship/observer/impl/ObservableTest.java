// ObservableTest.java

package de.htwg.battleship.observer.impl;

import de.htwg.battleship.observer.IObserver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ObservableTest tests the observable.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class ObservableTest {

    /**
     * Test of notifyObserver method, of class Observable.
     */
    @Test
    public final void testObserver() {
        ObservableTestObject oto = new ObservableTestObject();
        ObserverObject oo = new ObserverObject();
        oto.addObserver(oo);
        assertEquals(1, oo.testInt);
        oto.notifyObserver();
        assertEquals(0, oo.testInt);
    }

    /**
     * Test utility object.
     */
    private class ObservableTestObject extends Observable {}

    /**
     * Test utility object.
     */
    private class ObserverObject implements IObserver {

        /**
         * Test int.
         */
        private int testInt = 1;

        @Override
        public void update() {
            testInt = 0;
        }
    }
}
