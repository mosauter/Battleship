// MasterController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.StatCollection;

/**
 * MasterController is an implementation of the master controller.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-16
 */
public class MasterController extends Observable implements IMasterController {

    /**
     * Internal Ship controller.
     */
    private final ShipController shipController;
    /**
     * Internal shoot controller.
     */
    private final ShootController shootController;
    /**
     * Internal win controller.
     */
    private final WinController winController;
    /**
     * Saves the first Player.
     */
    private final IPlayer player1;
    /**
     * Saves the second Player.
     */
    private final IPlayer player2;
    /**
     * Presentation of the Game.
     */
    private Viewer view;

    /**
     * Public Constructor.
     * @param player1 player one
     * @param player2 player two
     */
    public MasterController(final IPlayer player1, final IPlayer player2) {
        this.shipController = new ShipController();
        this.shootController = new ShootController(player1, player2);
        this.winController = new WinController(player1, player2);
        this.player1 = player1;
        this.player2 = player2;
        this.view = null;
    }

    @Override
    public final void shoot(final int x, final int y, final IPlayer player) {
        boolean first;
        first = (player == this.player1);
        this.setViewer(new HitMissViewer(shootController.shoot(x, y, first)));
        this.win();
    }

    @Override
    public final void placeShip(final int x, final int y,
            final boolean orientation, final IPlayer player) {
        if (!shipController.placeShip(
                new Ship((player.getOwnBoard().getShips() + 2),
                orientation, x, y), player)) {
            this.setViewer(new PlaceErrorViewer());
            this.view = new PlaceViewer(player);
        }
        if (player.getOwnBoard().getShips() == StatCollection.SHIP_NUMBER_MAX) {
            this.view = new PlaceFieldViewer(player);
        }
        notifyObserver();
    }

    @Override
    public final void win() {
        IPlayer winner = winController.win();
        if (winner == null) {
            return;
        }
        this.setViewer(new WinPlayer(winner));
        System.exit(0);
    }

    @Override
    public final String getString() {
        return view.getString();
    }

    @Override
    public final void setViewer(final Viewer view) {
        this.view = view;
        notifyObserver();
    }

    @Override
    public final IPlayer getPlayer1() {
        return player1;
    }

    @Override
    public final IPlayer getPlayer2() {
        return player2;
    }
}
