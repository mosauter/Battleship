// IDAO

package de.htwg.battleship.dao;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;

import java.util.List;

/**
 * IDAO
 *
 * @author ms
 * @since 2016-03-29
 */
public interface IDAO {

    /**
     * Should save a whole game with the current state, the two players and the
     * board of the players.
     *
     * @param masterController the {@link de.htwg.battleship.controller.IMasterController}
     *                         of the current game.
     */
    void saveOrUpdateGame(IMasterController masterController);

    /**
     * Tests if a game of the two players exists in the database. Should ignore
     * if {@param player1} is the first or the second player of the game.
     *
     * @param player1 first/second player
     * @param player2 first/second player
     *
     * @return true if a game exists, false if not
     */
    boolean isGameExisting(IPlayer player1, IPlayer player2);

    /**
     * Load a whole game with two specified {@link IPlayer}, and a {@link
     * de.htwg.battleship.util.State} in the {@link IMasterController}. Again it
     * should ignore if the {@param player1} is the first or the second player.
     *
     * @param player1 first/second player
     * @param player2 first/second player
     *
     * @return the whole {@link IMasterController} of the game with the
     * specified players, null if {@link IDAO#isGameExisting(IPlayer, IPlayer)}
     * returns false.
     */
    IMasterController loadGame(IPlayer player1, IPlayer player2);

    /**
     * Lists all possible games for a specified player. Should ignore if the
     * {@param player} is the first or the second player.
     *
     * @param player first/second player
     *
     * @return a list of all possible {@link IMasterController} with the
     * specified player
     */
    List<IMasterController> listAllGames(IPlayer player);
}
