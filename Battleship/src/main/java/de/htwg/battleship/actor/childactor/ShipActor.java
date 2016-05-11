// ShipController.java
package de.htwg.battleship.actor.childactor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.battleship.actor.childactor.grandchild.BorderTrueActor;
import de.htwg.battleship.actor.childactor.grandchild.CollisionActor;
import de.htwg.battleship.actor.messages.BorderMessage;
import de.htwg.battleship.actor.messages.CollisionMessage;
import de.htwg.battleship.actor.messages.ShipMessage;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ShipController to place the ships and test if there are collisions. to test
 * that it uses another controller which uses a chain-of-responsibility-pattern
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipActor extends UntypedActor {

    private static final Timeout TIMEOUT = new Timeout(3, TimeUnit.SECONDS);

    /**
     * Controller with a chain of responsibility.
     */
    private final ActorRef collisionActor =
        getContext().actorOf(Props.create(CollisionActor.class), "Collider");

    /**
     * Controller with a chain of responsibility.
     */
    private final ActorRef borderActor =
        getContext().actorOf(Props.create(BorderTrueActor.class), "Borderer");

    /**
     * Method to place a Ship on a field.
     *
     * @param ship      ship which should be placed
     * @param player    on which board the ship should be placed
     * @param boardSize
     *
     * @return true if the ship was placed false if there was a collision or the
     * ship is out of the field
     */
    public final boolean placeShip(final IShip ship, final IPlayer player,
                                   int boardSize) {
        Future<Object> futureBorder = Patterns
            .ask(borderActor, new BorderMessage(ship, boardSize), TIMEOUT);
        List<Future<Object>> futureCollision = playerShip(ship, player);
        boolean result = false;
        try {
            result = (boolean) Await.result(futureBorder, TIMEOUT.duration());
            for (Future<Object> collision : futureCollision) {
                if (!result) {
                    break;
                }
                result = !(boolean) Await.result(collision, TIMEOUT.duration());
            }
            if (result) {
                player.getOwnBoard().addShip(ship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Utility-Method to set a ship for a specified player.
     *
     * @param ship   to place
     * @param player which player
     *
     * @return true if it is set, false if not
     */
    private List<Future<Object>> playerShip(final IShip ship,
                                            final IPlayer player) {
        List<Future<Object>> futureList = new LinkedList<>();
        IShip[] shipList = player.getOwnBoard().getShipList();
        for (int i = 0; i < player.getOwnBoard().getShips(); i++) {
            Future<Object> future = Patterns
                .ask(collisionActor, new CollisionMessage(ship, shipList[i]),
                     TIMEOUT);
            futureList.add(future);
        }
        return futureList;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof ShipMessage)) {
            unhandled(message);
            return;
        }
        ShipMessage shipMessage = (ShipMessage) message;
        boolean result =
            placeShip(shipMessage.getShip(), shipMessage.getPlayer(),
                      shipMessage.getBoardSize());
        getSender().tell(result, getSelf());
    }
}
