package de.htwg.battleship.actor

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.util.Timeout
import de.htwg.battleship.actor.messages.CollisionMessage
import de.htwg.battleship.model.IShip
import de.htwg.battleship.util.StatCollection

import scala.concurrent.duration._
import scala.util.control.Breaks._


object CollisionActor {
    val ACTOR_NAME = "collider"
}

class CollisionActor extends Actor {
    val log = Logging(context.system, this)
    val same = context.actorOf(Props[SameCollision], SameCollision.ACTOR_NAME)
    val diff = context.actorOf(Props[DifferentCollision], DifferentCollision.ACTOR_NAME)
    implicit val timeout = Timeout(5 seconds)

    override def receive: Receive = {
        case msg: CollisionMessage =>
            log.info(CollisionActor.ACTOR_NAME + " received a message")
            if (msg.ship1.isOrientation == msg.ship2.isOrientation) {
                log.info(CollisionActor.ACTOR_NAME + " forwarded to same")
                same forward msg
            } else {
                log.info(CollisionActor.ACTOR_NAME + " forwarded to diff")
                diff forward msg
            }
        case msg => unhandled(msg)
    }
}

object SameCollision {
    val ACTOR_NAME = "collider_same"
}

class SameCollision extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
        case msg: CollisionMessage =>
            log.info(SameCollision.ACTOR_NAME + " received a message")
            val result = isCollision(msg.ship1, msg.ship2)
            log.info(SameCollision.ACTOR_NAME + " results " + result.toString)
            sender() ! result
    }

    def isCollision(firstShip: IShip, secondShip: IShip): Boolean = {
        var firstLow: Int = -1
        var firstFix: Int = -1
        var secLow: Int = -1
        var secFix: Int = -1
        if (firstShip.isOrientation) {
            firstLow = firstShip.getX
            firstFix = firstShip.getY
            secLow = secondShip.getX
            secFix = secondShip.getY
        } else {
            firstLow = firstShip.getY
            firstFix = firstShip.getX
            secLow = secondShip.getY
            secFix = secondShip.getX
        }
        val firstUp = firstLow + firstShip.getSize - 1
        val secUp = secLow + secondShip.getSize - 1
        if (secFix != firstFix) {
            false
        } else {
            var returner = false
            breakable {
                for (i <- secLow until secUp) {
                    if (StatCollection.isBetween(firstUp, firstLow, i)) {
                        returner = true
                        break
                    }
                }
            }
            returner
        }
    }
}

object DifferentCollision {
    val ACTOR_NAME = "collider_different"
}

class DifferentCollision extends Actor {
    var log = Logging(context.system, this)

    override def receive: Receive = {
        case msg: CollisionMessage =>
            var horizontal: IShip = msg.ship1
            var vertical: IShip = msg.ship2
            if (!msg.ship1.isOrientation) {
                horizontal = msg.ship2
                vertical = msg.ship1
            }
            log.info(DifferentCollision.ACTOR_NAME + " received a message")
            val result = checkShipsDifferent(vertical, horizontal)
            log.info(DifferentCollision.ACTOR_NAME + " results " + result.toString)
            sender() ! result
    }

    def checkShipsDifferent(vertical: IShip, horizontal: IShip): Boolean = {
        val horizontalLow = horizontal.getX
        val horizontalUp = horizontalLow + horizontal.getSize - 1
        val horizontalFix = horizontal.getY
        val verticalLow = vertical.getY
        val verticalUp = verticalLow + vertical.getSize - 1
        val verticalFix = vertical.getX
        StatCollection.isBetween(verticalUp, verticalLow, horizontalFix) &&
            StatCollection.isBetween(horizontalUp, horizontalLow, verticalFix)
    }
}

