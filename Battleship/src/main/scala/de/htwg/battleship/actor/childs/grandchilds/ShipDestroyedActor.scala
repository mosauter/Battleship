package de.htwg.battleship.actor.childs.grandchilds

import akka.actor.Actor
import de.htwg.battleship.actor.messages.ShipDestroyedMessage
import de.htwg.battleship.model.{IPlayer, IShip}

import scala.util.control.Breaks._

/**
  * ShipDestroyedActor
  *
  * @author ms
  * @since 2016-06-16
  */
object ShipDestroyedActor {
    val ACTOR_NAME = "destroyer_ship"
}

class ShipDestroyedActor extends Actor {

    def isDestroyed(ship: IShip, player: IPlayer): Boolean = {
        val low = if (ship.isOrientation) ship.getX else ship.getY
        val fix = if (ship.isOrientation) ship.getY else ship.getX
        val up = low + ship.getSize
        val board = player.getOwnBoard
        var result = true
        breakable {
            for (i <- low until up) {
                if (ship.isOrientation) {
                    if (!board.isHit(i, fix)) {
                        result = false
                        break
                    }
                } else {
                    if (!board.isHit(fix, i)) {
                        result = false
                        break
                    }
                }
            }
        }
        result
    }

    override def receive: Receive = {
        case msg: ShipDestroyedMessage => sender() ! isDestroyed(msg.ship, msg.player)
        case msg => unhandled(msg)
    }
}
