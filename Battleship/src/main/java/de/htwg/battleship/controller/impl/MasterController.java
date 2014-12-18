// MasterController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.States;
import static de.htwg.battleship.util.States.FINALPLACE1;
import static de.htwg.battleship.util.States.FINALPLACE2;
import static de.htwg.battleship.util.States.GETNAME1;
import static de.htwg.battleship.util.States.GETNAME2;
import static de.htwg.battleship.util.States.HIT;
import static de.htwg.battleship.util.States.MISS;
import static de.htwg.battleship.util.States.PLACE1;
import static de.htwg.battleship.util.States.PLACE2;
import static de.htwg.battleship.util.States.PLACEERR;
import static de.htwg.battleship.util.States.SHOOT1;
import static de.htwg.battleship.util.States.SHOOT2;
import static de.htwg.battleship.util.States.START;
import static de.htwg.battleship.util.States.WIN1;
import static de.htwg.battleship.util.States.WIN2;
import static de.htwg.battleship.util.States.WRONGINPUT;

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
    private States state;

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
        this.state = START;
    }

    @Override
    public final void shoot(final int x, final int y) {
        IPlayer player;
        States tmp = this.state;
        boolean first;
        if (this.state == SHOOT1) {
            player = this.player1;
            first = true;
        } else if (this.state == SHOOT2) {
            player = this.player2;
            first = false;
        } else {
            this.setState(WRONGINPUT);
            this.setState(tmp);
            return;
        }
        if (shootController.shoot(x, y, first)) {
            this.setState(HIT);
        } else {
            this.setState(MISS);
        }
        if (first) {
            this.setState(SHOOT2);
        } else {
            this.setState(SHOOT1);
        }
        this.win();
    }

    @Override
    public final void placeShip(final int x, final int y,
            final boolean orientation) {
        IPlayer player;
        boolean firstPlayer;

        if (this.state == PLACE1) {
            player = player1;
            firstPlayer = true;
        } else if (this.state == PLACE2) {
            player = player2;
            firstPlayer = false;
        } else {
            States tmp = this.state;
            this.setState(WRONGINPUT);
            this.setState(tmp);
            return;
        }

        if (!shipController.placeShip(
                new Ship((player.getOwnBoard().getShips() + 2),
                orientation, x, y), player)) {
            this.setState(PLACEERR);
            if (firstPlayer) {
                this.state = PLACE1;
            } else {
                this.state = PLACE2;
            }
        }

        if (player.getOwnBoard().getShips() == StatCollection.SHIP_NUMBER_MAX) {
            if (firstPlayer) {
                this.setState(FINALPLACE1);
                this.state = PLACE2;
            } else {
                this.setState(FINALPLACE2);
                this.state = SHOOT1;
            }
        }
        notifyObserver();
    }

    @Override
    public final void win() {
        IPlayer winner = winController.win();
        if (winner == null) {
            return;
        }
        if (winner.equals(player1)) {
            this.setState(WIN1);
        } else {
            this.setState(WIN2);
        }
        System.exit(0);
    }

    @Override
    public final States getCurrentState() {
        return this.state;
    }

    @Override
    public final void setState(final States state) {
        this.state = state;
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

    @Override
    public final void setPlayerName(final String name) {
        if (this.state == GETNAME1) {
            player1.setName(name);
            this.setState(GETNAME1);
        } else if (this.state == GETNAME2) {
            player2.setName(name);
            this.setState(PLACE1);
        } else {
            States tmp = this.state;
            this.setState(WRONGINPUT);
            this.setState(tmp);
        }
    }
}
