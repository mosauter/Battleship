// ShootControllerTest.java

package de.htwg.battleship.controller.impl.childs;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.IBoardValues;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ShootControllerTest tests the entire shoot controller.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-19
 */
public class ShootControllerTest extends AbstractTest {

    public static final int HEIGHT_LENGTH = 10;
    public static final int MAX_SHIPS = 5;
    /**
     * Saves a shootController.
     */
    private ShootController sc;

    public ShootControllerTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up method.
     */
    @Before
    public final void setUp() {
        IBoardValues boardValues = injector.getInstance(IBoardValues.class);
        boardValues.setBoardSize(HEIGHT_LENGTH);
        boardValues.setMaxShips(MAX_SHIPS);
        IPlayer player1 = injector.getInstance(IPlayer.class);
        IPlayer player2 = injector.getInstance(IPlayer.class);
        IShip ship1 = createShip(1, true, 3, 2);
        IShip ship2 = createShip(1, false, 1, 1);
        player1.getOwnBoard().addShip(ship1);
        player2.getOwnBoard().addShip(ship2);
        sc = new ShootController(player1, player2);
    }

    /**
     * Test shoot-Method. Hit-/Non-Hit - Test. Ship-Orientation is true.
     */
    @Test
    public final void shootTestOneOriTrue() {
        boolean result = sc.shoot(3, 2, false);
        assertTrue(result);
        result = sc.shoot(3, 3, false);
        assertFalse(result);
    }

    /**
     * Test shoot-Method. Hit-/Non-Hit - Test. Ship-Orientation is true.
     */
    @Test
    public final void shootTestTwoOriTrue() {
        // first Time true
        // second time false
        //noinspection UnusedAssignment
        boolean result = sc.shoot(3, 2, true);
        result = sc.shoot(3, 2, true);
        assertFalse(result);
        result = sc.shoot(4, 2, true);
        assertFalse(result);
        result = sc.shoot(2, 2, true);
        assertFalse(result);
    }

    /**
     * Test for shoot orientation false.
     */
    @Test
    public final void shootTestOneOriFalse() {
        boolean result = sc.shoot(1, 1, true);
        assertTrue(result);
        result = sc.shoot(1, 1, true);
        assertFalse(result);
    }

    /**
     * Test for shoot orientation false.
     */
    @Test
    public final void shootTestTwoOriFalse() {
        boolean result = sc.shoot(1, 0, false);
        assertFalse(result);
        result = sc.shoot(1, 2, false);
        assertFalse(result);
        result = sc.shoot(0, 1, false);
        assertFalse(result);
    }
}
