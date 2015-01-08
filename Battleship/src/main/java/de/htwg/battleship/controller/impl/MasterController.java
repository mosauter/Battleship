// MasterController.java

package de.htwg.battleship.controller.impl;

import com.google.inject.Inject;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.END;
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
    private State currentState;

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
        this.currentState = START;
    }

    @Override
    public final void shoot(final int x, final int y) {
        boolean first;
        State before = currentState;
        if (this.currentState == SHOOT1) {
            first = true;
        } else if (this.currentState == SHOOT2) {
            first = false;
        } else {
            this.setCurrentState(WRONGINPUT);
            return;
        }
        hitMiss(shootController.shoot(x, y, first));
        if (!this.win()) {
            this.nextShootState(before);
        }
    }

    /**
     * Utility-Method to wrap the Hit/Miss differentiation.
     * @param shootResult true if there was a hit false if not
     */
    private void hitMiss(final boolean shootResult) {
        if (shootResult) {
            this.setCurrentState(HIT);
        } else {
            this.setCurrentState(MISS);
        }
    }

    /**
     * Utility-Method to set the next state for shooting.
     * @param before state before the shoot method changes it to hit or miss
     */
    private void nextShootState(final State before) {
        if (before.equals(SHOOT1)) {
            this.setCurrentState(SHOOT2);
        } else {
            this.setCurrentState(SHOOT1);
        }
    }

    @Override
    public final void placeShip(final int x, final int y,
            final boolean orientation) {
        IPlayer player;
        boolean firstPlayer;

        if (this.currentState == PLACE1) {
            player = player1;
            firstPlayer = true;
        } else if (this.currentState == PLACE2) {
            player = player2;
            firstPlayer = false;
        } else {
            this.setCurrentState(WRONGINPUT);
            return;
        }

        if (!shipController.placeShip(
                new Ship((player.getOwnBoard().getShips() + 2),
                orientation, x, y), player)) {
            this.setCurrentState(PLACEERR);
            return;
        }

        if (player.getOwnBoard().getShips() == StatCollection.shipNumberMax) {
            if (firstPlayer) {
                this.setCurrentState(FINALPLACE1);
                this.currentState = PLACE2;
            } else {
                this.setCurrentState(FINALPLACE2);
                this.currentState = SHOOT1;
            }
        }
        notifyObserver();
    }

    /**
     * Checs if someone has won.
     * @return true if someone has won false if not, sets the win-states
     *         and after that the end-state
     * returns true not until the win- and the end-states are setted
     */
    public final boolean win() {
        IPlayer winner = winController.win();
        if (winner == null) {
            return false;
        }
        winner(winner.equals(this.player1));
        this.setCurrentState(END);
        return true;
    }

    /**
     * Utility-Method to wrap the win-state differentiation.
     * @param first true if the winner is the player1 false if not
     */
    private void winner(final boolean first) {
        if (first) {
            this.setCurrentState(WIN1);
        } else {
            this.setCurrentState(WIN2);
        }
    }

    /**
     * Private utility-method to reset the boards of both players.
     */
    public void resetBoards() {
        player1.resetBoard();
        player2.resetBoard();
    }

    @Override
    public final State getCurrentState() {
        return this.currentState;
    }

    @Override
    public final void setCurrentState(final State currentState) {
        State tmp = currentState;
        if (currentState == WRONGINPUT || currentState == PLACEERR) {
            tmp = this.currentState;
            this.currentState = currentState;
            notifyObserver();
        }
        this.currentState = tmp;
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
        if (this.currentState == GETNAME1) {
            player1.setName(name);
            this.setCurrentState(GETNAME2);
        } else if (this.currentState == GETNAME2) {
            player2.setName(name);
            this.setCurrentState(PLACE1);
        } else {
            this.setCurrentState(WRONGINPUT);
        }
    }

    @Override
    public void startGame() {
        if (this.currentState == START) {
            this.setCurrentState(GETNAME1);
        } else if (this.currentState == END) {
            this.resetBoards();
            this.setCurrentState(PLACE1);
        }
    }
}
