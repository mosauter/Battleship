package de.htwg.battleship;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.modules.BattleshipCouchModule;
import de.htwg.battleship.modules.BattleshipHibernateModule;
import de.htwg.battleship.modules.BattleshipStandardModule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * AbstractTest provides some methods which are related to the Guice-injector
 * and is used in several other tests.
 *
 * @author Moritz Sauter <SauterMoritz@gmx.de>
 * @since 2015-04-03
 */
@RunWith(Parameterized.class)
public abstract class AbstractTest {

    /**
     * The Guice Injector with the standard Module.
     */
    protected Injector injector =
        Guice.createInjector(new BattleshipCouchModule());

    @Parameterized.Parameters
    public static Collection modules() {
        AbstractModule[] modules =
            new AbstractModule[] {new BattleshipCouchModule(),
                                  new BattleshipStandardModule(),
                                  new BattleshipHibernateModule()};
        return Arrays.asList(modules);
    }

    protected void createInjector(AbstractModule module) {
        this.injector = Guice.createInjector(module);
    }

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
        IShip ship = injector.getInstance(IShip.class);
        ship.setOrientation(orientation);
        ship.setSize(size);
        ship.setX(x);
        ship.setY(y);
        return ship;
    }

}
