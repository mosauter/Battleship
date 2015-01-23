
package de.htwg.battleship.aview.tui;

/**
 * @author moritz
 *
 */
public class OptionsMenu implements Viewer {

	@Override
	public String getString() {
		StringBuilder sb = new StringBuilder();
		sb.append("1. Set the Boardsize: Format: 1 [ 4 - 15 ]\n");
		sb.append("2. Set the maximum number of ships: Format: 2 [ 2 - 10 ]\n");
		sb.append("3. Set the Game Mode: Format: 3 [ NORMAL | EXTREME ]\n");
		sb.append("4. start the game: Format: 4\n");
		sb.append("\t-->\t");
		return sb.toString();
	}
}
