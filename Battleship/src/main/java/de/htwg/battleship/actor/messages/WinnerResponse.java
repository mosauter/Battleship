// WinnerResponse

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IPlayer;

/**
 * WinnerResponse
 *
 * @author ms
 * @since 2016-06-07
 */
public class WinnerResponse {
    private final boolean won;
    private final IPlayer winner;

    public WinnerResponse(final boolean won, final IPlayer winner) {
        this.won = won;
        this.winner = winner;
    }

    public IPlayer getWinner() {
        return winner;
    }

    public boolean hasWon() {
        return won;
    }
}
