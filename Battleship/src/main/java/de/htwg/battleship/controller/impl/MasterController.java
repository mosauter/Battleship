// MasterController.java

package de.htwg.battleship.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;

import java.util.Map;
import java.util.Set;

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
 *
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
    private IPlayer player1;
    /**
     * Saves the second Player.
     */
    private IPlayer player2;
    /**
     * Presentation of the Game.
     */
    private State currentState;
    /**
     * Saves the GameMode of the Game.
     */
    private GameMode gm;
    /**
     * Saves the injector which has to be set by the main method.
     */
    private Injector injector;

    /**
     * Public Constructor.
     *
     * @param player1 player one
     * @param player2 player two
     */
    @Inject
    public MasterController(final IPlayer player1, final IPlayer player2,
                            Injector in) {
        this.shipController = new ShipController();
        this.shootController = new ShootController(player1, player2);
        this.winController = new WinController(player1, player2);
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = START;
        this.gm = GameMode.NORMAL;
        this.injector = in;
    }

    @Override
    public final void shoot(final int x, final int y) {
        boolean first;
        State before = this.currentState;
        if (this.currentState == SHOOT1) {
            first = true;
        } else if (this.currentState == SHOOT2) {
            first = false;
        } else {
            this.setCurrentState(WRONGINPUT);
            return;
        }
        boolean shootResult = shootController.shoot(x, y, first);
        if (!this.win()) {
            this.hitMiss(shootResult);
            this.nextShootState(before, shootResult);
        }
    }

    /**
     * Utility-Method to wrap the Hit/Miss differentiation.
     *
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
     *
     * @param before state before the shoot method changes it to hit or miss
     */
    private void nextShootState(final State before, final boolean shootResult) {
        if (before.equals(SHOOT1)) {
            if (this.gm.equals(GameMode.NORMAL) && shootResult) {
                this.setCurrentState(SHOOT1);
            } else {
                this.setCurrentState(SHOOT2);
            }
        } else {
            if (this.gm.equals(GameMode.NORMAL) && shootResult) {
                this.setCurrentState(SHOOT2);
            } else {
                this.setCurrentState(SHOOT1);
            }
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
            createShip(x, y, orientation, player.getOwnBoard().getShips() + 2),
            player)) {
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
     * Utility Method to create a ship with dependency injection and additional
     * setters.
     *
     * @param x           x-coordinate where the ship starts
     * @param y           y-coordinate where the ship starts
     * @param orientation orientation of the ship true if horizontal, false if
     *                    vertical
     * @param size        size of the ship
     *
     * @return the created ship
     */
    private IShip createShip(final int x, final int y,
                             final boolean orientation, final int size) {
        IShip ship = injector.getInstance(IShip.class);
        ship.setX(x);
        ship.setY(y);
        ship.setOrientation(orientation);
        ship.setSize(size);
        return ship;
    }

    /**
     * Checks if someone has won.
     *
     * @return true if someone has won false if not, sets the win-states and
     * after that the end-state returns true not until the win- and the
     * end-states are setted
     */
    final boolean win() {
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
     *
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
     * Utility-method to reset the boards of both players.
     */
    void resetBoards() {
        player1.resetBoard(injector.getInstance(IBoard.class));
        player2.resetBoard(injector.getInstance(IBoard.class));
    }

    @Override
    public final State getCurrentState() {
        return this.currentState;
    }

    @Override
    public final void setCurrentState(final State newState) {
        State tmp = newState;
        if (newState == WRONGINPUT || newState == PLACEERR) {
            tmp = this.currentState;
            this.currentState = newState;
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
    public final void startGame() {
        if (this.currentState == START || this.currentState == State.OPTIONS) {
            this.setCurrentState(GETNAME1);
        } else if (this.currentState == END) {
            this.resetBoards();
            this.setCurrentState(PLACE1);
        }
    }

    @Override
    public final Map<Integer, Set<Integer>> fillMap(final IShip[] shipList,
                                                    final Map<Integer, Set<Integer>> map,
                                                    final int ships) {
        for (int i = 0; i < ships; i++) {
            this.getSet(shipList[i], map);
        }
        return map;
    }

    /**
     * Utility Method to create a Map where ships take place.
     *
     * @param ship specified ship
     * @param map  specified map
     *
     * @return the new Map
     */
    final Map<Integer, Set<Integer>> getSet(final IShip ship,
                                            final Map<Integer, Set<Integer>> map) {
        if (ship.isOrientation()) {
            int xlow = ship.getX();
            int xupp = xlow + ship.getSize();
            Set<Integer> set = map.get(ship.getY());
            for (int i = xlow; i < xupp; i++) {
                set.add(i);
            }
            return map;
        } else {
            int ylow = ship.getY();
            int yupp = ylow + ship.getSize();
            int x = ship.getX();
            for (int i = ylow; i < yupp; i++) {
                map.get(i).add(x);
            }
            return map;
        }
    }

    @Override
    public final void configureGame() {
        if (this.currentState == START) {
            this.setCurrentState(State.OPTIONS);
        }
    }

    @Override
    public final void setBoardSize(final int boardSize) {
        if (this.currentState != State.OPTIONS ||
            (StatCollection.shipNumberMax + 2) >= boardSize) {
            this.setCurrentState(WRONGINPUT);
            return;
        }
        StatCollection.heightLenght = boardSize;
        this.resetBoards();
        notifyObserver();
    }

    @Override
    public final void setShipNumber(final int shipNumber) {
        if (this.currentState != State.OPTIONS ||
            ((shipNumber + 2) >= StatCollection.heightLenght)) {
            this.setCurrentState(WRONGINPUT);
            return;
        }
        StatCollection.shipNumberMax = shipNumber;
        this.resetBoards();
        notifyObserver();
    }

    @Override
    public final GameMode getGameMode() {
        return this.gm;
    }

    @Override
    public final void setGameMode(final GameMode newMode) {
        if (this.currentState == State.OPTIONS) {
            this.gm = newMode;
            this.notifyObserver();
        } else {
            this.setCurrentState(WRONGINPUT);
        }
    }

    @Override
    public final void configure() {
        this.setCurrentState(State.OPTIONS);
    }

    @Override
    public final void restoreGame(IGameSave save) {
        player1.setName(save.getPlayer1());
        player2.setName(save.getPlayer2());
        gm = save.getGameMode();
        currentState = save.getCurrentState();
        IBoard board1 = injector.getInstance(IBoard.class);
        IBoard board2 = injector.getInstance(IBoard.class);
        board1.restoreBoard(save.getField1(), save.getShipList1());
        board2.restoreBoard(save.getField2(), save.getShipList2());
        StatCollection.heightLenght = save.getHeightLength();
        StatCollection.shipNumberMax = save.getMaxShipNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MasterController)) return false;

        MasterController that = (MasterController) o;
        return this.getCurrentState() == that.currentState &&
               this.gm == that.gm && (this.player1.equals(that.player1) &&
                                      this.player2.equals(that.player2) ||
                                      this.player1.equals(that.player2) &&
                                      this.player2.equals(that.player1));

    }

    @Override
    public int hashCode() {
        int result = shipController.hashCode();
        result = 31 * result + shootController.hashCode();
        result = 31 * result + winController.hashCode();
        result =
            31 * result + (getPlayer1() != null ? getPlayer1().hashCode() : 0);
        result =
            31 * result + (getPlayer2() != null ? getPlayer2().hashCode() : 0);
        result = 31 * result +
                 (getCurrentState() != null ? getCurrentState().hashCode() : 0);
        result = 31 * result + (gm != null ? gm.hashCode() : 0);
        result = 31 * result + (injector != null ? injector.hashCode() : 0);
        return result;
    }
}
