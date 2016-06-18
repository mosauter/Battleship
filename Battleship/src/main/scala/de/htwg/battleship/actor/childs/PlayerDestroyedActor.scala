package de.htwg.battleship.actor.childs

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import de.htwg.battleship.actor.childs.grandchilds.ShipDestroyedActor
import de.htwg.battleship.actor.messages.{PlayerDestroyedMessage, PlayerDestroyedResponse, ShipDestroyedMessage}
import de.htwg.battleship.model.{IPlayer, IShip}
import de.htwg.battleship.util.Reducers

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
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

    def createFutures(shipList: Array[IShip], player: IPlayer): List[Future[Boolean]] = {
        var list = new ListBuffer[Future[Boolean]]()
        for (counter <- 0 until player.getOwnBoard.getShips) {
            list += (shipper ? ShipDestroyedMessage(shipList(counter), player)).mapTo[Boolean]
        }
        list.toList
    }

    def handleMessage(msg: PlayerDestroyedMessage, ref: ActorRef) = {
        val futureList = createFutures(msg.player.getOwnBoard.getShipList, msg.player)
        val aggrFuture = Future sequence futureList
        aggrFuture onSuccess {
            case results: List[Boolean] =>
                val result = results.reduce(Reducers.reduceBooleanAND)
                ref ! PlayerDestroyedResponse(result, msg.first)
        }
    }

    override def receive: Receive = {
        case msg: PlayerDestroyedMessage =>
            handleMessage(msg, sender())
        case msg => unhandled(msg)
    }
}
