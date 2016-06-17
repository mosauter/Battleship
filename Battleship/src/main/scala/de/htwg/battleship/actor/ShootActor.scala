package de.htwg.battleship.actor

import akka.actor.Actor
import de.htwg.battleship.actor.messages.ShootMessage
import de.htwg.battleship.model.{IBoard, IPlayer, IShip}
import de.htwg.battleship.util.StatCollection

import scala.util.control.Breaks._

/**
  * ShootActor
  *
  * @author ms
  * @since 2016-06-16
  */
object ShootActor {
    val ACTOR_NAME = "shooter"
}

class ShootActor extends Actor {
    def getBoard(first: Boolean, player1: IPlayer, player2: IPlayer): IBoard = {
        if (first) {
            player2.getOwnBoard
        } else {
            player1.getOwnBoard
        }
    }

    def isHit(upper: Int, lower: Int, targetVar: Int, targetFix: Int, fix: Int): Boolean = {
        targetFix == fix && StatCollection.isBetween(upper, lower, targetVar)
    }

    def hit(ship: IShip, x: Int, y: Int): Boolean = {
        val shipX = ship.getX
        val shipY = ship.getY
        val size = ship.getSize
        if (ship.isOrientation) {

            val xupp = shipX + size - 1
            isHit(xupp, shipX, x, y, shipY)
        } else {
            val yupp = shipY + size - 1
            isHit(yupp, shipY, y, x, shipX)
        }
    }

    def hitShipList(shipList: Array[IShip], x: Int, y: Int, counter: Int): Boolean = {
        var result = false
        breakable {
            for (i <- 0 until counter) {
                if (hit(shipList(i), x, y)) {
                    result = true
                    break
                }
            }
        }
        result
    }

    def shoot(x: Int, y: Int, board: IBoard): Boolean = {
        if (board.isHit(x, y)) {
            false
        } else {
            val shipList = board.getShipList
            hitShipList(shipList, x, y, board.getShips)
        }
    }

    override def receive: Receive = {
        case msg: ShootMessage =>
            sender() ! shoot(msg.x, msg.y, getBoard(msg.first, msg.player1, msg.player2))
        case msg => unhandled(msg)
    }
}
