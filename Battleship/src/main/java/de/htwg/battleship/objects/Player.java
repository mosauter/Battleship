// Player.java

package de.htwg.battleship.objects;

/**
 * Player
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class Player {
    private final Board ownBoard;
    private final Board shootBoard;
    //private final String name;
//    private boolean turn;
    
    
    public Player(Board player1Board, Board player2Board) {
        ownBoard = player1Board;
        shootBoard = player2Board;
    }
    
    public boolean shoot(int x, int y) {
        return false;  
    }

}
