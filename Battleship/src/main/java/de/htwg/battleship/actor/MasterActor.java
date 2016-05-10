// MasterActor

package de.htwg.battleship.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import de.htwg.battleship.actor.childactor.ShootActor;
import de.htwg.battleship.actor.messages.ShootMessage;

/**
 * MasterActor
 *
 * @author ms
 * @since 2016-05-10
 */
public class MasterActor extends UntypedActor {

    ActorRef shootActors = getContext().actorOf(Props.create(ShootActor.class), "Shooters");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ShootMessage) {
            shootActors.forward(message, getContext());
        }
        unhandled(message);
    }
}
