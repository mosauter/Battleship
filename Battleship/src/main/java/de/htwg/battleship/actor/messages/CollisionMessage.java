// CollisionMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IShip;

/**
 * CollisionMessage
 *
 * @author ms
 * @since 2016-05-11
 */
public class CollisionMessage {
    private final IShip ship1;
    private final IShip ship2;

    public CollisionMessage(IShip ship1, IShip ship2) {
        this.ship1 = ship1;
        this.ship2 = ship2;
    }

    public IShip getShip1() {
        return ship1;
    }

    public IShip getShip2() {
        return ship2;
    }
}
