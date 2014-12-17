//IObserver.java

package de.htwg.battleship.observer;

/**
 * IObserver is an Utility-Interface for an observable element.
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
     * To notify all the Observables which are subscribed in the List.
     */
    void notifyObserver();
}
