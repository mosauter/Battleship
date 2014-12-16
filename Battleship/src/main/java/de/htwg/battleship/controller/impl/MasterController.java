// MasterController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.impl.Observable;

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
    private Viewer view;


    public MasterController(IPlayer player1, IPlayer player2) {
        this.shipController = new ShipController(player1, player2);
        this.shootController = new ShootController(player1, player2);
        this.winController = new WinController(player1, player2);
        this.view = new StartMenu();
    }

    @Override
    public void shoot(final int x, final int y, final boolean player) {
        shootController.shoot(x, y, player);
        notifyObserver();
    }

    @Override
    public void placeShip(final IShip ship, final boolean player) {
        shipController.placeShip(ship, player);
        notifyObserver();
    }

    @Override
    public void win() {
        winController.win();
        notifyObserver();
    }

    @Override
    public String getString() {
        return view.getString();
    }
}
