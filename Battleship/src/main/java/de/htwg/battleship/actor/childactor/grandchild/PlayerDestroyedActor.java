// PlayerDestroyedActor

package de.htwg.battleship.actor.childactor.grandchild;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import de.htwg.battleship.actor.messages.PlayerDestroyedMessage;
import de.htwg.battleship.actor.messages.ShipDestroyedMessage;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.LinkedList;
import java.util.List;

/**
 * PlayerDestroyedActor
 *
 * @author ms
 * @since 2016-06-07
 */
public class PlayerDestroyedActor extends AbstractGrandchildActor {
    public static final String ACTOR_NAME = "player_destroyer";
    private final ActorRef shipDestroyed = getContext()
        .actorOf(Props.create(ShipDestroyedActor.class),
                 ShipDestroyedActor.ACTOR_NAME);

    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof PlayerDestroyedMessage)) {
            logUnhandled(ACTOR_NAME, message);
            return;
        }

        PlayerDestroyedMessage playerDestroyedMessage =
            (PlayerDestroyedMessage) message;

        List<Future<Object>> futureList = createFutures(
            playerDestroyedMessage.getPlayer().getOwnBoard().getShipList(),
            playerDestroyedMessage.getPlayer());

        waitFutures(futureList);
    }

    private List<Future<Object>> createFutures(IShip[] ships, IPlayer player) {
        List<Future<Object>> futureList = new LinkedList<>();
        for (IShip ship : ships) {
            Future<Object> future = Patterns
                .ask(shipDestroyed, new ShipDestroyedMessage(ship, player),
                     TIMEOUT);
            futureList.add(future);
        }
        return futureList;
    }

    private void waitFutures(List<Future<Object>> futureList) {
        for (Future<Object> f : futureList) {
            try {
                boolean result = (boolean) Await.result(f, TIMEOUT.duration());
                if (!result) {
                    // a ship is not destroyed completely, so the player lives
                    getSender().tell(false, getSelf());
                    return;
                }
            } catch (Exception e) {
                // Timeout exception
            }
        }
        // all ships are destroyed, so the player is dead
        getSender().tell(true, getSelf());
    }

}
