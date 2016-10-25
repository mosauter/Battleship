package de.htwg.battleship.actor

import akka.actor.Actor
import de.htwg.battleship.Battleship
import de.htwg.battleship.actor.messages.SaveMessage
import de.htwg.battleship.dao.IDAO

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
    val iDAO = Battleship.getInstance().getInjector.getInstance(classOf[IDAO])
    override def receive: Receive = {
        case msg: SaveMessage =>
            iDAO.saveOrUpdateGame(msg.master)
            // do nothin for now
        case msg => unhandled(msg)
    }
}
