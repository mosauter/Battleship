// BoardField.java

package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.util.StatCollection;

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
     * Public-Constructor.
     */
    public BoardField() {
        this.shipList = new IShip[StatCollection.shipNumberMax];
        this.ships = 0;
        this.field =
            new boolean[StatCollection.heightLenght][StatCollection.heightLenght];
    }

    @Override
    public int addShip(IShip ship) {
        if (this.ships == StatCollection.shipNumberMax) {
            return -1;
        }
        this.shipList[ships] = ship;
        this.ships++;
        return StatCollection.shipNumberMax - this.ships;
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
