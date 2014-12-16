// StartMenu.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.impl.Viewer;

/**
 * StartMenu
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class StartMenu implements Viewer {

    public String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Start Game\n");
        sb.append("2. Exit\n");
        sb.append("\t--->\t");
        return sb.toString();
    }

}
