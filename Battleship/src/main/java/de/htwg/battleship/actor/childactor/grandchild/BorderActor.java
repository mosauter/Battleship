// BorderController.java

package de.htwg.battleship.actor.childactor.grandchild;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import de.htwg.battleship.messages.BorderMessage;
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

abstract class BorderActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof BorderMessage)) {
            unhandled(message);
            return;
        }
        handleBorderMessage((BorderMessage) message);
    }

    abstract void handleBorderMessage(BorderMessage message);
}

/**
 * BorderController implementation for the true ship-orientation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public class BorderTrueActor extends BorderActor {
    private ActorRef falseActor = getContext()
        .actorOf(Props.create(BorderFalseActor.class), "FALSE_BORDER");

    private boolean isIn(final IShip ship, int boardSize) {
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize() - 1;
        return isBetween(boardSize - 1, 0, xlow) &&
               isBetween(boardSize - 1, 0, xupp) &&
               isBetween(boardSize - 1, 0, ship.getY());
    }

    @Override
    void handleBorderMessage(BorderMessage borderMessage) {
        if (borderMessage.getShip().isOrientation()) {
            getSender().tell(
                isIn(borderMessage.getShip(), borderMessage.getBoardSize()),
                getSelf());
        } else {
            falseActor.forward(borderMessage, getContext());
        }
    }
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
