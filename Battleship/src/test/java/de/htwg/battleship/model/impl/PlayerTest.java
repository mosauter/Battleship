package de.htwg.battleship.model.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.StatCollection;
import org.junit.Before;
import org.junit.Test;

import static de.htwg.battleship.util.StatCollection.heightLenght;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// PlayerTest.java

/**
 * PlayerTest tests the player implementation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class PlayerTest {

    private static final String PLAYER_NAME = "PLAYER_NAME";
    private static final int PLAYER_ID = 17;
    /**
     * Saves player.
     */
    private IPlayer player;

    private Injector injector;

    /**
     * Set-Up.
     */
    @Before
    public final void testSetUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        injector = Guice.createInjector(new BattleshipModule());
        player = injector.getInstance(IPlayer.class);
    }

    @Test
    public final void testGetOwnBoard() {
        boolean[][] expResult = new boolean[heightLenght][heightLenght];
        IBoard result = player.getOwnBoard();
        for (int x = 0; x < expResult.length; x++) {
            boolean[] betResult = expResult[x];
            for (int y = 0; y < betResult.length; y++) {
                assertEquals(betResult[y], result.isHit(x, y));
            }
        }
    }

    /**
     * Test for setProfile and getName methods.
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
        //noinspection SuspiciousNameCombination
        player.getOwnBoard().addShip(
            new Ship(heightLenght, true, heightLenght, heightLenght));
        player.resetBoard(injector.getInstance(IBoard.class));
        assertEquals(player.getOwnBoard().getShips(), 0);
    }

    @Test
    public void equalsPlayer() throws Exception {
        player.setProfile(PLAYER_NAME, PLAYER_ID);
        assertFalse(player.equals(injector.getInstance(IPlayer.class)));
    }

    @Test
    public void equalsPlayerObject() throws Exception {
        player.setProfile(PLAYER_NAME, PLAYER_ID);
        assertFalse(player.equals("NO_PLAYER"));
    }

    @Test
    public void equalsPlayerReference() throws Exception {
        player.setProfile(PLAYER_NAME, PLAYER_ID);
        assertTrue(player.equals(player));
    }

    @Test
    public void equalsPlayerTrue() throws Exception {
        player.setProfile(PLAYER_NAME, PLAYER_ID);
        IPlayer testee = injector.getInstance(IPlayer.class);
        testee.setProfile(PLAYER_NAME, PLAYER_ID);
        assertTrue(player.equals(testee));
    }

    @Test
    public void hashPlayer() throws Exception {
        player.setProfile(PLAYER_NAME, PLAYER_ID);
        assertEquals(PLAYER_ID, player.hashCode());
    }

    @Test
    public void getPlayerID() throws Exception {
        player.setProfile(PLAYER_NAME, PLAYER_ID);
        assertEquals(PLAYER_ID, player.getID());
    }
}
