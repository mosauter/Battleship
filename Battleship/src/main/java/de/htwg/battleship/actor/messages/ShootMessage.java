// ShootMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IPlayer;

/**
 * ShootMessage
 *
 * @author ms
 * @since 2016-05-10
 */
public class ShootMessage {

    private final IPlayer player1;
    private final IPlayer player2;
    private final int x;
    private final int y;
    private final boolean first;

    public ShootMessage(IPlayer player1, IPlayer player2, int x, int y,
                        boolean first) {

        this.player1 = player1;
        this.player2 = player2;
        this.x = x;
        this.y = y;
        this.first = first;
    }

    public boolean isFirst() {
        return first;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public IPlayer getPlayer2() {
        return player2;
    }

    public IPlayer getPlayer1() {
        return player1;
    }
}
