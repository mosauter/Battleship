package de.htwg.battleship.model.persistence;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.StatCollection;
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
public class GameSaveTest {

    private static final String PLAYER_NAME = "PLAYER_NAME";
    private static final int BOARD_CONST = 5;
    private static final int PLAYER_1_ID = 1;
    private static final int PLAYER_2_ID = 2;
    private IGameSave gameSave;
    private static final Injector INJECTOR =
        Guice.createInjector(new BattleshipModule());

    @Before
    public void setUp() throws Exception {
        gameSave = INJECTOR.getInstance(IGameSave.class);
    }

    @Test
    public void validateTrue() throws Exception {
        IMasterController masterController =
            INJECTOR.getInstance(IMasterController.class);
        gameSave.saveGame(masterController);
        assertTrue(gameSave.validate());
    }

    @Test
    public void validateFalse() throws Exception {
        assertFalse(gameSave.validate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void restoreGameWithException() throws Exception {
        Injector injector = Guice.createInjector(new BattleshipModule());
        IMasterController mc = gameSave.restoreGame(injector);
    }

    @Test
    public void restoreGame() throws Exception {
        IMasterController masterController =
            INJECTOR.getInstance(IMasterController.class);
        masterController.getPlayer1().setProfile(PLAYER_NAME, PLAYER_1_ID);
        masterController.getPlayer2().setProfile(PLAYER_NAME, PLAYER_2_ID);
        masterController.setCurrentState(State.SHOOT1);
        gameSave.saveGame(masterController);
        IMasterController result = gameSave.restoreGame(INJECTOR);

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
        System.out.println("field1 " + StatCollection.heightLenght);
        gameSave.setField1(
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght]);
        boolean[][] result =
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght];
        compareField(result);
    }

    @Test
    public void field2() throws Exception {

        gameSave.setField1(
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght]);
        boolean[][] result =
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght];
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
        gameSave.setShipList1(new Ship[StatCollection.shipNumberMax]);
        assertEquals(StatCollection.shipNumberMax,
                     gameSave.getShipList1().length);
    }

    @Test
    public void shipList2() throws Exception {
        gameSave.setShipList2(new Ship[StatCollection.shipNumberMax]);
        assertEquals(StatCollection.shipNumberMax,
                     gameSave.getShipList2().length);
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
