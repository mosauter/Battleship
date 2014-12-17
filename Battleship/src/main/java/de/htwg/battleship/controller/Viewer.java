//Viewer.java

package de.htwg.battleship.controller;

import de.htwg.battleship.util.State;

/**
 * Viewer is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public interface Viewer {

    /**
     * To get a String - Representation of something the
     * Viewer has to implement.
     * @return String
     */
    String getString();
    /**
     * To get the current state of the game.
     * @return enum State
     */
    State getCurrentState();
}
