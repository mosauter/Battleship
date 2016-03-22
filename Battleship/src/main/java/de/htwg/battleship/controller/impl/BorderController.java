// BorderController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;

import static de.htwg.battleship.util.StatCollection.heightLenght;
import static de.htwg.battleship.util.StatCollection.isBetween;

/**
 * BorderController which checks of the ship is in the field. use the
 * Chain-of-Responsibility-Pattern
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
abstract class BorderController {

    /**
     * Orientation of the ship. indicates the responsibility of the controller
     * implementation
     */
    boolean shipOrientation;

    /**
     * Next controller implementation of the chain.
     */
    BorderController next;

    /**
     * Method to test if the ship is in the Field.
     *
     * @param ship ship to test
     *
     * @return true if it is in false if not
     */
    public abstract boolean isIn(IShip ship);

    /**
     * Method to search who is responsible for the case of the chain.
     *
     * @param ship ship to test
     *
     * @return true if it is in false if not
     */
    final boolean responsibility(final IShip ship) {
        if (ship.isOrientation() == shipOrientation) {
            return isIn(ship);
        }
        return next.responsibility(ship);
    }
}

/**
 * BorderController implementation for the true ship-orientation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class BorderTrueController extends BorderController {

    /**
     * Public Constructor. creates the next controller in the chain
     * automatically
     */
    BorderTrueController() {
        this.shipOrientation = true;
        this.next = new BorderFalseController();
    }

    @Override
    public boolean isIn(final IShip ship) {
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize() - 1;
        return isBetween(heightLenght - 1, 0, xlow) &&
               isBetween(heightLenght - 1, 0, xupp) &&
               isBetween(heightLenght - 1, 0, ship.getY());
    }
}

/**
 * BorderController implementation for the false ship-orientation.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class BorderFalseController extends BorderController {

    /**
     * Public Constructor.
     */
    BorderFalseController() {
        this.shipOrientation = false;
        this.next = null;
    }

    @Override
    public boolean isIn(final IShip ship) {
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize() - 1;
        int x = ship.getX();
        return isBetween(heightLenght - 1, 0, ylow) &&
               isBetween(heightLenght - 1, 0, yupp) &&
               isBetween(heightLenght - 1, 0, x);
    }
}
