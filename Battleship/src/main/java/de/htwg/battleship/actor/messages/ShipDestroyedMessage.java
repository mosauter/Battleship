// DestroyedMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;

/**
 * DestroyedMessage
 *
 * @author ms
 * @since 2016-06-01
 */
public class ShipDestroyedMessage {
    private final IShip ship;
    private final IPlayer player;

    public ShipDestroyedMessage(final IShip ship, final IPlayer player) {
        this.ship = ship;
        this.player = player;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public IShip getShip() {
        return ship;
    }
}
