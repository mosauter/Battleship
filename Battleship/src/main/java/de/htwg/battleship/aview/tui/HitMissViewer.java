// HitMissViewer.java

package de.htwg.battleship.aview.tui;

/**
 * HitMissViewer presents if a shot was a hit or a miss.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-17
 */
public class HitMissViewer implements Viewer {

    /**
     * To save if the shot was a hit.
     */
    private final boolean hit;

    /**
     * Public Constructor.
     * @param hit true if the shot was a hit
     */
    public HitMissViewer(final boolean hit) {
        this.hit = hit;
    }

    @Override
    public final String getString() {
        if (hit) {
            return "Your Shot was a Hit!!\n";
        }
        return "Your Shot was a Miss\n";
    }
}
