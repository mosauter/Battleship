package de.htwg.battleship.actor

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
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
    val log = Logging(context.system, this)
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

    def reduce(one: Boolean, two: Boolean): Boolean = one && two

    def placeShip(ship: IShip, player: IPlayer, boardSize: Int, ref: ActorRef) = {
        log.info(ShipActor.ACTOR_NAME + " start placeShip")
        val borderFuture = (borderer ? BorderMessage(ship, boardSize)).mapTo[Boolean]
        log.info(ShipActor.ACTOR_NAME + " send ask to borderer")
        val collisionFutures = createColliderFutures(ship, player)
        log.info(ShipActor.ACTOR_NAME + " send asks to colliders")
        val allFutures = List(collisionFutures, List(borderFuture)).flatten
        log.info(ShipActor.ACTOR_NAME + " flatten all futures " + allFutures.toString())
        val aggrFuture = Future sequence allFutures
        log.info(ShipActor.ACTOR_NAME + " aggregated the futures")
        aggrFuture onSuccess {
            case results: List[Boolean] =>
                log.info(ShipActor.ACTOR_NAME + " in onSuccess " + results.toString())
                val result = results.reduce(reduce)
                log.info(ShipActor.ACTOR_NAME + " in onSuccess result " + result.toString)
                log.info(ShipActor.ACTOR_NAME + " in onSuccess sender ref " + ref.toString())
                ref ! result
        }
    }

    override def receive: Receive = {
        case msg: ShipMessage => {
            log.info(ShipActor.ACTOR_NAME + " received a message")
            placeShip(msg.ship, msg.player, msg.boardSize, sender())
        }
        case msg => unhandled(msg)
    }
}
