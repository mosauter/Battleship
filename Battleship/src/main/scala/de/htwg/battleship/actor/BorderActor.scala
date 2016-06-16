package de.htwg.battleship.actor

import akka.actor.Actor
import de.htwg.battleship.actor.messages.BorderMessage
import de.htwg.battleship.model.IShip
import de.htwg.battleship.util.StatCollection.isBetween

/**
  * BorderActor
  *
  * @author ms
  * @since 2016-06-16
  */

object BorderActor {
    val ACTOR_NAME = "borderer"
}

class BorderActor extends Actor {

    def isOnField(ship: IShip, boardSize: Int): Boolean = {
        val realBoardSize = boardSize - 1
        val low = if (ship.isOrientation) ship.getX else ship.getY
        val fix = if (ship.isOrientation) ship.getY else ship.getX
        val up = low + ship.getSize
        isBetween(realBoardSize, 0, low) &&
            isBetween(realBoardSize, 0, up) &&
            isBetween(realBoardSize, 0, fix)
    }

    /**
      * Method to receive messages.
      *
      * @return sends true to the asker if the ship is NOT on the field, sends false if the ship is placed on the board in a valid way
      */
    override def receive: Receive = {
        case msg: BorderMessage => sender() ! !isOnField(msg.ship, msg.boardSize)
        case msg => unhandled(msg)
    }
}
