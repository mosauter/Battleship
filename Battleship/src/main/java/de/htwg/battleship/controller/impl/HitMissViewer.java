// HitMissViewer.java

package de.htwg.battleship.controller.impl;

/**
 * HitMissViewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-17
 */
public class HitMissViewer implements Viewer {

    private final boolean hit;
    public HitMissViewer(boolean hit) {
        this.hit = hit;
    }
    @Override
    public String getString() {
        if (hit) {
            return "Your Shot was a Hit!!\n";
        }
        return "Your Shot was a Miss\n";
    }

}
