// PlaceErrorViewer.java

package de.htwg.battleship.aview.tui;

/**
 * PlaceErrorViewer presents a error-Message.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-17
 */
public class PlaceErrorViewer implements Viewer {

    @Override
    public final String getString() {
        return "Cannot place the Ship because there was a collision "
                + "or the ship ends out of the field. Try again!\n";
    }
}
