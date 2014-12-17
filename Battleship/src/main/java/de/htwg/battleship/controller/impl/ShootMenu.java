// ShootMenu.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;

/**
 * ShootMenu
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class ShootMenu implements Viewer {

    private IPlayer player;
    private Viewer shootViewer;
    public ShootMenu(IPlayer player, IMasterController master) {
        this.player = player;
        this.shootViewer = new ShootFieldViewer(player, master);
    }

    @Override
    public String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(shootViewer.getString());
        sb.append(player.getName());
        sb.append(", where to shoot? (Format: [a - j] [0 - 9])\n");
        sb.append("\t-->\t");
        return sb.toString();
    }

}
