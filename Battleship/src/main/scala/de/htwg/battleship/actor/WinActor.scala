package de.htwg.battleship.actor

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import de.htwg.battleship.actor.messages.{PlayerDestroyedMessage, PlayerDestroyedResponse, WinMessage, WinnerResponse}
import de.htwg.battleship.model.IPlayer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * WinActor
  *
  * @author ms
  * @since 2016-06-16
  */
object WinActor {
    val ACTOR_NAME = "winner"
}

class WinActor extends Actor {
    val playerer = context.actorOf(Props[PlayerDestroyedActor], PlayerDestroyedActor.ACTOR_NAME)
    implicit val timeout = Timeout(5 seconds)

    def handleMessage(player1: IPlayer, player2: IPlayer, ref: ActorRef) = {
        val pl1 = (playerer ? PlayerDestroyedMessage(player1, first = true)).mapTo[PlayerDestroyedResponse]
        val pl2 = (playerer ? PlayerDestroyedMessage(player2, first = false)).mapTo[PlayerDestroyedResponse]

        val entireFuture = pl1 flatMap { first =>
            pl2 map { second =>
                if (first.destroyed || second.destroyed) {
                    // someone has won
                    ref ! WinnerResponse(won = true, winner = if (first.destroyed) player2 else player1)
                } else {
                    ref ! WinnerResponse(won = false, winner = null)
                }
            }
        }
    }

    override def receive: Receive = {
        case msg: WinMessage =>
            handleMessage(msg.player1, msg.player2, sender())
        case msg => unhandled(msg)
    }
}
