// Board.java

package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IField;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.SHIP_NUMBER_MAX;

/**
 * The Board-Object where each player adds his own ships.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-10-29
 */
public class Board implements IBoard {

    /**
     * The Ships which were on the Board.
     */
    private final IShip[] shipList;
    /**
     * The number of ships that are current on the field.
     */
    private int ships;
    /**
     * The field with the saved fields.
     */
    private final Field[][] field = new Field[HEIGTH_LENGTH][HEIGTH_LENGTH];

    /**
     * Public-Constructor which initialize the Field-Matrix.
     */
    public Board() {
        this.shipList = new Ship[SHIP_NUMBER_MAX];
        this.ships = 0;
        for (int x = 0; x < HEIGTH_LENGTH; x++) {
            for (int y = 0; y < HEIGTH_LENGTH; y++) {
                field[x][y] = new Field(x, y);
            }
        }
    }

    @Override
    public final int addShip(final IShip ship) {
        if (this.ships == SHIP_NUMBER_MAX) {
            return -1;
        }
        this.shipList[ships] = ship;
        this.ships++;
        return SHIP_NUMBER_MAX - this.ships;
    }

    @Override
    public final int getShips() {
        return ships;
    }

    @Override
    public final IShip[] getShipList() {
        return shipList;
    }

    @Override
    public final IField[][] getBoard() {
        return field;
    }

    @Override
    public final IField getField(final int x, final int y) {
        return field[x][y];
    }

    @Override
    public final void shoot(final int x, final int y) {
        field[x][y].setHit(true);
    }
}
