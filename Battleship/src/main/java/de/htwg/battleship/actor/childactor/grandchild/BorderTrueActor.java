// BorderTrueActor

package de.htwg.battleship.actor.childactor.grandchild;

import akka.actor.ActorRef;
import akka.actor.Props;
import de.htwg.battleship.actor.messages.BorderMessage;
import de.htwg.battleship.model.IShip;

import static de.htwg.battleship.util.StatCollection.isBetween;

/**
 * BorderController implementation for the true ship-orientation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public class BorderTrueActor extends BorderActor {
    public static final String ACTOR_NAME = "borderer";
    private ActorRef falseActor = getContext()
        .actorOf(Props.create(BorderFalseActor.class),
                 BorderFalseActor.ACTOR_NAME);

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
