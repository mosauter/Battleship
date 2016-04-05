// GameSave

package de.htwg.battleship.model.persistence;

import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.State;

/**
 * IGameSave represents a whole Game instance. In this particular case it saves
 * a whole {@link IMasterController} Object.
 *
 * @author ms
 * @since 2016-04-05
 */
public interface IGameSave {

    /**
     * Same as: {@link IPlayer#getName()}.
     *
     * @return the name of the player one
     */
    String getPlayer1();

    /**
     * Same as: {@link IPlayer#setName(String)} but without the second write
     * protection.
     *
     * @param player1 the new name of player one
     */
    void setPlayer1(String player1);

    /**
     * Same as: {@link IPlayer#getName()}.
     *
     * @return the name of the player two
     */
    String getPlayer2();

    /**
     * Same as: {@link IPlayer#setName(String)} but without the second write
     * protection.
     *
     * @param player2 the new name of player two
     */
    void setPlayer2(String player2);

    /**
     * The {@link GameMode} of the saved instance of the game.
     *
     * @return the specified GameMode
     */
    GameMode getGameMode();

    /**
     * Setter for the GameMode of the saved instance.
     *
     * @param gameMode the new GameMode
     */
    void setGameMode(GameMode gameMode);

    /**
     * The {@link State} in which the game was when it was saved.
     *
     * @return the state
     */
    State getCurrentState();

    /**
     * Setter for the {@link State} of the game instance.
     *
     * @param currentState the state of the game
     */
    void setCurrentState(State currentState);

    /**
     * Same as: {@link IPlayer#getOwnBoard()} of player one.
     *
     * @return the field which should be used in {@link IBoard#restoreBoard(boolean[][],
     * IShip[])}
     */
    boolean[][] getField1();

    /**
     * Setter for the HitMap of player one.
     *
     * @param field1 Hit Map
     */
    void setField1(boolean[][] field1);

    /**
     * Same as: {@link IPlayer#getOwnBoard()} of player two.
     *
     * @return the field which should be used in {@link IBoard#restoreBoard(boolean[][],
     * IShip[])}
     */
    boolean[][] getField2();

    /**
     * Setter for the HitMap of player two.
     *
     * @param field2 Hit Map
     */
    void setField2(boolean[][] field2);

    /**
     * Same as: {@link IBoard#getShipList()} of player one.
     *
     * @return the ship list which should be used in {@link
     * IBoard#restoreBoard(boolean[][], IShip[])}
     */
    IShip[] getShipList1();

    /**
     * Setter for the Ship List of player one.
     *
     * @param shipList1 ship list
     */
    void setShipList1(IShip[] shipList1);

    /**
     * Same as: {@link IBoard#getShipList()} of player two.
     *
     * @return the ship list which should be used in {@link
     * IBoard#restoreBoard(boolean[][], IShip[])}
     */
    IShip[] getShipList2();

    /**
     * Setter for the Ship List of player two.
     *
     * @param shipList2 ship list
     */
    void setShipList2(IShip[] shipList2);

    /**
     * Same as: {@link de.htwg.battleship.util.StatCollection#heightLenght}
     *
     * @return the value of heightLenght
     */
    int getHeightLength();

    /**
     * Setter for {@link de.htwg.battleship.util.StatCollection#heightLenght}
     *
     * @param heightLength the new value for heightLenght
     */
    void setHeightLength(int heightLength);

    /**
     * Same as: {@link de.htwg.battleship.util.StatCollection#shipNumberMax}
     *
     * @return the value of heightLenght
     */
    int getMaxShipNumber();

    /**
     * Setter for {@link de.htwg.battleship.util.StatCollection#shipNumberMax}
     *
     * @param maxShipNumber the new value for shipNumberMax
     */
    void setMaxShipNumber(int maxShipNumber);

    /**
     * Method to restore a whole game with "this" as {@link IGameSave}
     *
     * @param injector a Guice injector which can create a {@link
     *                 IMasterController}
     *
     * @return the {@link IMasterController} which has the restored state of
     * "this"
     */
    IMasterController restoreGame(Injector injector);
}
