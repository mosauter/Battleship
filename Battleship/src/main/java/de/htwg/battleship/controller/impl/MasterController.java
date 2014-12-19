// MasterController.java

package de.htwg.battleship.controller.impl;

import com.google.inject.Inject;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.FINALPLACE1;
import static de.htwg.battleship.util.State.FINALPLACE2;
import static de.htwg.battleship.util.State.GETNAME1;
import static de.htwg.battleship.util.State.GETNAME2;
import static de.htwg.battleship.util.State.HIT;
import static de.htwg.battleship.util.State.MISS;
import static de.htwg.battleship.util.State.PLACE1;
import static de.htwg.battleship.util.State.PLACE2;
import static de.htwg.battleship.util.State.PLACEERR;
import static de.htwg.battleship.util.State.SHOOT1;
import static de.htwg.battleship.util.State.SHOOT2;
import static de.htwg.battleship.util.State.START;
import static de.htwg.battleship.util.State.WIN1;
import static de.htwg.battleship.util.State.WIN2;
import static de.htwg.battleship.util.State.WRONGINPUT;

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
    private State state;

    /**
     * Public Constructor.
     * @param player1 player one
     * @param player2 player two
     */
    @Inject
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
        boolean first;
        if (this.state == SHOOT1) {
            player = this.player1;
            first = true;
        } else if (this.state == SHOOT2) {
            player = this.player2;
            first = false;
        } else {
            this.setState(WRONGINPUT);
            return;
        }
        if (shootController.shoot(x, y, first)) {
            this.setState(HIT);
        } else {
            this.setState(MISS);
        }
        this.win();
        if (first) {
            this.setState(SHOOT2);
        } else {
            this.setState(SHOOT1);
        }
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
            this.setState(WRONGINPUT);
            return;
        }

        if (!shipController.placeShip(
                new Ship((player.getOwnBoard().getShips() + 2),
                orientation, x, y), player)) {
            this.setState(PLACEERR);
            return;
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
    public final State getCurrentState() {
        return this.state;
    }

    @Override
    public final void setState(final State state) {
        State tmp = state;
        if (state == WRONGINPUT || state == PLACEERR) {
            tmp = this.state;
            this.state = state;
            notifyObserver();
        }
        this.state = tmp;
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
            this.setState(GETNAME2);
        } else if (this.state == GETNAME2) {
            player2.setName(name);
            this.setState(PLACE1);
        } else {
            this.setState(WRONGINPUT);
        }
    }
}
