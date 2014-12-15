// BorderController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.isBetween;

/**
 * BorderController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public abstract class BorderController {

    boolean shipOrientation;
    BorderController next;
    
    public abstract boolean isIn(IShip ship);
    public final boolean responsibility(final IShip ship) {
        if (ship.isOrientation() == shipOrientation) {
            return isIn(ship);
        }
        return next.responsibility(ship);
    }
}

class BorderTrueController extends BorderController {

    public BorderTrueController() {
        this.shipOrientation = true;
        this.next = new BorderFalseController();
    }

    @Override
    public boolean isIn(IShip ship) {
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize() - 1;
        int y = ship.getY();
        if (!isBetween((HEIGTH_LENGTH - 1), 0, xlow)) {
            return false;
        }
        if (!isBetween((HEIGTH_LENGTH - 1), 0, xupp)) {
            return false;
        }
        if (!isBetween((HEIGTH_LENGTH - 1), 0, y)) {
            return false;
        }
        return true;
    }
    
}

class BorderFalseController extends BorderController {

    public BorderFalseController() {
        this.shipOrientation = false;
        this.next = null;
    }

    @Override
    public boolean isIn(IShip ship) {
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize() - 1;
        int x = ship.getX();
        if (!isBetween((HEIGTH_LENGTH - 1), 0, ylow)) {
            return false;
        }
        if (!isBetween((HEIGTH_LENGTH - 1), 0, yupp)) {
            return false;
        }
        if (!isBetween((HEIGTH_LENGTH - 1), 0, x)) {
            return false;
        }
        return true;
    }
    
}
