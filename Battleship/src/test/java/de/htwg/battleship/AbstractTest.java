package de.htwg.battleship;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.model.IShip;

/**
 * AbstractTest provides some methods which are related to the Guice-injector
 * and is used in several other tests.
 *
 * @author Moritz Sauter <SauterMoritz@gmx.de>
 * @since 2015-04-03
 */
public abstract class AbstractTest {

    /**
     * The Guice Injector with the standard Module.
     */
    protected Injector in = Guice.createInjector(new BattleshipModule());

    /**
     * createShip is a help method to create a ship like you did with the
     * constructor before.
     *
     * @param size        of the ship
     * @param orientation of the ship
     * @param x           x-Coordinate where the ship starts
     * @param y           y-Coordinate where the ship starts
     *
     * @return the new IShip
     */
    protected IShip createShip(int size, boolean orientation, int x, int y) {
        IShip ship = in.getInstance(IShip.class);
        ship.setOrientation(orientation);
        ship.setSize(size);
        ship.setX(x);
        ship.setY(y);
        return ship;
    }

}
