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
     * Place state that the second Player is at the turn to place a ship.
     */
    PLACE2,
    /**
     * Place state that there was an Error while placing a Ship.
     */
    PLACEERR,
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
    WIN2;
}
