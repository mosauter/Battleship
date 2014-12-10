//IObserver.java

package de.htwg.battleship.util;

/**
 * IObserver is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IObservable {

    /**
     * To add a Observable to the subscribers list.
     * @param observer object has to implement the IObserver
     */
    void addObserver(IObserver observer);
    /**
     * To remove a Observable from the subscriber list.
     * @param observer object has to implement the IObserver
     */
    void removeObserver(IObserver observer);
    /**
     * To clear the List.
     */
    void removeAllObserver();
    /**
     * To notify all the Observables which are subscribed in the List.
     */
    void notifyObserver();
}
