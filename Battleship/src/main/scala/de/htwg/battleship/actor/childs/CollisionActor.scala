package de.htwg.battleship.actor.childs

import akka.actor.{Actor, Props}
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
    val same = context.actorOf(Props[SameCollision], SameCollision.ACTOR_NAME)
    val diff = context.actorOf(Props[DifferentCollision], DifferentCollision.ACTOR_NAME)
    implicit val timeout = Timeout(5 seconds)

    /**
      * Method to check if there is a collision.
      *
      * @return Sends 'true' for it is all okay or 'false' if there is a problem to the asker
      */
    override def receive: Receive = {
        case msg: CollisionMessage =>
            if (msg.ship1.isOrientation == msg.ship2.isOrientation) {
                same forward msg
            } else {
                diff forward msg
            }
        case msg => unhandled(msg)
    }
}

object SameCollision {
    val ACTOR_NAME = "collider_same"
}

class SameCollision extends Actor {
    override def receive: Receive = {
        case msg: CollisionMessage =>
            // negate result as we need false as stronger boolean in the composition
            val result = !isCollision(msg.ship1, msg.ship2)
            sender() ! result
    }

    /**
      * Help method to check if two ships collide when both ships have the same orientation
      *
      * @param firstShip  first ship
      * @param secondShip second ship
      * @return true if there was a collision, false if everything was okay
      */
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
                // loop INCLUDING secLow AND secUp -> for (i = secLow; i <= secUp; i++)
                for (i <- secLow to secUp) {
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
    override def receive: Receive = {
        case msg: CollisionMessage =>
            var horizontal: IShip = msg.ship1
            var vertical: IShip = msg.ship2
            if (!msg.ship1.isOrientation) {
                horizontal = msg.ship2
                vertical = msg.ship1
            }
            // negate result as we need false forces the entire result to be false
            val result = !checkShipsDifferent(vertical, horizontal)
            sender() ! result
    }

    /**
      * Help method to check if two ships collide if they have different orientations.
      *
      * @param vertical   the ship with orientation = false
      * @param horizontal the ship with the orientation = true
      * @return 'true' if there is a collision or false if everything is okay
      */
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

