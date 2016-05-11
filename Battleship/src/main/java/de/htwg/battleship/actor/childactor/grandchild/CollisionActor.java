// CollisionController.java

package de.htwg.battleship.actor.childactor.grandchild;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import de.htwg.battleship.actor.messages.CollisionMessage;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;

/**
 * Controller for checking if ship collides. use the Chain-of-Responsibility-Pattern
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionActor extends UntypedActor {

    private final ActorRef same =
        getContext().actorOf(Props.create(SameCollision.class), "both_same");
    private final ActorRef different = getContext()
        .actorOf(Props.create(DifferentCollision.class), "first_true");

    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof CollisionMessage)) {
            unhandled(message);
            return;
        }
        CollisionMessage collisionMessage = (CollisionMessage) message;
        if (collisionMessage.getShip1().isOrientation() ==
            collisionMessage.getShip2().isOrientation()) {
            // orientations are the same
            same.forward(message, getContext());
        } else {
            // orientations are different
            different.forward(message, getContext());
        }
    }
}

abstract class ShipCollisionActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof CollisionMessage)) {
            unhandled(message);
            return;
        }
        final boolean collision =
            handleCollisionMessage((CollisionMessage) message);
        getSender().tell(collision, getSelf());
    }

    abstract boolean handleCollisionMessage(final CollisionMessage message);
}

class SameCollision extends ShipCollisionActor {
    private boolean isCollision(final IShip firstShip, final IShip secondShip) {
        final int firstLow, firstFix, secLow, secFix;
        if (firstShip.isOrientation()) {
            firstLow = firstShip.getX();
            firstFix = firstShip.getY();
            secLow = secondShip.getX();
            secFix = secondShip.getY();
        } else {
            firstLow = firstShip.getY();
            firstFix = firstShip.getX();
            secLow = secondShip.getY();
            secFix = secondShip.getX();
        }
        final int firstUp = firstLow + firstShip.getSize() - 1;
        final int secUp = secLow + secondShip.getSize() - 1;
        for (int i = secLow; i <= secUp; i++) {
            if (StatCollection.isBetween(firstUp, firstLow, i) &&
                secFix == firstFix) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean handleCollisionMessage(final CollisionMessage message) {
        return isCollision(message.getShip1(), message.getShip2());
    }
}

class DifferentCollision extends ShipCollisionActor {
    /**
     * Check if the ships collide. To use this method the ships must have
     * different orientations.
     *
     * @param vertical   the first ship
     * @param horizontal the second ship with a different orientation
     *
     * @return true if the ships collide
     */
    private boolean checkShipsDifferent(IShip vertical, IShip horizontal) {
        final int horizontalLow = horizontal.getX();
        final int horizontalUp = horizontalLow + horizontal.getSize() - 1;
        final int horizontalFix = horizontal.getY();
        final int verticalLow = vertical.getY();
        final int verticalUp = verticalLow + vertical.getSize() - 1;
        final int verticalFix = vertical.getX();
        return
            StatCollection.isBetween(verticalUp, verticalLow, horizontalFix) &&
            StatCollection.isBetween(horizontalUp, horizontalLow, verticalFix);
    }

    @Override
    boolean handleCollisionMessage(final CollisionMessage message) {
        IShip vertical, horizontal;
        if (message.getShip1().isOrientation()) {
            horizontal = message.getShip1();
            vertical = message.getShip2();
        } else {
            horizontal = message.getShip2();
            vertical = message.getShip1();
        }
        return checkShipsDifferent(vertical, horizontal);
    }
}
