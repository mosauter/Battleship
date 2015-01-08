package de.htwg.battleship.model.impl;

// PlayerTest.java

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.StatCollection;
import static de.htwg.battleship.util.StatCollection.heightLenght;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * PlayerTest tests the player implementation.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class PlayerTest {

    /**
     * Saves player.
     */
    private IPlayer player;

    /**
     * Set-Up.
     */
    @Before
    public final void testSetUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        player = new Player();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test for getOwnBoard method.
     */
    @Test
    public final void testGetOwnBoard() {
        Field[][] expResult = new Field[heightLenght][heightLenght];
        for (int x = 0; x < heightLenght; x++) {
            for (int y = 0; y < heightLenght; y++) {
                expResult[x][y] = new Field(x, y);
            }
        }
        IBoard result = player.getOwnBoard();
        for (int x = 0; x < heightLenght; x++) {
            for (int y = 0; y < heightLenght; y++) {
                assertEquals(expResult[x][y].isHit(), result.isHit(x, y));
            }
        }
    }

    /**
     * Test for setName and getName methods.
     */
    @Test
    public final void setGetName() {
        String expResult = "";
        String result = player.getName();
        assertEquals(expResult, result);
        expResult = "Moritz";
        player.setName(expResult);
        result = player.getName();
        assertEquals(expResult, result);
        player.setName("Hans");
        result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetBoard method, of class Player.
     */
    @Test
    public final void testResetBoard() {
        player.getOwnBoard().addShip(
                new Ship(heightLenght, true, heightLenght, heightLenght));
        player.resetBoard();
        assertEquals(player.getOwnBoard().getShips(), 0);
    }
}