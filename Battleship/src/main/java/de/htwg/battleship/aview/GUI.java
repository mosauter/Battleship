package de.htwg.battleship.aview;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.battleship.controller.impl.GuiController;
import de.htwg.battleship.controller.impl.GuiController.ButtonListener;
import de.htwg.battleship.controller.impl.GuiController.PlayerListener;

public class GUI extends JFrame{
	
	GuiController gc = new GuiController(this);
	JButton start = new JButton("Start Game");
	JButton options = new JButton("Options");
	JButton exit = new JButton("Exit");
	JTextField ausgabe;
	JTextField player1;
	JTextField player2;
	Container c;
	
	public GUI() {
		this.setTitle("Battleship");
		this.setLayout(new GridLayout(2, 1));
		 c = getContentPane();
		this.start.setSize(80, 50);
		this.exit.setSize(80, 50);
		this.start.addActionListener(gc);
		this.exit.addActionListener(gc);
		c.add(start);
		c.add(exit);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void newGame(int boardsize) {
		this.setVisible(false);
		//remove old components
		c.remove(start);
		c.remove(exit);
		
		//new Layout
		this.setLayout(new BorderLayout());
		JPanel main = new JPanel();
		c.add(main, BorderLayout.CENTER);
		
		//panel for the left description
		JPanel beschriftung1 = new JPanel();
		beschriftung1.setLayout(new GridLayout(boardsize,1));
		beschriftung1.setPreferredSize(new Dimension(30,30));
		c.add(beschriftung1, BorderLayout.WEST);
		
		//panel for top description
		JPanel beschriftung2 = new JPanel();
		beschriftung2.setLayout(new GridLayout(1,boardsize));
		beschriftung2.setPreferredSize(new Dimension(30, 30));
		//panel for the place in the higher left corner
		JPanel left_higher_corner = new JPanel();
		left_higher_corner.setSize(5,5);
		beschriftung2.add(left_higher_corner);
		c.add(beschriftung2, BorderLayout.NORTH);
		
		//center
		GridLayout gl = new GridLayout(boardsize, boardsize);
		main.setLayout(gl);
		ButtonListener bl = gc.new ButtonListener();
		for(int i = 0; i < boardsize; i++) {
			JLabel x = new JLabel("" + i);
//			x.setHorizontalAlignment(JLabel.CENTER);
			beschriftung1.add(x);
			beschriftung2.add(x);
			for (int j = 0; j < boardsize; j++) {
				String name = i + " " + j ;
				JButton b = new JButton();
				b.setName(name);
				b.addActionListener(bl);
				main.add(b);
			}
		}
		
		//bottom
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,3));
		c.add(bottom, BorderLayout.SOUTH);
		ausgabe = new JTextField();
		ausgabe.setEditable(false);
		bottom.add(ausgabe);
		this.setVisible(true);
	}
	
	public void setPlayer() {
		PlayerListener pl = gc.new PlayerListener();
		JFrame frame = new JFrame();
		frame.setSize(300, 150);
		frame.setLayout(new GridLayout(4, 1));
		frame.add(new JLabel("please insert both player names"));
		this.player1 = new JTextField();
		player1.addActionListener(pl);
		this.player2 = new JTextField();
		player2.addActionListener(pl);
		JButton submit = new JButton("OK");
		submit.addActionListener(pl);
		frame.add(player1);
		frame.add(player2);
		frame.add(submit);
		frame.setLocationRelativeTo(getParent());
		frame.setVisible(true);
//		TextListener tl = gc.new TextListener();
//		ausgabe.addActionListener(tl);
//		ausgabe.setEditable(true);
//		ausgabe.setText("Insert name of player" + number + " : ");
//		ausgabe.setEditable(false);
	}
}
