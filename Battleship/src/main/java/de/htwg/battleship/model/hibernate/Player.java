// Player.java
package de.htwg.battleship.model.hibernate;

import com.google.inject.Inject;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Player.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player implements IPlayer, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    /**
     * Saves the board of the specified Player.
     */
    @OneToOne
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
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void setName(final String name) {
        if (this.name.isEmpty()) {
            this.name = name;
        }
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
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;
        return this.name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
