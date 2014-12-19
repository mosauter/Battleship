//IBoard.java

package de.htwg.battleship.model;

/**
 * IBoard is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IBoard {

    /**
     * Method for adding a new Ship on the field.
     * @param ship the ship that should be added.
     * @return the number of ships that could be added after.
     *         returns -1 if the field is already full.
     */
    int addShip(IShip ship);
    /**
     * Getter for the number of Ships.
     * @return the number of ships that are current on the field as Integer.
     */
    int getShips();
    /**
     * Getter for the list of ships that are on the field.
     * @return the list of ships as Ship[]-Array.
     */
    IShip[] getShipList();
    /**
     * Method to shoot on one Field.
     * @param x x-Coordinate of the Field
     * @param y y-Coordinate of the Field
     */
    void shoot(int x, int y);
    /**
     * Getter if the Field is now hit.
     * @param x x-Coordinate of the Field
     * @param y y-Coordinate of the Field
     * @return boolean if the Field is hit
     */
    boolean isHit(int x, int y);
}
