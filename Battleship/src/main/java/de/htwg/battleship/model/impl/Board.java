// Board.java

package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.heightLenght;
import static de.htwg.battleship.util.StatCollection.shipNumberMax;

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
    private final Field[][] field = new Field[heightLenght][heightLenght];

    /**
     * Public-Constructor which initialize the Field-Matrix.
     */
    public Board() {
        this.shipList = new Ship[shipNumberMax];
        this.ships = 0;
        for (int x = 0; x < heightLenght; x++) {
            initFieldMatrix(x);
        }
    }

    /**
     * Utility method to initialize the field matrix.
     * @param x the specified row which has to be initialized
     */
    private void initFieldMatrix(final int x) {
        for (int y = 0; y < heightLenght; y++) {
            field[x][y] = new Field(x, y);
        }
    }

    @Override
    public final int addShip(final IShip ship) {
        if (this.ships == shipNumberMax) {
            return -1;
        }
        this.shipList[ships] = ship;
        this.ships++;
        return shipNumberMax - this.ships;
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
    public final void shoot(final int x, final int y) {
        field[x][y].setHit(true);
    }

    @Override
    public final boolean isHit(final int x, final int y) {
        return field[x][y].isHit();
    }
}
