// Board.java

package objects;

/**
 * The Board-Object where each player adds his own ships.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-10-29
 */
public class Board {

    /**
     * The heigth and the length of the field.
     */
    private static final int HEIGTH_LENGTH = 10;
    /**
     * The max number of ships that could be on the field.
     */
    private static final int SHIP_NUMBER_MAX = 5;
    /**
     * The Ships which were on the Board.
     */
    private Ship[] shipList;
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

    /**
     * Method for adding a new Ship on the field.
     * @param ship the ship that should be added.
     * @return the number of ships that could be added after.
         returns -1 if the field is already full.
     */
    public final int addShip(final Ship ship) {
        if (this.ships == SHIP_NUMBER_MAX) {
            return -1;
        }
        this.shipList[ships] = ship;
        this.ships++;
        return SHIP_NUMBER_MAX - this.ships;
    }

    /**
     * Getter for the number of Ships.
     * @return the number of ships that are current on the field as Integer.
     */
    public int getShips() {
        return ships;
    }

    /**
     * Getter for the list of ships that are on the field.
     * @return the list of ships as Ship[]-Array.
     */
    public Ship[] getShipList() {
        return shipList;
    }
    
    public Field[][] getBoard() {
        return field;
    }
    
    public Field getField(int x, int y) {
        return field[x][y];
    }
    
    public void shoot(int x, int y) {
        field[x][y].setHit(true);
    }
}
