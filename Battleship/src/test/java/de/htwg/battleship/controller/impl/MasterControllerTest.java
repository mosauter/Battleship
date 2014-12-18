// MasterControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.START;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * MasterControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class MasterControllerTest {

    IMasterController master;
    IPlayer player1;
    IPlayer player2;

    public MasterControllerTest() {
    }

    @Before
    public void setUp() {
        player1 = new Player();
        player2 = new Player();
        master = new MasterController(player1, player2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shoot method, of class MasterController.
     */
    @Test
    public void testShoot() {
    }

    /**
     * Test of placeShip method, of class MasterController.
     */
    @Test
    public void testPlaceShip() {
    }

    /**
     * Test of win method, of class MasterController.
     */
    @Test
    public void testWin() {
    }


    /**
     * Test of setState method, of class MasterController.
     */
    @Test
    public void testState() {
        State st = START;
        assertEquals(master.getCurrentState(), st);
        st = State.HIT;
        master.setState(st);
        assertEquals(master.getCurrentState(), st);
    }

    /**
     * Test of getPlayer1 method, of class MasterController.
     */
    @Test
    public void testGetPlayer1() {
        assertEquals(master.getPlayer1(), player1);
    }

    /**
     * Test of getPlayer2 method, of class MasterController.
     */
    @Test
    public void testGetPlayer2() {
        assertEquals(master.getPlayer2(), player2);
    }

    /**
     * Test of setPlayerName method, of class MasterController.
     */
    @Test
    public void testSetPlayerName() {
        String name = "Moritz";
        master.setState(State.GETNAME1);
        master.setPlayerName(name);
        assert (name.equals(master.getPlayer1().getName()));
        master.setPlayerName(name);
        assert (name.equals(master.getPlayer2().getName()));
    }

}