// WinControllerTest.java

package de.htwg.battleship.controller.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;

/**
 * WinControllerTest tests the entire win controller.
 * 
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-14
 */
public class WinControllerTest {

    private IPlayer  player1;
    private IPlayer  player2;
    private Injector injector;

    /**
     * Set-Up method.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 2;
        StatCollection.shipNumberMax = 2;
        injector = Guice.createInjector(new BattleshipModule());
        IPlayer player1 = injector.getInstance(IPlayer.class);
        IPlayer player2 = injector.getInstance(IPlayer.class);
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
        IPlayer expRes = null;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }

    /**
     * Test of win method, result player1.
     */
    @Test
    public final void testPlayer1Win() {
        IShip ship = new Ship(1, true, 0, 0);
        player1.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer expRes = player1;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }

    /**
     * Test of win method, result player2.
     */
    @Test
    public final void testPlayer2Win() {
        IShip ship = new Ship(1, true, 0, 0);
        player2.getOwnBoard().addShip(ship);
        WinController wc = new WinController(player1, player2);
        IPlayer expRes = player2;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
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
        IPlayer expRes = null;
        IPlayer result = wc.win();
        assertEquals(expRes, result);
    }
}
