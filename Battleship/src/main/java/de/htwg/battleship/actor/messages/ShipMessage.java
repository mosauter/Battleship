// ShipMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;

/**
 * ShipMessage
 *
 * @author ms
 * @since 2016-05-11
 */
public class ShipMessage {
    private final int boardSize;
    private final IPlayer player;
    private final IShip ship;

    public ShipMessage(int boardSize, IPlayer player, IShip ship) {

        this.boardSize = boardSize;
        this.player = player;
        this.ship = ship;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public IShip getShip() {
        return ship;
    }
}
