// Player.java
package de.htwg.battleship.objects;

/**
 * Player.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player {

    private final Board ownBoard;
    private final Board shootBoard;
    private String name = "";

    public Player(Board player1Board, Board player2Board) {
        ownBoard = player1Board;
        shootBoard = player2Board;
    }

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

    public final Board getOwnBoard() {
        return ownBoard;
    }

    public final Board getShootBoard() {
        return shootBoard;
    }
}
