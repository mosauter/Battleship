// MasterController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObserver;

/**
 * MasterController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-16
 */
public class MasterController implements IMasterController {

    ShipController shipController;
    ShootController shootController;
    WinController winController;


    public MasterController(IPlayer player1, IPlayer player2) {
        this.shipController = new ShipController(player1, player2);
        this.shootController = new ShootController(player1, player2);
        this.winController = new WinController(player1, player2);
    }

    public boolean shoot(int x, int y, boolean player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean placeShip(IShip ship, boolean player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IPlayer win() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyObserver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
