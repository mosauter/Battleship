// ShootMenu.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.SHOOT1;
import static de.htwg.battleship.util.State.SHOOT2;

/**
 * ShootMenu presents a interface where the player wants to shoot.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class ShootMenu implements Viewer {

    /**
     * Saves the Player.
     */
    private final IPlayer player;
    /**
     * Viewer for a presentation of the Field.
     */
    private final ShootFieldViewer shootViewer;
    /**
     * True if the shooter is the first player,
     * false if the shooter is the second player.
     */
    private final boolean firstPlayer;

    /**
     * Public Constructor.
     * @param player player
     * @param master master controller for the FieldViewer
     */
    public ShootMenu(final IPlayer player, final IMasterController master) {
        this.player = player;
        this.shootViewer = new ShootFieldViewer(player, master);
        this.firstPlayer = player.equals(master.getPlayer1());
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(shootViewer.getString());
        sb.append(player.getName());
        sb.append(", where to shoot? (Format: [a - j] [0 - 9])\n");
        sb.append("\t-->\t");
        return sb.toString();
    }

    @Override
    public State getCurrentState() {
        if (firstPlayer) {
            return SHOOT1;
        }
        return SHOOT2;
    }
}
