// Size

package de.htwg.battleship.util.impl;

import de.htwg.battleship.util.IBoardValues;

/**
 * BoardValues
 *
 * @author ms
 * @since 2016-04-20
 */
public final class BoardValues implements IBoardValues {

    /**
     * Initial value for heightLength.
     */
    private static final int STANDARD_HEIGHT_LENGTH = 10;
    /**
     * Initial value for the maximum ship number.
     */
    private static final int STANDARD_SHIP_NUMBER_MAX = 5;
    /**
     * The current heigth and the length of the field.
     */
    private int heightLength;
    /**
     * The current max number of ships that could be on the field.
     */
    private int shipNumberMax;

    public BoardValues() {
        heightLength = STANDARD_HEIGHT_LENGTH;
        shipNumberMax = STANDARD_SHIP_NUMBER_MAX;
    }

    @Override
    public int getBoardSize() {
        return heightLength;
    }

    @Override
    public int getMaxShips() {
        return shipNumberMax;
    }

    @Override
    public void setMaxShips(int maxShips) {
        this.shipNumberMax = maxShips;
    }

    @Override
    public void setBoardSize(int boardSize) {
        this.heightLength = boardSize;
    }
}
