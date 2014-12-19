package de.htwg.battleship.model.impl;

// PlayerTest.java

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.util.StatCollection;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
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
        StatCollection.HEIGTH_LENGTH = 10;
        StatCollection.SHIP_NUMBER_MAX = 5;
        player = new Player();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getOwnBoard() {
        Field[][] expResult = new Field[HEIGTH_LENGTH][HEIGTH_LENGTH];
        for (int x = 0; x < HEIGTH_LENGTH; x++) {
            for (int y = 0; y < HEIGTH_LENGTH; y++) {
                expResult[x][y] = new Field(x, y);
            }
        }
        IBoard result = player.getOwnBoard();
        for (int x = 0; x < HEIGTH_LENGTH; x++) {
            for (int y = 0; y < HEIGTH_LENGTH; y++) {
                assertEquals(expResult[x][y].isHit(), result.isHit(x, y));
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