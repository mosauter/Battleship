// WinController.java

package de.htwg.battleship.actor.childactor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import de.htwg.battleship.actor.childactor.grandchild.PlayerDestroyedActor;
import de.htwg.battleship.actor.messages.PlayerDestroyedMessage;
import de.htwg.battleship.actor.messages.WinMessage;
import de.htwg.battleship.actor.messages.WinnerResponse;
import de.htwg.battleship.model.IPlayer;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.ArrayList;
import java.util.List;

/**
 * WinController checks if someone has won.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public class WinActor extends AbstractChildActor {

    public static final String ACTOR_NAME = "winner";
    private final ActorRef playerDestroyer = getContext()
        .actorOf(Props.create(PlayerDestroyedActor.class),
                 PlayerDestroyedActor.ACTOR_NAME);

    /**
     * Method to check if a player wins and who wins.
     *
     * @return the winner or null if there is no winner
     */
    private IPlayer win(final IPlayer player1, final IPlayer player2) {
        List<Future<Object>> futures = new ArrayList<>();
        futures.add(Patterns.ask(playerDestroyer,
                                 new PlayerDestroyedMessage(player1), TIMEOUT));
        futures.add(Patterns.ask(playerDestroyer,
                                 new PlayerDestroyedMessage(player2), TIMEOUT));

        for (int i = 0; i < futures.size(); i++) {
            Future<Object> future = futures.get(i);
            try {
                boolean destroyed =
                    (boolean) Await.result(future, TIMEOUT.duration());
                if (destroyed) {
                    return i == 0 ? player2 : player1;
                }
            } catch (Exception e) {
                // Timeout exception
            }
        }
        return null;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof WinMessage)) {
            logUnhandled(ACTOR_NAME, message);
            return;
        }

        WinMessage winMessage = (WinMessage) message;

        IPlayer winner = win(winMessage.getPlayer1(), winMessage.getPlayer2());

        getSender().tell(new WinnerResponse(winner != null, winner), getSelf());
    }
}
