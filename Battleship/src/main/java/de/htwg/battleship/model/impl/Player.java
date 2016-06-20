// Player.java
package de.htwg.battleship.model.impl;

import com.google.inject.Inject;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.StatCollection;

import java.util.Random;

/**
 * Player.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player implements IPlayer {

    private static final String NO_ID = "no-id";
    /**
     * Saves an unique identifier for a player.
     */
    private String id;
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
        id = NO_ID;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.setProfile(name, StatCollection.LOCAL_PLAYER_ID);
    }

    @Override
    public final void setProfile(final String name, String id) {
        if (this.name.isEmpty()) {
            this.name = name;
        }
        if (NO_ID.equals(this.id)) {
            if (StatCollection.LOCAL_PLAYER_ID.equals(id)) {
                this.id = Integer.toString((new Random()).nextInt(Integer.MAX_VALUE - 1) + 1);
            } else {
                this.id = id;
            }
        }
    }

    @Override
    public String getID() {
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
        return this.id.equals(player.getID());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
