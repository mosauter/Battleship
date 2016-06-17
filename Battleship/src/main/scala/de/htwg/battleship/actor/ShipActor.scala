package de.htwg.battleship.actor

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import de.htwg.battleship.actor.messages.{BorderMessage, CollisionMessage, ShipMessage}
import de.htwg.battleship.model.{IPlayer, IShip}

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object ShipActor {
    val ACTOR_NAME = "shipper"
}

class ShipActor extends Actor {
    val borderer = context.actorOf(Props[BorderActor], BorderActor.ACTOR_NAME)
    val collider = context.actorOf(Props[CollisionActor], CollisionActor.ACTOR_NAME)
    implicit val timeout = Timeout(5 seconds)

    def createColliderFutures(ship: IShip, player: IPlayer): List[Future[Boolean]] = {
        var list = new ListBuffer[Future[Boolean]]()
        val shipList = player.getOwnBoard.getShipList
        for (i <- 0 until player.getOwnBoard.getShips) {
            list += (collider ? CollisionMessage(ship, shipList(i))).mapTo[Boolean]
        }
        list.toList
    }

    // TODO: think about an abstract
    def reduce(one: Boolean, two: Boolean): Boolean = one && two

    def placeShip(ship: IShip, player: IPlayer, boardSize: Int, ref: ActorRef) = {
        val borderFuture = (borderer ? BorderMessage(ship, boardSize)).mapTo[Boolean]
        val collisionFutures = createColliderFutures(ship, player)
        val allFutures = List(collisionFutures, List(borderFuture)).flatten
        val aggrFuture = Future sequence allFutures
        aggrFuture onSuccess {
            case results: List[Boolean] =>
                ref ! results.reduce(reduce)
        }
    }

    /**
      * Method to check if a ship could validly placed on the board of a player
      *
      * @return
      */
    override def receive: Receive = {
        case msg: ShipMessage =>
            placeShip(msg.ship, msg.player, msg.boardSize, sender())
        case msg => unhandled(msg)
    }
}
