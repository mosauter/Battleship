// CollisionController.java

package de.htwg.battleship.controller.impl.childs;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;

/**
 * Controller for checking if ship collides. use the Chain-of-Responsibility-Pattern
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
abstract class CollisionController {

    /**
     * Orientation of the first ship. indicates the responsibility of the
     * Controller implementation
     */
    boolean firstShip;
    /**
     * Orientation of the second ship. indicates the responsibility of the
     * Controller implementation
     */
    boolean secondShip;

    /**
     * Next Controller implementation of the chain.
     */
    CollisionController next;

    /**
     * Method to test if the two ship collides.
     *
     * @param shipIn ship one
     * @param ship   ship two
     *
     * @return true if there is a collision false if not
     */
    public abstract boolean isCollision(IShip shipIn, IShip ship);

    /**
     * Method to search who is responsible for the case in the chain.
     *
     * @param shipIn first ship
     * @param ship   second ship
     *
     * @return true if there is a collision false if not
     */
    final boolean responsibility(final IShip shipIn, final IShip ship) {
        if ((shipIn.isOrientation() == firstShip) &&
            (ship.isOrientation() == secondShip)) {
            return isCollision(shipIn, ship);
        }
        return next.responsibility(shipIn, ship);
    }

    /**
     * Check if the ships collide. To use this method the ships must have
     * different orientations.
     *
     * @param first  the first ship
     * @param second the second ship with a different orientation
     *
     * @return true if the ships collide
     */
    boolean checkShipsDifferent(IShip first, IShip second) {
        int xinlow = second.getX();
        int xinupp = xinlow + second.getSize() - 1;
        int yin = second.getY();
        int ylow = first.getY();
        int yupp = ylow + first.getSize() - 1;
        int x = first.getX();
        return StatCollection.isBetween(yupp, ylow, yin) &&
               StatCollection.isBetween(xinupp, xinlow, x);
    }
}

/**
 * Controller implementation for both ship orientations equals true.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class CollisionOrientationBothTrue extends CollisionController {

    /**
     * Public Constructor.
     */
    CollisionOrientationBothTrue() {
        this.firstShip = true;
        this.secondShip = true;
        this.next = new CollisionOrientationBothFalse();
    }

    @Override
    public boolean isCollision(final IShip shipIn, final IShip ship) {
        int xinlow = shipIn.getX();
        int xinupp = xinlow + shipIn.getSize() - 1;
        int yin = shipIn.getY();
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize() - 1;
        int y = ship.getY();
        for (int i = xlow; i <= xupp; i++) {
            if (StatCollection.isBetween(xinupp, xinlow, i) && y == yin) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Controller for both ship orientations equals false.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class CollisionOrientationBothFalse extends CollisionController {

    /**
     * Public Constructor.
     */
    CollisionOrientationBothFalse() {
        this.firstShip = false;
        this.secondShip = false;
        this.next = new CollisionOrientationFirstTrue();
    }

    @Override
    public boolean isCollision(final IShip shipIn, final IShip ship) {
        int yinlow = shipIn.getY();
        int yinupp = yinlow + shipIn.getSize() - 1;
        int xin = shipIn.getX();
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize() - 1;
        int x = ship.getX();
        for (int i = ylow; i <= yupp; i++) {
            if (StatCollection.isBetween(yinupp, yinlow, i) && x == xin) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Controller implementation for first ship orientation true. extends
 * CollisionController
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class CollisionOrientationFirstTrue extends CollisionController {

    /**
     * Public Constructor.
     */
    CollisionOrientationFirstTrue() {
        this.firstShip = true;
        this.secondShip = false;
        this.next = new CollisionOrientationFirstFalse();
    }

    @Override
    public boolean isCollision(final IShip horizontalShip,
                               final IShip verticalShip) {
        return checkShipsDifferent(verticalShip, horizontalShip);
    }
}

/**
 * Controller implementation for first ship orientation false. extends
 * CollisionController
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class CollisionOrientationFirstFalse extends CollisionController {

    /**
     * Public - Constructor.
     */
    CollisionOrientationFirstFalse() {
        this.firstShip = false;
        this.secondShip = true;
    }

    @Override
    public boolean isCollision(final IShip verticalShip,
                               final IShip horizontalShip) {
        return checkShipsDifferent(verticalShip, horizontalShip);
    }
}
