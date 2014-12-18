// WrongInputViewer.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;

/**
 * WrongInputViewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-18
 */
public class WrongInputViewer implements Viewer {

    @Override
    public String getString() {
        return "Your Input is not recognized and was false"
                + " at the current state of the Game!!\n";
    }
}
