// AbstractActor

package de.htwg.battleship.actor;

import akka.actor.UntypedActor;
import akka.util.Timeout;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * AbstractActor
 *
 * @author ms
 * @since 2016-06-07
 */
public abstract class AbstractActor extends UntypedActor {
    final Logger LOGGER = Logger.getLogger(this.getClass());
    static final Timeout TIMEOUT = new Timeout(8, TimeUnit.SECONDS);

    protected final void logUnhandled(String actorName, Object message) {
        LOGGER.info(actorName + " received an unrecognized message of type: " +
                    message.getClass());
        unhandled(message);
    }
}
