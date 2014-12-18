// States.java

package de.htwg.battleship.util;

/**
 * Enum with different States for the game.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public enum States {
    /**
     * Start state.
     *//**
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
     * Place state that the second Player completed the placing of the ships.
     */
    FINALPLACE2,
    /**
     * Place state that the second Player is at the turn to place a ship.
     */
    PLACE2,
    /**
     * Place state that there was an Error while placing a Ship.
     */
    PLACEERR,
    /**
     * States that the first player shoots.
     */
    SHOOT1,
    /**
     * States that the second player shoots.
     */
    SHOOT2,
    /**
     * States for a hit.
     */
    HIT,
    /**
     * States for a miss.
     */
    MISS,
    /**
     * States that the first player has won.
     */
    WIN1,
    /**
     * States that the second player has won.
     */
    WIN2,
    /**
     * States that there was a wrong Input.
     */
    WRONGINPUT;
}
