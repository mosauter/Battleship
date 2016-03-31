package de.htwg.battleship.model.persistence;

import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * GameSaveTest
 *
 * @author ms
 * @since 2016-03-31
 */
public class GameSaveTest {

    private static final String PLAYER_NAME = "PLAYER_NAME";
    private static final int BOARD_CONST = 5;
    private GameSave gameSave;

    @Before
    public void setUp() throws Exception {
        gameSave = new GameSave();
    }

    @Test
    public void restoreGame() throws Exception {
        assert false;
    }

    @Test
    public void testConstruct() throws Exception {
        assert false;
    }

    @Test
    public void player1() throws Exception {
        gameSave.setPlayer1(PLAYER_NAME);
        assertEquals(PLAYER_NAME, gameSave.getPlayer1());
    }

    @Test
    public void player2() throws Exception {
        gameSave.setPlayer2(PLAYER_NAME);
        assertEquals(PLAYER_NAME, gameSave.getPlayer2());
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
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght]);
        boolean[][] result =
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght];
        for (int i = 0; i < result.length; i++) {
            boolean[] x = result[i];
            boolean[] y = gameSave.getField1()[i];
            assertEquals(x, y);
        }
    }

    @Test
    public void field2() throws Exception {
        gameSave.setField1(
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght]);
        boolean[][] result =
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght];
        for (int i = 0; i < result.length; i++) {
            boolean[] x = result[i];
            boolean[] y = gameSave.getField1()[i];
            assertEquals(x, y);
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
