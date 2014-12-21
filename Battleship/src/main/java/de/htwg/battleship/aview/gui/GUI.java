package de.htwg.battleship.aview.gui;

import com.google.inject.Inject;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.GuiController;
import de.htwg.battleship.controller.impl.GuiController.ButtonListener;
import de.htwg.battleship.controller.impl.GuiController.PlayerListener;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Graphical User Interface.
 */
public class GUI extends JFrame implements IObserver {

    GuiController gc = new GuiController(this);
    JButton start = new JButton("Start Game");
    JButton options = new JButton("Options");
    JButton exit = new JButton("Exit");
    JTextField ausgabe;
    JTextField player1;
    JTextField player2;
    Container c;
    private final JButton[][] buttonField =
            new JButton[HEIGTH_LENGTH][HEIGTH_LENGTH];
    private final IMasterController master;

    @Inject
    public GUI(final IMasterController master) {
        this.master = master;
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
        beschriftung1.setLayout(new GridLayout(boardsize, 1));
        beschriftung1.setPreferredSize(new Dimension(30, 30));
        c.add(beschriftung1, BorderLayout.WEST);

        //panel for top description
        JPanel beschriftung2 = new JPanel();
        beschriftung2.setLayout(new GridLayout(1, boardsize));
        beschriftung2.setPreferredSize(new Dimension(30, 30));
        //panel for the place in the higher left corner
        JPanel left_higher_corner = new JPanel();
        left_higher_corner.setSize(5, 5);
        beschriftung2.add(left_higher_corner);
        c.add(beschriftung2, BorderLayout.NORTH);

        //center
        GridLayout gl = new GridLayout(boardsize, boardsize);
        main.setLayout(gl);
        ButtonListener bl = gc.new ButtonListener();
        for (int i = 0; i < boardsize; i++) {
            JLabel x = new JLabel("" + i);
//			x.setHorizontalAlignment(JLabel.CENTER);
            beschriftung1.add(x);
            beschriftung2.add(x);
            for (int j = 0; j < boardsize; j++) {
                String name = i + " " + j;
                buttonField[i][j] = new JButton();
                JButton b = new JButton();
                buttonField[i][j].setName(name);
                buttonField[i][j].addActionListener(bl);
                main.add(buttonField[i][j]);
            }
        }

        //bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 3));
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

    @Override
    public final void update() {
        switch (master.getCurrentState()) {
            case START:
                break;
            case GETNAME1:
                break;
            case GETNAME2:
                break;
            case PLACE1:
                activateListener(new PlaceListener());
                break;
            case PLACE2:
                activateListener(new PlaceListener());
                break;
            case FINALPLACE1:
                break;
            case FINALPLACE2:
                break;
            case PLACEERR:
                break;
            case SHOOT1:
                activateListener(new ShootListener());
                break;
            case SHOOT2:
                activateListener(new ShootListener());
                break;
            case HIT:
                break;
            case MISS:
                break;
            case WIN1:
                break;
            case WIN2:
                break;
            case WRONGINPUT:
                
                break;
            default:
                break;
        }
    }

    /**
     * Method to activate a new Action Listener to the JButton[][]-Matrix.
     * uses the removeListener-Method
     * @param newListener the new Listener of the button matrix
     */
    private void activateListener(final ActionListener newListener) {
        for (JButton[] buttonArray : buttonField) {
            for (JButton button : buttonArray) {
                removeListener(button);
                button.addActionListener(newListener);
            }
        }
    }

    /**
     * Method which removes all Listener from a button.
     * @param button specified button
     */
    private void removeListener(final JButton button) {
        ActionListener[] actionList = button.getActionListeners();
        for (ActionListener acLst : actionList) {
            button.removeActionListener(acLst);
        }
    }

    /**
     * ActionListener for the State of the Game in which the Player has to
     * set his Ships on the field.
     * PLACE1 / PLACE2 / FINALPLACE1 / FINALPLACE2
     */
    private class PlaceListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * ActionListener for the State of the Game in which the Player has to
     * shoot on the other Players board.
     * SHOOT1 / SHOOT2
     */
    private class ShootListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}