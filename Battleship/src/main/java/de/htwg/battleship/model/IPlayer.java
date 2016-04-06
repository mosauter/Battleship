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
     * Setter only for the name. As ID it sets a negative number. Should only be
     * used in UIs where an ID is not given by a login system.
     *
     * @param name the name of the player
     */
    void setName(String name);

    /**
     * Setter for the whole profile of a player. Only works one time.
     *
     * @param name String name of the player
     * @param id   of the player should be a positive number given by a login
     *             system
     */
    void setProfile(String name, int id);

    /**
     * Getter for the unique player id.
     * @return the id as an int
     */
    int getID();

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
