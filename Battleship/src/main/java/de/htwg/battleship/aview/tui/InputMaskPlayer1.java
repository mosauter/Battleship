// InputMask.java

package de.htwg.battleship.aview.tui;

/**
 * InputMask presents the input mask for player one.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class InputMaskPlayer1 implements Viewer {

    @Override
    public final String getString() {
        return "\nName Player 1:\t";
    }
}
