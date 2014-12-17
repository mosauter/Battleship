package de.htwg.battleship.controller.impl;

import de.htwg.battleship.aview.GUI;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class GuiController implements ActionListener{
	
	private GUI gui;
	private String[] player = new String[2];
	
	public GuiController(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		switch(target.getText()) {
			case "Start Game":
					newGame();
				break;
			case "Exit": 
					exit();
				break;
			default:
				break;
		}
		
	}
	
	public void newGame() {
		gui.newGame(HEIGTH_LENGTH);
		this.setPlayer();
	}
	public void exit() {
		System.exit(0);
	}
	
	public String[] setPlayer() {
		gui.setPlayer();
		return this.player;
	}
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			StringBuilder sb = new StringBuilder(button.getName());
			String zeile = "" + sb.charAt(0);
			String spalte = "" + sb.charAt(2);
			System.out.println(zeile + ", " + spalte);
			
		}
		
	}
	
	public class PlayerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}

}
