// MenuViewer.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.Viewer;

/**
 * MenuViewer presents the menu to shoot.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public class MenuViewer implements Viewer {

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("What you want to do next?\n");
        sb.append("\t1. Shoot\n");
        sb.append("\t2. Exit\n");
        sb.append("\t\t--->\t");
        return sb.toString();
    }
}
