// IBoardValues

package de.htwg.battleship.util;

/**
 * IBoardValues
 *
 * @author ms
 * @since 2016-04-20
 */
public interface IBoardValues {
    int getBoardSize();

    int getMaxShips();

    void setMaxShips(int maxShips);

    void setBoardSize(int boardSize);
}
