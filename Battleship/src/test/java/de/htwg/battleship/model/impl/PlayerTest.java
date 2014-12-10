package de.htwg.battleship.model.impl;

// PlayerTest.java

import de.htwg.battleship.model.IField;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * PlayerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class PlayerTest {

    Player player;
    public PlayerTest() {
    }

    @Before
    public void setUp() {
        player = new Player(new Board());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getOwnBoard() {
        IField[][] expResult = new Field[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                expResult[x][y] = new Field(x, y);
            }
        }
        IField[][] result = player.getOwnBoard().getBoard();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
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
    public void setGetName() {
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
}