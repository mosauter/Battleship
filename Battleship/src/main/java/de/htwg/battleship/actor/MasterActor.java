// MasterActor

package de.htwg.battleship.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import de.htwg.battleship.actor.childactor.ShipActor;
import de.htwg.battleship.actor.childactor.ShootActor;
import de.htwg.battleship.actor.messages.ShipMessage;
import de.htwg.battleship.actor.messages.ShootMessage;

/**
 * MasterActor
 *
 * @author ms
 * @since 2016-05-10
 */
public class MasterActor extends UntypedActor {

    private ActorRef shootActors =
        getContext().actorOf(Props.create(ShootActor.class), "Shooters");
    private ActorRef shipActor =
        getContext().actorOf(Props.create(ShipActor.class), "Shipper");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ShootMessage) {
            shootActors.forward(message, getContext());
        } else if (message instanceof ShipMessage) {
            shipActor.forward(message, getContext());
        }
        unhandled(message);
    }
}
