// State.java

package de.htwg.battleship.util;

/**
 * Enum with different State for the game.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public enum State {
    /**
     * Start state.
     */
    START,
    /**
     * Get state for the name of the first Player.
     */
    GETNAME1,
    /**
     * Get state for the name of the second Player.
     */
    GETNAME2,
    /**
     * Place state that the first Player is at the turn to place a ship.
     */
    PLACE1,
    /**
     * Place state that the first Player completed the placing of the ships.
     */
    FINALPLACE1,
    /**
     * Place state that the second Player is at the turn to place a ship.
     */
    PLACE2,
    /**
     * Place state that the second Player completed the placing of the ships.
     */
    FINALPLACE2,
    /**
     * State that the first player shoots.
     */
    SHOOT1,
    /**
     * State that the second player shoots.
     */
    SHOOT2,
    /**
     * State for a hit.
     */
    HIT,
    /**
     * State for a miss.
     */
    MISS,
    /**
     * State that the first player has won.
     */
    WIN1,
    /**
     * State that the second player has won.
     */
    WIN2,
    /**
     * State that the game has ended, there you can go back to the
     * start state again.
     */
    END,

    //  -----------------   Error States    ------------------------

    /*
     * Error States are set and also reset by the controller!
     */

    //  -----------------   Error States    ------------------------
    /**
     * Place state that there was an Error while placing a Ship.
     * Error State
     */
    PLACEERR,
    /**
     * Interim state that there was a method call which
     * was wrong in the current state.
     * Error State
     */
    WRONGINPUT;
}
