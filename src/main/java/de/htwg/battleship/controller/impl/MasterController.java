// MasterController.java

package de.htwg.battleship.controller.impl;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.actor.ActorFactory;
import de.htwg.battleship.actor.messages.*;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.IBoardValues;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    private static final State START_STATE = START;
    private static final Timeout TIMEOUT = new Timeout(5, TimeUnit.SECONDS);
    private static final Logger LOGGER = LogManager.getLogger(MasterController.class);
    private final ActorRef masterActor;
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
     * Saves important values of the size for the board and how many ships could be set on it.
     */
    private IBoardValues boardValues;

    /**
     * Public Constructor.
     *
     * @param player1 player one
     * @param player2 player two
     */
    @Inject
    public MasterController(final IPlayer player1, final IPlayer player2, final Injector in,
                            final IBoardValues boardValues) {
        masterActor = ActorFactory.getMasterRef();
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = START_STATE;
        this.gm = GameMode.NORMAL;
        this.boardValues = boardValues;
        this.injector = in;
    }

    @Override
    public void shoot(final int x, final int y) {
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
        Future<Object> future = Patterns.ask(masterActor, new ShootMessage(player1, player2, x, y, first), TIMEOUT);
        try {
            boolean shootResult = (boolean) Await.result(future, TIMEOUT.duration());
            // set board value on true
            (first ? player2.getOwnBoard() : player1.getOwnBoard()).shoot(x, y);
            if (shootResult) {
                // detected a hit so have to check if someone win
                if (this.win()) {
                    // someone has won, win method handle the rest from here
                    return;
                }
            }
            // a miss was detected and/or noone has won so handle after shoot
            this.hitMiss(shootResult);
            this.nextShootState(before, shootResult);
        } catch (Exception e) {
            logTimeout(e);
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
    public void placeShip(final int x, final int y, final boolean orientation) {
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
        IShip ship = createShip(x, y, orientation, player.getOwnBoard().getShips() + 2);
        Future<Object> future =
            Patterns.ask(masterActor, new ShipMessage(boardValues.getBoardSize(), player, ship), TIMEOUT);
        try {
            boolean result = (boolean) Await.result(future, TIMEOUT.duration());
            if (!result) {
                this.setCurrentState(PLACEERR);
                return;
            }
            // if all was okay add the ship to the players board
            player.getOwnBoard().addShip(ship);
        } catch (Exception e) {
            logTimeout(e);
        }

        if (player.getOwnBoard().getShips() == boardValues.getMaxShips()) {
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
     * Utility Method to create a ship with dependency injection and additional setters.
     *
     * @param x           x-coordinate where the ship starts
     * @param y           y-coordinate where the ship starts
     * @param orientation orientation of the ship true if horizontal, false if vertical
     * @param size        size of the ship
     *
     * @return the created ship
     */
    private IShip createShip(final int x, final int y, final boolean orientation, final int size) {
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
     * @return true if someone has won false if not, sets the win-states and after that the end-state returns true not
     * until the win- and the end-states are setted
     */
    boolean win() {
        Future<Object> future =
            Patterns.ask(masterActor, new WinMessage(this.getPlayer1(), this.getPlayer2()), TIMEOUT);

        try {
            WinnerResponse winnerResponse = (WinnerResponse) Await.result(future, TIMEOUT.duration());
            if (winnerResponse.won()) {
                saveGame();
                winner(winnerResponse.winner().equals(this.player1));
                this.setCurrentState(END);
                return true;
            }
        } catch (final Exception e) {
            logTimeout(e);
        }
        return false;
    }

    private void saveGame() {
        masterActor.tell(new SaveMessage(this), ActorRef.noSender());
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
    public State getCurrentState() {
        return this.currentState;
    }

    @Override
    public void setCurrentState(final State newState) {
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
    public IPlayer getPlayer1() {
        return player1;
    }

    @Override
    public IPlayer getPlayer2() {
        return player2;
    }

    @Override
    public void setPlayerProfile(final String name, final String id) {
        if (this.currentState == GETNAME1) {
            this.player1.setProfile(name, id);
            this.setCurrentState(GETNAME2);
        } else if (this.currentState == GETNAME2) {
            this.player2.setProfile(name, id);
            this.setCurrentState(PLACE1);
        } else {
            this.setCurrentState(WRONGINPUT);
        }
    }

    @Override
    public void setPlayerName(final String name) {
        this.setPlayerProfile(name, StatCollection.LOCAL_PLAYER_ID);
    }

    @Override
    public void startGame() {
        if (this.currentState == START || this.currentState == State.OPTIONS) {
            this.setCurrentState(GETNAME1);
        } else if (this.currentState == END) {
            this.resetBoards();
            this.setCurrentState(PLACE1);
        }
    }

    @Override
    public Map<Integer, Set<Integer>> fillMap(final IShip[] shipList, final Map<Integer, Set<Integer>> map,
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
    final Map<Integer, Set<Integer>> getSet(final IShip ship, final Map<Integer, Set<Integer>> map) {
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
    public void configureGame() {
        if (this.currentState == START) {
            this.setCurrentState(State.OPTIONS);
        }
    }

    @Override
    public void setBoardSize(final int boardSize) {
        if (this.currentState != State.OPTIONS || (this.boardValues.getMaxShips() + 2) >= boardSize) {
            this.setCurrentState(WRONGINPUT);
            return;
        }
        this.boardValues.setBoardSize(boardSize);
        this.resetBoards();
        notifyObserver();
    }

    @Override
    public int getShipNumber() {
        return boardValues.getMaxShips();
    }

    @Override
    public void setShipNumber(final int shipNumber) {
        if (this.currentState != State.OPTIONS || ((shipNumber + 2) >= boardValues.getBoardSize())) {
            this.setCurrentState(WRONGINPUT);
            return;
        }
        boardValues.setMaxShips(shipNumber);
        this.resetBoards();
        notifyObserver();
    }

    @Override
    public GameMode getGameMode() {
        return this.gm;
    }

    @Override
    public void setGameMode(final GameMode newMode) {
        if (this.currentState == State.OPTIONS) {
            this.gm = newMode;
            this.notifyObserver();
        } else {
            this.setCurrentState(WRONGINPUT);
        }
    }

    @Override
    public void configure() {
        this.setCurrentState(State.OPTIONS);
    }

    @Override
    public int getBoardSize() {
        return boardValues.getBoardSize();
    }

    @Override
    public void restoreGame(final IGameSave save) {
        if (!save.validate()) {
            throw new IllegalArgumentException("The game save is not valid, check with IGameSave.validate()");
        }
        player1.setProfile(save.getPlayer1Name(), save.getPlayer1ID());
        player2.setProfile(save.getPlayer2Name(), save.getPlayer2ID());
        gm = save.getGameMode();
        currentState = save.getCurrentState();
        IBoard board1 = injector.getInstance(IBoard.class);
        IBoard board2 = injector.getInstance(IBoard.class);
        boardValues.setMaxShips(save.getMaxShipNumber());
        boardValues.setBoardSize(save.getHeightLength());
        board1.restoreBoard(save.getField1(), save.getShipList1());
        board2.restoreBoard(save.getField2(), save.getShipList2());
    }

    private void logTimeout(Throwable throwable) {
        LOGGER.error("in the masterControler a timeout exception occured", throwable);
    }

    @Override
    @SuppressWarnings("squid:S1067")
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterController)) {
            return false;
        }

        MasterController that = (MasterController) o;
        return this.getCurrentState() == that.currentState &&
               this.gm == that.gm && (this.player1.equals(that.player1) && this.player2.equals(that.player2) ||
                                      this.player1.equals(that.player2) && this.player2.equals(that.player1));

    }

    @Override
    public int hashCode() {
        int result = getPlayer1() != null ? getPlayer1().hashCode() : 0;
        result = 31 * result + (getPlayer2() != null ? getPlayer2().hashCode() : 0);
        result = 31 * result + (getCurrentState() != null ? getCurrentState().hashCode() : 0);
        result = 31 * result + (gm != null ? gm.hashCode() : 0);
        result = 31 * result + (injector != null ? injector.hashCode() : 0);
        return result;
    }
}
