// Player.java
package de.htwg.battleship.model.impl;

import com.google.inject.Inject;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;

import java.util.Random;

/**
 * Player.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player implements IPlayer {

    /**
     * Saves an unique identifier for a player.
     */
    private int id;

    /**
     * Saves the board of the specified Player.
     */
    private IBoard ownBoard;
    /**
     * Name of the Player.
     */
    private String name = "";

    /**
     * Public Constructor.
     *
     * @param player1Board Board of the Player.
     */
    @Inject
    public Player(final IBoard player1Board) {
        ownBoard = player1Board;
        id = -2;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.setProfile(name, -1);
    }

    @Override
    public final void setProfile(final String name, int id) {
        if (this.name.isEmpty()) {
            this.name = name;
        }
        if (this.id == -2) {
            if (id == -1) {
                this.id = (new Random()).nextInt(Integer.MAX_VALUE - 1) + 1;
            } else {
                this.id = id;
            }
        }
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public final IBoard getOwnBoard() {
        return ownBoard;
    }

    @Override
    @Inject
    public final void resetBoard(IBoard board) {
        this.ownBoard = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }

        Player player = (Player) o;
        return this.id == player.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
