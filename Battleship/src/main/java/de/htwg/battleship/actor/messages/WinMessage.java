// WinMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IPlayer;

/**
 * WinMessage
 *
 * @author ms
 * @since 2016-06-07
 */
public class WinMessage {
    private final IPlayer player1;
    private final IPlayer player2;

    public WinMessage(final IPlayer player1, final IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public IPlayer getPlayer1() {
        return player1;
    }

    public IPlayer getPlayer2() {
        return player2;
    }
}
