// ActorFactory

package de.htwg.battleship.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * ActorFactory
 *
 * @author ms
 * @since 2016-05-10
 */
public class ActorFactory {
    private static final String SYSTEM_NAME = "Battleship_Actor_System";
    private static ActorSystem actorSystem = ActorSystem.create(SYSTEM_NAME);
    private static ActorRef master = actorSystem.actorOf(Props.create(MasterActor.class), "Master");

    public static ActorRef getMasterRef() {
        return master;
    }

    public static ActorSystem getActorSystem() {
        return actorSystem;
    }
}
