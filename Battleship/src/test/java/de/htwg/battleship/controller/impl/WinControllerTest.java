// WinControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * WinControllerTest tests the entire win controller.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class WinControllerTest extends AbstractTest {

    private IPlayer player1;
    private IPlayer player2;

    /**
     * Set-Up method.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 2;
        StatCollection.shipNumberMax = 2;
        player1 = in.getInstance(IPlayer.class);
        player2 = in.getInstance(IPlayer.class);
    }

    /**
     * Test of win method, of class WinController.
     */
    @Test
    public final void testNullWin() {
        IShip ship = new Ship(1, true, 0, 0);
        player1.getOwnBoard().addShip(ship);
        player2.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer result = wc.win();
        assertEquals(null, result);
    }

    /**
     * Test of win method, result player1.
     */
    @Test
    public final void testPlayer1Win() {
        checkPlayerWin(player1);
    }

    /**
     * Test of win method, result player2.
     */
    @Test
    public final void testPlayer2Win() {
        checkPlayerWin(player2);
    }

    private void checkPlayerWin(IPlayer player) {
        IShip ship = new Ship(1, true, 0, 0);
        player.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer result = wc.win();
        assertEquals(player, result);
    }

    @Test
    public final void testPlayerDestroyed() {
        IShip ship = new Ship(1, true, 0, 0);
        player1.getOwnBoard().addShip(ship);
        player2.getOwnBoard().addShip(ship);
        IShip ship2 = new Ship(1, true, 1, 1);
        player1.getOwnBoard().addShip(ship2);
        player1.getOwnBoard().shoot(0, 0);
        WinController wc = new WinController(player1, player2);
        IPlayer result = wc.win();
        assertEquals(null, result);
    }
}
