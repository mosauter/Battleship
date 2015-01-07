// BoardTest.java
package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;
import static de.htwg.battleship.util.StatCollection.heightLenght;
import static de.htwg.battleship.util.StatCollection.shipNumberMax;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * BoardTest tests a board implementation.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-05
 */
public class BoardTest {

    /**
     * Saves a Board.
     */
    private Board board;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        board = new Board();
    }

    /**
     * Test of addShip method, of class Board.
     */
    @Test
    public final void testAddShip() {
        IShip[] expRes = {new Ship(2, true, 3, 4),
            new Ship(3, false, 5, 8),
            new Ship(6, true, 1, 1),
            new Ship(1, true, 10, 10),
            new Ship(4, false, 1, 10)};
        board.addShip(expRes[0]);

        IShip[] result = board.getShipList();
        assertEquals(expRes[0], result[0]);
        for (int i = 1; i < shipNumberMax; i++) {
            board.addShip(expRes[i]);
        }
        result = board.getShipList();
        for (int i = 0; i < shipNumberMax; i++) {
            assertEquals(expRes[i], result[i]);
        }
        board.addShip(expRes[3]);
        result = board.getShipList();
        for (int i = 0; i < shipNumberMax; i++) {
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
        Ship[] shipList = {new Ship(2, true, 3, 4),
            new Ship(3, false, 5, 8),
            new Ship(6, true, 1, 1),
            new Ship(1, true, 10, 10),
            new Ship(4, false, 1, 10)};
        for (int i = 0; i < shipNumberMax; i++) {
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
        Field[][] expResult = new Field[heightLenght][heightLenght];
        for (int x = 0; x < heightLenght; x++) {
            for (int y = 0; y < heightLenght; y++) {
                expResult[x][y] = new Field(x, y);
            }
        }
        for (int x = 0; x < heightLenght; x++) {
            for (int y = 0; y < heightLenght; y++) {
                assertEquals(expResult[x][y].isHit(), board.isHit(x, y));
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
        boolean expRes = false;
        boolean result = board.isHit(x, y);
        assertEquals(expRes, result);
        expRes = true;
        board.shoot(x, y);
        result = board.isHit(x, y);
        assertEquals(expRes, result);
    }
}
