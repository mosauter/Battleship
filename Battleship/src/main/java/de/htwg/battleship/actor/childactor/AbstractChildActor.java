// AbstractChildActor

package de.htwg.battleship.actor.childactor;

import akka.util.Timeout;
import de.htwg.battleship.actor.AbstractActor;

import java.util.concurrent.TimeUnit;

/**
 * AbstractChildActor
 *
 * @author ms
 * @since 2016-06-07
 */
public abstract class AbstractChildActor extends AbstractActor {
    static final Timeout TIMEOUT = new Timeout(5, TimeUnit.SECONDS);
}
