// DestroyedController.java

package de.htwg.battleship.actor.childactor.grandchild;

import akka.actor.ActorRef;
import akka.actor.Props;
import de.htwg.battleship.actor.messages.ShipDestroyedMessage;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;

/**
 * DestroyedController to check if a player is completely destroyed. implements
 * Chain-of-Responsibility
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
class ShipDestroyedActor extends AbstractGrandchildActor {

    static final String ACTOR_NAME = "ship_destroyer";
    /**
     * Saves the orientation of the ship. used to check if the implementation is
     * responsible for the specific case
     */
    boolean ship;
    /**
     * Reference to the next implementation of the controller.
     */
    private final ActorRef destroyedTrue = getContext()
        .actorOf(Props.create(DestroyedTrue.class), "orientation_true");
    private final ActorRef destroyedFalse = getContext()
        .actorOf(Props.create(DestroyedFalse.class), "orientation_false");

    @Override
    public void onReceive(final Object message) throws Exception {
        if (!(message instanceof ShipDestroyedMessage)) {
            logUnhandled(ACTOR_NAME, message);
            return;
        }
        handleMessage((ShipDestroyedMessage) message);
    }

    void handleMessage(ShipDestroyedMessage destroyedMessage) {
        if (destroyedMessage.getShip().isOrientation()) {
            destroyedTrue.forward(destroyedMessage, getContext());
        } else {
            destroyedFalse.forward(destroyedMessage, getContext());
        }
    }
}

abstract class AbstractShipDestroyedActor extends ShipDestroyedActor {
    @Override
    void handleMessage(ShipDestroyedMessage destroyedMessage) {
        getSender().tell(isDestroyed(destroyedMessage.getShip(),
                                     destroyedMessage.getPlayer()), getSelf());
    }

    abstract boolean isDestroyed(final IShip ship, final IPlayer player);

}

/**
 * Implementation for the Destroyed Controller. responsible for the case the
 * ships orientation is true
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class DestroyedTrue extends AbstractShipDestroyedActor {

    boolean isDestroyed(final IShip ship, final IPlayer player) {
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize();
        int y = ship.getY();
        IBoard board = player.getOwnBoard();
        for (int i = xlow; i < xupp; i++) {
            if (!board.isHit(i, y)) {
                return false;
            }
        }
        return true;
    }

}

/**
 * Implementation for the Destroyed Controller. responsible for the case the
 * ships orientation is false
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class DestroyedFalse extends AbstractShipDestroyedActor {

    boolean isDestroyed(final IShip ship, final IPlayer player) {
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize();
        int x = ship.getX();
        IBoard board = player.getOwnBoard();
        for (int i = ylow; i < yupp; i++) {
            if (!board.isHit(x, i)) {
                return false;
            }
        }
        return true;
    }
}
