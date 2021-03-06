// BoardTest.java
package de.htwg.battleship.model.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.IBoardValues;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * BoardTest tests a board implementation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-05
 */
public class BoardTest extends AbstractTest {

    private static final int HEIGHT_LENGTH = 10;
    private static final int MAX_SHIPS = 5;

    /**
     * Saves a Board.
     */
    private IBoard board;
    private IBoardValues boardValues;

    public BoardTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        boardValues = injector.getInstance(IBoardValues.class);
        boardValues.setBoardSize(HEIGHT_LENGTH);
        boardValues.setMaxShips(MAX_SHIPS);
        board = injector.getInstance(IBoard.class);
    }

    /**
     * Test of addShip method, of class Board.
     */
    @Test
    public final void testAddShip() {
        IShip[] expRes = {createShip(2, true, 3, 4), createShip(3, false, 5, 8),
                          createShip(6, true, 1, 1),
                          createShip(1, true, 10, 10),
                          createShip(4, false, 1, 10)};
        board.addShip(expRes[0]);

        IShip[] result = board.getShipList();
        assertEquals(expRes[0], result[0]);
        for (int i = 1; i < boardValues.getMaxShips(); i++) {
            board.addShip(expRes[i]);
        }
        result = board.getShipList();
        for (int i = 0; i < boardValues.getMaxShips(); i++) {
            assertEquals(expRes[i], result[i]);
        }
        board.addShip(expRes[3]);
        result = board.getShipList();
        for (int i = 0; i < boardValues.getMaxShips(); i++) {
            assertEquals(expRes[i], result[i]);
        }

    }

    /**
     * Test for the getShips method.
     */
    @Test
    public final void testGetShips() {
        int expRes = 0;
        int result = board.getShips();
        assertEquals(expRes, result);
        IShip[] shipList =
            {createShip(2, true, 3, 4), createShip(3, false, 5, 8),
             createShip(6, true, 1, 1), createShip(1, true, 10, 10),
             createShip(4, false, 1, 10)};
        for (int i = 0; i < boardValues.getMaxShips(); i++) {
            board.addShip(shipList[i]);
            expRes++;
            result = board.getShips();
            assertEquals(expRes, result);
        }
        board.addShip(shipList[3]);
        result = board.getShips();
        assertEquals(expRes, result);
    }

    /**
     * Test for the getBoard method.
     */
    @Test
    public final void testGetBoard() {
        boolean[][] expResult =
            new boolean[boardValues.getBoardSize()][boardValues.getBoardSize()];
        for (int x = 0; x < expResult.length; x++) {
            boolean[] tmpResult = expResult[x];
            for (int y = 0; y < tmpResult.length; y++) {
                assertEquals(tmpResult[y], board.isHit(x, y));
            }
        }
    }

    /**
     * Test for the shoot method.
     */
    @Test
    public final void testShoot() {
        int x = 2;
        int y = 3;
        boolean result = board.isHit(x, y);
        assertFalse(result);
        board.shoot(x, y);
        result = board.isHit(x, y);
        assertTrue(result);
    }

    @Test
    public void testGetHitMap() throws Exception {
        boolean[][] hitMap = board.getHitMap();
        for (boolean[] aHitMap : hitMap) {
            for (boolean anAHitMap : aHitMap) {
                assertFalse(anAHitMap);
            }
        }
    }

    @Test
    public void testRestoreBoard() throws Exception {
        boolean[][] hitMap =
            new boolean[boardValues.getBoardSize()][boardValues.getBoardSize()];
        IShip[] shipList = new IShip[boardValues.getMaxShips()];
        // create a dummy ship
        shipList[0] = createShip(2, true, 3, 4);
        // shoot on (1,1)
        hitMap[1][1] = true;

        // shoot on real hitmap, should be false after the restore
        board.shoot(0, 0);

        board.restoreBoard(hitMap, shipList);

        for (int i = 0; i < board.getHitMap().length; i++) {
            boolean[] row = board.getHitMap()[i];
            for (int j = 0; j < row.length; j++) {
                assertEquals(hitMap[i][j], row[j]);
            }
        }
        IShip[] result = board.getShipList();
        for (int i = 0; i < result.length; i++) {
            assertEquals(shipList[i], result[i]);
        }
    }
}
