// AbstractGrandchildActor

package de.htwg.battleship.actor.childactor.grandchild;

import akka.util.Timeout;
import de.htwg.battleship.actor.childactor.AbstractChildActor;

import java.util.concurrent.TimeUnit;

/**
 * AbstractGrandchildActor
 *
 * @author ms
 * @since 2016-06-07
 */
public abstract class AbstractGrandchildActor extends AbstractChildActor {
    static final Timeout TIMEOUT = new Timeout(2, TimeUnit.SECONDS);
}
