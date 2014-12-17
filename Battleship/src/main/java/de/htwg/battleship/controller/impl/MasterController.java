// MasterController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.StatCollection;

/**
 * MasterController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-16
 */
public class MasterController extends Observable implements IMasterController {

    private final ShipController shipController;
    private final ShootController shootController;
    private final WinController winController;
    private final IPlayer player1;
    private final IPlayer player2;
    private Viewer view;


    public MasterController(IPlayer player1, IPlayer player2) {
        this.shipController = new ShipController();
        this.shootController = new ShootController(player1, player2);
        this.winController = new WinController(player1, player2);
        this.player1 = player1;
        this.player2 = player2;
        this.view = null;
    }

    @Override
    public void shoot(final int x, final int y, final IPlayer player) {
        boolean first;
        if (player == this.player1) {
            first = true;
        } else {
            first = false;
        }
        this.setViewer(new HitMissViewer(shootController.shoot(x, y, first)));
        this.win();
    }

    @Override
    public void placeShip(int x, int y, boolean orientation, IPlayer player) {
        shipController.placeShip(new Ship((player.getOwnBoard().getShips() + 2),
                orientation, x, y), player);
        if (player.getOwnBoard().getShips() == StatCollection.SHIP_NUMBER_MAX) {
            this.view = new PlaceFieldViewer(player);
        }
        notifyObserver();
    }

    @Override
    public void win() {
        IPlayer winner = winController.win();
        if (winner == null) {
            return;
        }
        this.setViewer(new WinPlayer(winner));
        System.exit(0);
    }

    @Override
    public String getString() {
        return view.getString();
    }

    @Override
    public void setViewer(final Viewer view) {
        this.view = view;
        notifyObserver();
    }
    
    public IPlayer getPlayer1() {
        return player1;
    }

    public IPlayer getPlayer2() {
        return player2;
    }
    
}
