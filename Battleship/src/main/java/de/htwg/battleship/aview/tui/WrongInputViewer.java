// WrongInputViewer.java

package de.htwg.battleship.aview.tui;

/**
 * WrongInputViewer is a presentation as a result of a wrong input.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class WrongInputViewer implements Viewer {

    @Override
    public final String getString() {
        return "Your Input is not recognized and was false"
                + " at the current state of the Game!!\n";
    }
}
