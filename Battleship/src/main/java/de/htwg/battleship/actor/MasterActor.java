// MasterActor

package de.htwg.battleship.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import de.htwg.battleship.actor.childactor.ShipActor;
import de.htwg.battleship.actor.childactor.ShootActor;
import de.htwg.battleship.actor.childactor.WinActor;
import de.htwg.battleship.actor.messages.ShipMessage;
import de.htwg.battleship.actor.messages.ShootMessage;
import de.htwg.battleship.actor.messages.WinMessage;

/**
 * MasterActor
 *
 * @author ms
 * @since 2016-05-10
 */
class MasterActor extends AbstractActor {

    private static final String ACTOR_NAME = "master";
    private final ActorRef shootActors = getContext()
        .actorOf(Props.create(ShootActor.class), ShootActor.ACTOR_NAME);
    private final ActorRef shipActor = getContext()
        .actorOf(Props.create(ShipActor.class), ShipActor.ACTOR_NAME);
    private final ActorRef winActor =
        getContext().actorOf(Props.create(WinActor.class), WinActor.ACTOR_NAME);

    @Override
    public void onReceive(final Object message) throws Exception {
        if (message instanceof ShootMessage) {
            shootActors.forward(message, getContext());
        } else if (message instanceof ShipMessage) {
            shipActor.forward(message, getContext());
        } else if (message instanceof WinMessage) {
            winActor.forward(message, getContext());
        } else {
            logUnhandled(ACTOR_NAME, message);
        }
    }
}
