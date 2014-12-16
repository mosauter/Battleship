// TUI.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.observer.IObserver;

/**
 * TUI
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class TUI implements IObserver {

    private final IMasterController master;

    public TUI(IMasterController master) {
        this.master= master;
        this.master.addObserver(this);
    }

    public void printTUI() {
        System.out.println(master.getString());
    }
    public void update() {
        printTUI();
    }
}
