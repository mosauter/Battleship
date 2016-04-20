package de.htwg.battleship.model.persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.BattleshipCouchModule;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * GameSaveTest
 *
 * @author ms
 * @since 2016-03-31
 */
public class GameSaveTest extends AbstractTest {

    private static final String PLAYER_NAME = "PLAYER_NAME";
    private static final int MAX_SHIPS = 5;
    private static final int BOARD_CONST = MAX_SHIPS;
    private static final int PLAYER_1_ID = 1;
    private static final int PLAYER_2_ID = 2;
    private static final int HEIGHT_LENGTH = 10;
    private IGameSave gameSave;

    public GameSaveTest(AbstractModule module) {
        this.createInjector(module);
    }

    @Before
    public void setUp() throws Exception {
        gameSave = injector.getInstance(IGameSave.class);
    }

    @Test
    public void validateTrue() throws Exception {
        IMasterController masterController =
            injector.getInstance(IMasterController.class);
        gameSave.saveGame(masterController);
        assertTrue(gameSave.validate());
    }

    @Test
    public void validateFalse() throws Exception {
        assertFalse(gameSave.validate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void restoreGameWithException() throws Exception {
        Injector injector = Guice.createInjector(new BattleshipCouchModule());
        IMasterController mc = gameSave.restoreGame(injector);
    }

    @Test
    public void restoreGame() throws Exception {
        IMasterController masterController =
            injector.getInstance(IMasterController.class);
        masterController.getPlayer1().setProfile(PLAYER_NAME, PLAYER_1_ID);
        masterController.getPlayer2().setProfile(PLAYER_NAME, PLAYER_2_ID);
        masterController.setCurrentState(State.SHOOT1);
        gameSave.saveGame(masterController);
        IMasterController result = gameSave.restoreGame(injector);

        assertEquals(masterController.getPlayer1().getID(),
                     result.getPlayer1().getID());
        assertEquals(masterController.getPlayer2().getID(),
                     result.getPlayer2().getID());
        assertEquals(masterController.getCurrentState(),
                     result.getCurrentState());
        assertEquals(masterController.getGameMode(), result.getGameMode());
    }

    @Test
    public void player1() throws Exception {
        gameSave.setPlayer1Name(PLAYER_NAME);
        assertEquals(PLAYER_NAME, gameSave.getPlayer1Name());
    }

    @Test
    public void player2() throws Exception {
        gameSave.setPlayer2Name(PLAYER_NAME);
        assertEquals(PLAYER_NAME, gameSave.getPlayer2Name());
    }

    @Test
    public void gameMode() throws Exception {
        gameSave.setGameMode(GameMode.EXTREME);
        assertEquals(GameMode.EXTREME, gameSave.getGameMode());
    }

    @Test
    public void currentState() throws Exception {
        gameSave.setCurrentState(State.START);
        assertEquals(State.START, gameSave.getCurrentState());
    }

    @Test
    public void field1() throws Exception {
        gameSave.setField1(
            new boolean[HEIGHT_LENGTH][HEIGHT_LENGTH]);
        boolean[][] result =
            new boolean[HEIGHT_LENGTH][HEIGHT_LENGTH];
        compareField(result);
    }

    @Test
    public void field2() throws Exception {
        gameSave.setField1(
            new boolean[HEIGHT_LENGTH][HEIGHT_LENGTH]);
        boolean[][] result =
            new boolean[HEIGHT_LENGTH][HEIGHT_LENGTH];
        compareField(result);
    }

    private void compareField(boolean[][] result) {
        for (int i = 0; i < result.length; i++) {
            boolean[] x = result[i];
            boolean[] y = gameSave.getField1()[i];
            for (int j = 0; j < x.length; j++) {
                assertEquals(x[j], y[j]);
            }
        }
    }


    @Test
    public void shipList1() throws Exception {
        gameSave.setShipList1(new Ship[MAX_SHIPS]);
        assertEquals(MAX_SHIPS,
                     gameSave.getShipList1().length);
    }

    @Test
    public void shipList2() throws Exception {
        gameSave.setShipList2(new Ship[MAX_SHIPS]);
        assertEquals(MAX_SHIPS,
                     gameSave.getShipList2().length);
    }

    @Test
    public void setPlayer1ID() throws Exception {
        gameSave.setPlayer1ID(PLAYER_1_ID);
        assertEquals(PLAYER_1_ID, gameSave.getPlayer1ID());
    }

    @Test
    public void setPlayer2ID() throws Exception {
        gameSave.setPlayer2ID(PLAYER_2_ID);
        assertEquals(PLAYER_2_ID, gameSave.getPlayer2ID());
    }

    @Test
    public void heightLength() throws Exception {
        gameSave.setHeightLength(BOARD_CONST);
        assertEquals(BOARD_CONST, gameSave.getHeightLength());
    }

    @Test
    public void maxShipNumber() throws Exception {
        gameSave.setMaxShipNumber(BOARD_CONST);
        assertEquals(BOARD_CONST, gameSave.getMaxShipNumber());
    }
}
