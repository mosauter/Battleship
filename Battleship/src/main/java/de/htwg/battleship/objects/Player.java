// Player.java
package de.htwg.battleship.objects;

/**
 * Player.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player {

    /**
     * Saves the board of the specified Player.
     */
    private final Board ownBoard;
    /**
     * Name of the Player.
     */
    private String name = "";

    /**
     * Public Constructor.
     * @param player1Board Board of the Player.
     */
    public Player(final Board player1Board) {
        ownBoard = player1Board;
    }

    /**
     * Setter for the name.
     * Only works one time.
     * @param name String name of the player
     */
    public final void setName(final String name) {
        if (this.name.isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Getter for the Name of the Player.
     * @return String
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Getter for the Board of the Player.
     * @return Board
     */
    public final Board getOwnBoard() {
        return ownBoard;
    }
}
