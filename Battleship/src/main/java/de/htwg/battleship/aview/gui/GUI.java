package de.htwg.battleship.aview.gui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import de.htwg.battleship.util.State;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Graphical User Interface.
 */
@SuppressWarnings("serial")
public class GUI extends JFrame implements IObserver {

    private final JButton start = new JButton("Start Game");
    private final JButton options = new JButton("Options");
    private final JButton exit = new JButton("Exit");
    private final long displaytime = 2000L;
    /**
     * JButton[][] for the Field.
     * Button named with: 'x + " " + y'
     */
    private final JButton[][] buttonField
            = new JButton[HEIGTH_LENGTH][HEIGTH_LENGTH];
    private final IMasterController master;
    private final JComboBox<String> orientation = new JComboBox<>();
    private JPanel east;

    /**
     * JButton where the Ship should be placed.
     */
    private static JButton shipPlacePosition;
    private JDialog notifyframe;
    private JTextField player;
    private JTextField ausgabe;
    private JPopupDialog jpd = null;
    private final Container c;

    public GUI(final IMasterController master) {
        this.master = master;
        master.addObserver(this);
        this.setTitle("Battleship");
        this.setLayout(new GridLayout(2, 1));
        c = getContentPane();
        this.start.setSize(80, 50);
        this.exit.setSize(80, 50);
        MenuListener menu = new MenuListener();
        this.start.addActionListener(menu);
        this.exit.addActionListener(menu);
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
        for (int y = 0; y < boardsize; y++) {
            JLabel xLabel = new JLabel("" + y);
            beschriftung1.add(xLabel);
            beschriftung2.add(xLabel);
            for (int x = 0; x < boardsize; x++) {
                String name = x + " " + y;
                buttonField[x][y] = new JButton();
                buttonField[x][y].setName(name);
                main.add(buttonField[x][y]);
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

        master.setCurrentState(State.GETNAME1);
    }

    public void getPlayername(int playernumber) {
        PlayerListener pl = new PlayerListener();
        player = new JTextField();
        JButton submit = new JButton("OK");
        submit.addActionListener(pl);
        notifyframe = new JDialog();
        notifyframe.setModal(true);
        notifyframe.setSize(300, 150);
        notifyframe.setLayout(new GridLayout(3, 1));
        notifyframe.add(new JLabel("please insert playername " + playernumber));
        notifyframe.add(player);
        notifyframe.add(submit);
        notifyframe.setResizable(false);
        notifyframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        notifyframe.setLocationRelativeTo(getParent());
        notifyframe.setVisible(true);
    }

    public void placeShip() {
        this.notifyframe.dispose();
        this.setVisible(false);
        JButton place = new JButton("place");
        place.addActionListener(new PlaceListener());
        east = new JPanel();
        east.setLayout(new GridLayout(3, 1));
        c.add(east, BorderLayout.EAST);
        orientation.addItem("horizontal");
        orientation.addItem("vertical");
        orientation.setPreferredSize(new Dimension(90, 15));
        east.add(orientation);
        east.add(place);
        this.setVisible(true);
    }

    @Override
    public final void update() {
        switch (master.getCurrentState()) {
            case START:
                newGame(HEIGTH_LENGTH);
                break;
            case GETNAME1:
                this.dispose();
                getPlayername(1);
                break;
            case GETNAME2:
                notifyframe.dispose();
                getPlayername(2);
                break;
            case PLACE1:
                notifyframe.dispose();
                resetPlaceButton();
                activateListener(new PlaceListener());
                placeShip();
                break;
            case PLACE2:
                resetPlaceButton();
                activateListener(new PlaceListener());
                break;
            case FINALPLACE1:
                resetPlaceButton();
                break;
            case FINALPLACE2:
                resetPlaceButton();
                break;
            case PLACEERR:
                jpd = new JPopupDialog(this, "Placement error",
                        "Cannot place a ship there due to a collision or "
                                + "the ship is out of the field!",
                        displaytime, false);
                break;
            case SHOOT1:
                this.setVisible(false);
                c.remove(east);
                this.setVisible(true);
                activateListener(new ShootListener());
                break;
            case SHOOT2:
                activateListener(new ShootListener());
                break;
            case HIT:
                jpd = new JPopupDialog(this, "Shot Result",
                        "Your shot was a Hit!!", displaytime, false);
                break;
            case MISS:
                jpd = new JPopupDialog(this, "Shot Result",
                        "Your shot was a Miss!!", displaytime, false);
                break;
            case WIN1:
                String msg = master.getPlayer1().getName() + " has won!!";
                jpd = new JPopupDialog(this, "Winner!", msg,
                        displaytime, false);
                break;
            case WIN2:
                msg = master.getPlayer2().getName() + " has won!!";
                jpd = new JPopupDialog(this, "Winner!", msg,
                        displaytime, false);
                break;
            case WRONGINPUT:
//                sisn't needed in the GUI, help-state for a UI where you
//                can give input at the false states
                break;
            case END:
                break;
            default:
                break;
        }
    }

    /**
     * Method to end the GUI and the TUI as well.
     */
    public final void exit() {
        System.exit(0);
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
        if (button == null) {
            return;
        }
        ActionListener[] actionList = button.getActionListeners();
        for (ActionListener acLst : actionList) {
            button.removeActionListener(acLst);
        }
    }

    /**
     * ActionListener for the State of the Game in which the Player has to set
     * his Ships on the field. PLACE1 / PLACE2 / FINALPLACE1 / FINALPLACE2
     */
    private class PlaceListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if ("place".equals(button.getText())) {
                if (shipPlacePosition != null) {
                    String[] parts = shipPlacePosition.getName().split(" ");
                    if (orientation.getSelectedItem() == "horizontal") {
                        master.placeShip(new Integer(parts[0]),
                                new Integer(parts[1]), true);
                    } else {
                        master.placeShip(new Integer(parts[0]),
                                new Integer(parts[1]), false);
                    }
                } else {
                    throw new UnsupportedOperationException("Not "
                            + "supported yet.");
                }
            } else {
                if (shipPlacePosition != null && shipPlacePosition != button) {
                    switchColor(shipPlacePosition);
                }
                String[] parts = button.getName().split(" ");
                JButton select = buttonField[new Integer(parts[0])]
                        [new Integer(parts[1])];
                switchColor(select);
            }
        }

        /**
         * Method to switch the colour of a button.
         * @param select specified Button
         */
       private void switchColor(final JButton select) {
           JButton defaultColor = new JButton();
           if (select.getBackground() == defaultColor.getBackground()) {
               select.setBackground(Color.BLUE);
               shipPlacePosition = select;
           } else {
               select.setBackground(defaultColor.getBackground());
               shipPlacePosition = null;
           }
        }
    }

    /**
     * ActionListener for the State of the Game in which the Player has to shoot
     * on the other Players board. SHOOT1 / SHOOT2
     */
    private class ShootListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String[] name = button.getName().split(" ");
            int x = Integer.parseInt(name[0]);
            int y = Integer.parseInt(name[1]);
            master.shoot(x, y);
        }
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton target = (JButton) e.getSource();
            switch (target.getText()) {
                case "Start Game":
                    update();
                    break;
                case "Exit":
                    exit();
                    break;
                default:
                    break;
            }
        }

    }

    private class PlayerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            notifyframe.setVisible(false);
            master.setPlayerName(player.getText());
            notifyframe.dispose();
        }
    }

    private void resetPlaceButton() {
        if (shipPlacePosition != null) {
            shipPlacePosition.setBackground((new JButton()).getBackground());
            shipPlacePosition = null;
        }
    }
}
