// BoardTest.java
package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IField;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.SHIP_NUMBER_MAX;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * BoardTest
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-05
 */
public class BoardTest {

    Board board;

    public BoardTest() {
    }

    @Before
    public void setUp() {
        board = new Board();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addShip method, of class Board.
     */
    @Test
    public void testAddShip() {
        IShip[] expRes = {new Ship(2, true, 3, 4),
            new Ship(3, false, 5, 8),
            new Ship(6, true, 1, 1),
            new Ship(1, true, 10, 10),
            new Ship(4, false, 1, 10)};
        board.addShip(expRes[0]);

        IShip[] result = board.getShipList();
        assertEquals(expRes[0], result[0]);
        for (int i = 1; i < SHIP_NUMBER_MAX; i++) {
            board.addShip(expRes[i]);
        }
        result = board.getShipList();
        for (int i = 0; i < SHIP_NUMBER_MAX; i++) {
            assertEquals(expRes[i], result[i]);
        }
        board.addShip(expRes[3]);
        result = board.getShipList();
        for (int i = 0; i < SHIP_NUMBER_MAX; i++) {
            assertEquals(expRes[i], result[i]);
        }

    }

    @Test
    public void testGetShips() {
        int expRes = 0;
        int result = board.getShips();
        assertEquals(expRes, result);
        Ship[] shipList = {new Ship(2, true, 3, 4),
            new Ship(3, false, 5, 8),
            new Ship(6, true, 1, 1),
            new Ship(1, true, 10, 10),
            new Ship(4, false, 1, 10)};
        for (int i = 0; i < SHIP_NUMBER_MAX; i++) {
            board.addShip(shipList[i]);
            expRes++;
            result = board.getShips();
            assertEquals(expRes, result);
        }
        board.addShip(shipList[3]);
        result = board.getShips();
        assertEquals(expRes, result);
    }

    @Test
    public void testGetBoard() {
        IField[][] expResult = new Field[HEIGTH_LENGTH][HEIGTH_LENGTH];
        for (int x = 0; x < HEIGTH_LENGTH; x++) {
            for (int y = 0; y < HEIGTH_LENGTH; y++) {
                expResult[x][y] = new Field(x, y);
            }
        }
        IField[][] result = board.getBoard();
        for (int x = 0; x < HEIGTH_LENGTH; x++) {
            for (int y = 0; y < HEIGTH_LENGTH; y++) {
                int expX = expResult[x][y].getX();
                int expY = expResult[x][y].getY();
                int resX = result[x][y].getX();
                int resY = result[x][y].getY();
                assertEquals(expX, resX);
                assertEquals(expY, resY);
                assertEquals(expResult[x][y].isHit(), result[x][y].isHit());
            }
        }
    }

    @Test
    public void testShoot() {
        int x = 2;
        int y = 3;
        boolean expRes = false;
        boolean result = board.getField(x, y).isHit();
        assertEquals(expRes, result);
        expRes = true;
        board.shoot(x, y);
        result = board.getField(x, y).isHit();
        assertEquals(expRes, result);
    }
}
