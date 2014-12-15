// ShootMenu.java

package de.htwg.battleship.aview.impl;

import de.htwg.battleship.aview.Viewer;
import de.htwg.battleship.model.IPlayer;

/**
 * ShootMenu
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class ShootMenu implements Viewer {

    private IPlayer player;
    public ShootMenu(IPlayer player) {
        this.player = player;
    }

    @Override
    public String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName());
        sb.append(", where to shoot? (Format: [a - z] [0 - 9])\n");
        sb.append("\t-->\t");
        return sb.toString();
    }

}
