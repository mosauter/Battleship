// Observable.java

package de.htwg.battleship.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Standard-Implementation of the Observer-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class Observable implements IObservable {

    /**
     * List to save the Observables.
     */
    private final List<IObserver> subscriber = new LinkedList<IObserver>();

    @Override
    public final void addObserver(final IObserver observer) {
        subscriber.add(observer);
    }

    @Override
    public final void removeObserver(final IObserver observer) {
        subscriber.add(observer);
    }

    @Override
    public final void removeAllObserver() {
        subscriber.clear();
    }

    @Override
    public final void notifyObserver() {
        for (IObserver sub : subscriber) {
            sub.update();
        }
    }

}
