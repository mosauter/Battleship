package de.htwg.battleship.actor

import akka.actor.Actor
import de.htwg.battleship.actor.messages.SaveMessage

/**
  * SaveActor
  *
  * @author ms
  * @since 2016-06-19
  */
object SaveActor {
    val ACTOR_NAME = "saver"
}

class SaveActor extends Actor {
    override def receive: Receive = {
        case msg: SaveMessage =>
            // do nothin for now
        case msg => unhandled(msg)
    }
}
