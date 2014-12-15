// WinPlayer1.java

package de.htwg.battleship.aview.impl;

import de.htwg.battleship.aview.Viewer;
import de.htwg.battleship.model.IPlayer;

/**
 * WinPlayer1
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public class WinPlayer implements Viewer {

    private final IPlayer player;
    
    public WinPlayer(IPlayer player) {
        this.player = player;
    }
    public String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName());
        sb.append(" has won!");
        return sb.toString();
    }
}
