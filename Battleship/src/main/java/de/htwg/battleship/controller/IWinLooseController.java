//WinLooseController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.observer.IObservable;

/**
 * IWinLooseController is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public interface IWinLooseController extends IObservable {

    /**
     * Checs if someone has won.
     * @return IPlayer who has won.
     */
    IPlayer win();
}
