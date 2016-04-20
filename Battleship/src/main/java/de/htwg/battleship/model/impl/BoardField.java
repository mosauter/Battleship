// BoardField.java

package de.htwg.battleship.model.impl;

import com.google.inject.Inject;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.IBoardValues;

/**
 * Another implementation of the Board-Object where each player adds his own
 * ships.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 2.00
 * @since 2015-04-02
 */
public class BoardField implements IBoard {

    /**
     * Saved Ships on the Board.
     */
    private IShip[] shipList;
    /**
     * The hit matrix.
     */
    private boolean[][] field;
    /**
     * How many ships are on the board.
     */
    private int ships;
    /**
     * The maximum number of ships on the current board. Used in constructor and
     * if someone resets the board.
     */
    private int shipNumber;

    /**
     * Public-Constructor.
     */
    @Inject
    public BoardField(IBoardValues boardValues) {
        this.shipNumber = boardValues.getMaxShips();
        this.shipList = new IShip[shipNumber];
        this.ships = 0;
        this.field =
            new boolean[boardValues.getBoardSize()][boardValues.getBoardSize()];
    }

    @Override
    public int addShip(IShip ship) {
        if (this.ships == this.shipNumber) {
            return -1;
        }
        this.shipList[ships] = ship;
        this.ships++;
        return this.shipNumber - this.ships;
    }

    @Override
    public int getShips() {
        return ships;
    }

    @Override
    public IShip[] getShipList() {
        return shipList;
    }

    @Override
    public void shoot(int x, int y) {
        field[x][y] = true;
    }

    @Override
    public boolean isHit(int x, int y) {
        return field[x][y];
    }

    @Override
    public boolean[][] getHitMap() {
        return this.field;
    }

    @Override
    public void restoreBoard(final boolean[][] hitMap, final IShip[] shipList) {
        this.field = hitMap;
        this.shipList = shipList;
    }
}
