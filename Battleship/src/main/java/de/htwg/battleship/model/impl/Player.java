// Player.java
package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;

/**
 * Player.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player implements IPlayer {

    /**
     * Saves the board of the specified Player.
     */
    private final IBoard ownBoard;
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

    @Override
    public final void setName(final String name) {
        if (this.name.isEmpty()) {
            this.name = name;
        }
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final IBoard getOwnBoard() {
        return ownBoard;
    }
}