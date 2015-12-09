// IPlayer.java

package de.htwg.battleship.model;

/**
 * IPlayer is an Utility-Interface.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IPlayer {

    /**
     * Getter for the Name of the Player.
     *
     * @return String
     */
    String getName();

    /**
     * Setter for the name.
     * Only works one time.
     *
     * @param name
     *            String name of the player
     */
    void setName(String name);

    /**
     * Getter for the Board of the Player.
     *
     * @return IBoard
     */
    IBoard getOwnBoard();

    /**
     * Method resets the board of the player.
     */
    void resetBoard(IBoard board);
}
