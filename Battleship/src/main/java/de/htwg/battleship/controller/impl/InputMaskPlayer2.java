// InputMaskPlayer2.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.GETNAME2;

/**
 * InputMaskPlayer2 presents a input mask for player two.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class InputMaskPlayer2 implements Viewer {

    @Override
    public final String getString() {
        return "\nName Player 2:\t";
    }

    @Override
    public final State getCurrentState() {
        return GETNAME2;
    }
}
