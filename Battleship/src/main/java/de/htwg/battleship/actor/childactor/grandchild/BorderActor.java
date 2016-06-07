// BorderController.java

package de.htwg.battleship.actor.childactor.grandchild;

import de.htwg.battleship.actor.messages.BorderMessage;
import de.htwg.battleship.model.IShip;

import static de.htwg.battleship.util.StatCollection.isBetween;

/**
 * BorderController which checks of the ship is in the field. use the
 * Chain-of-Responsibility-Pattern
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */

abstract class BorderActor extends AbstractGrandchildActor {
    static final String ACTOR_NAME = "borderer";

    @Override
    public void onReceive(final Object message) throws Exception {
        if (!(message instanceof BorderMessage)) {
            logUnhandled(ACTOR_NAME, message);
            return;
        }
        handleBorderMessage((BorderMessage) message);
    }

    abstract void handleBorderMessage(BorderMessage message);
}

/**
 * BorderController implementation for the false ship-orientation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class BorderFalseActor extends BorderActor {
    private boolean isIn(final IShip ship, int boardSize) {
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize() - 1;
        int x = ship.getX();
        return isBetween(boardSize - 1, 0, ylow) &&
               isBetween(boardSize - 1, 0, yupp) &&
               isBetween(boardSize - 1, 0, x);
    }

    @Override
    void handleBorderMessage(BorderMessage borderMessage) {
        getSender()
            .tell(isIn(borderMessage.getShip(), borderMessage.getBoardSize()),
                  getSelf());
    }
}
