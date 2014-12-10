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
     * Getter for the Field-Matrix.
     * @return Field[][]
     */
    IField[][] getBoard();
    /**
     * Getter for one Field in the Board.
     * @param x x-Coordinate of the Field
     * @param y y-Coordinate of the Field
     * @return Field
     */
    IField getField(int x, int y);
    /**
     * Method to shoot on one Field.
     * @param x x-Coordinate of the Field
     * @param y y-Coordinate of the Field
     */
    void shoot(int x, int y);
}
