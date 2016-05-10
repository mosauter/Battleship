// ShootActor

package de.htwg.battleship.actor.childactor;

import akka.actor.UntypedActor;

/**
 * ShootActor
 *
 * @author ms
 * @since 2016-05-10
 */
public class ShootActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        unhandled(message);
    }
}
