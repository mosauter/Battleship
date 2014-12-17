// InputMask.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.GETNAME1;

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

    @Override
    public State getCurrentState() {
        return GETNAME1;
    }
}
