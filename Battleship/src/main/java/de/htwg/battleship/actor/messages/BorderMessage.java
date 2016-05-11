// BorderMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IShip;

/**
 * BorderMessage
 *
 * @author ms
 * @since 2016-05-11
 */
public class BorderMessage {
    private final IShip ship;
    private final int boardSize;

    public BorderMessage(IShip ship, int boardSize) {

        this.ship = ship;
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public IShip getShip() {
        return ship;
    }
}
