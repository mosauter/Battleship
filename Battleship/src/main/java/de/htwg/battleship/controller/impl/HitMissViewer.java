// HitMissViewer.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.HIT;
import static de.htwg.battleship.util.State.MISS;

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

    @Override
    public State getCurrentState() {
        if (hit) {
            return HIT;
        }
        return MISS;
    }

}
