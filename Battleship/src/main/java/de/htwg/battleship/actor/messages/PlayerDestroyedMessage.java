// PLayerDestroyedMessage

package de.htwg.battleship.actor.messages;

import de.htwg.battleship.model.IPlayer;

/**
 * PlayerDestroyedMessage
 *
 * @author ms
 * @since 2016-06-07
 */
public class PlayerDestroyedMessage {
    private final IPlayer player;

    public PlayerDestroyedMessage(IPlayer player) {
        this.player = player;
    }

    public IPlayer getPlayer() {
        return player;
    }
}
