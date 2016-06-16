package de.htwg.battleship.actor

import akka.actor.{Actor, Props}
import akka.pattern.ask
import akka.util.Timeout
import de.htwg.battleship.actor.messages.{PlayerDestroyedMessage, PlayerDestroyedResponse, ShipDestroyedMessage}
import de.htwg.battleship.model.{IPlayer, IShip}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * PlayerDestroyedActor
  *
  * @author ms
  * @since 2016-06-16
  */
object PlayerDestroyedActor {
    val ACTOR_NAME = "destroyer_player"
}

class PlayerDestroyedActor extends Actor {
    val shipper = context.actorOf(Props[ShipDestroyedActor], ShipDestroyedActor.ACTOR_NAME)
    implicit val timeout = Timeout(5 seconds)

    import context.dispatcher

    def reduce(one: Boolean, two: Boolean): Boolean = one && two

    def createFutures(shipList: Array[IShip], player: IPlayer): List[Future[Boolean]] = {
        var list = new ListBuffer[Future[Boolean]]()
        for (ship <- shipList) {
            list += (shipper ? ShipDestroyedMessage(ship, player)).mapTo[Boolean]
        }
        list.toList
    }

    def handleMessage(msg: PlayerDestroyedMessage) = {
        val futureList = createFutures(msg.player.getOwnBoard.getShipList, msg.player)
        val aggrFuture = Future sequence futureList
        aggrFuture onSuccess {
            case results: List[Boolean] =>
                val result = results.reduce(reduce)
                sender() ! PlayerDestroyedResponse(result, msg.first)
        }
    }

    override def receive: Receive = {
        case msg: PlayerDestroyedMessage => handleMessage(msg)
        case msg => unhandled(msg)
    }
}
