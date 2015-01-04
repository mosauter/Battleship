package de.htwg.battleship.aview.gui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.State.GETNAME1;
import static de.htwg.battleship.util.State.PLACE1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.SwingConstants;

/**
 * Graphical User Interface for the Game
 * @version 1.00
 * @since 2014-12-20
 */
@SuppressWarnings("serial")
public final class GUI extends JFrame implements IObserver {

    /**
     * Constant that indicates how long the JPopupDialogs should be shown
     * before they close automatically.
     */
    private static final long displaytime = 1500L;
    
    /**
     * JButton to start or later to restart the Game.
     */
    private final JButton start = new JButton("Start Game");
    
//    /**
//     * JButton to configure the options of the game.
//     * Currently not in use
//     */
//    private final JButton options = new JButton("Options");
    
    /**
     * JButton to exit the whole game right at the beginning or at the end.
     */
    private final JButton exit = new JButton("Exit");
    /**
     * JButton[][] for the Field.
     * Button named with: 'x + " " + y'
     */
    private final JButton[][] buttonField
            = new JButton[HEIGTH_LENGTH][HEIGTH_LENGTH];
    
    /**
     * To save the MasterController for all of the several UIs.
     */
    private final IMasterController master;
    
    /**
     * ComboBox to indicate which orientation the ship that now
     * should be placed.
     */
    private final JComboBox<String> orientation = new JComboBox<>();
    
    /**
     * JPanel for the east side of the mainframe.
     */
    private JPanel east;

    /**
     * JButton where the Ship should be placed.
     */
    private JButton shipPlacePosition;
    
    /**
     * JDialog which has to be saved that the program can dispose them
     * after its not in use anymore.
     */
    private JDialog notifyframe;
    
    /**
     * JTextField where the player should input his name.
     */
    private JTextField player;
    
    /**
     * JPopupDialog to notify the players
     */
    @SuppressWarnings("unused")
	private JPopupDialog jpd = null;
    
    /**
     * JLabel to send out instructions
     */
    private JLabel ausgabe;
    
    /**
     * Container which contains all object of the mainframe
     */
    private final Container container;
    
    /**
     * JFrame 
     */
    private final JFrame menuFrame;
    
    /**
     * 
     */
    private final Container startContainer;

    /**
     * Public Contructor to create a GUI.
     * @param master MasterController which is the same for all UI
     */
    public GUI(final IMasterController master) {
        master.addObserver(this);
        this.master = master;
//        initialize menu
        this.menuFrame = new JFrame("Battleship");
//        close operations
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        get the contentpanes of both frames
        this.container = this.getContentPane();
        this.startContainer = this.menuFrame.getContentPane();
        this.menuFrame.setLayout(new GridLayout(2, 1));
//        set buttonsizes
        this.start.setSize(80, 50);
        this.exit.setSize(80, 50);
//        add actionlisteners
        this.start.addActionListener(new MenuListener());
        this.exit.addActionListener(new MenuListener());
//        add buttons to container
        this.startContainer.add(start);
        this.startContainer.add(exit);
//        set frame size
        this.menuFrame.setSize(800, 500);
//        set options
        this.menuFrame.setResizable(false);
        this.menuFrame.setLocationRelativeTo(null);
//        show
        this.menuFrame.setVisible(true);
//        initialize field
        this.newGame(HEIGTH_LENGTH);
    }

    /**
     * Method to initialize the GUI for the fields.
     * @param boardsize usually StatCollection.HEIGTH_LENGTH
     */
    public void newGame(final int boardsize) {
        //new Layout
        this.setLayout(new BorderLayout());
        JPanel main = new JPanel();
        container.add(main, BorderLayout.CENTER);

        //panel for the left description
        JPanel beschriftung1 = new JPanel();
        beschriftung1.setLayout(new GridLayout(boardsize, 1));
        beschriftung1.setPreferredSize(new Dimension(30, 30));
        container.add(beschriftung1, BorderLayout.WEST);

        //panel for top description
        JPanel beschriftung2 = new JPanel();
        beschriftung2.setLayout(new GridLayout(1, boardsize));
        beschriftung2.setPreferredSize(new Dimension(30, 30));
        //panel for the place in the higher left corner
        JPanel left_higher_corner = new JPanel();
        left_higher_corner.setSize(5, 5);
        beschriftung2.add(left_higher_corner);
        container.add(beschriftung2, BorderLayout.NORTH);

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
        container.add(bottom, BorderLayout.SOUTH);
        ausgabe = new JLabel();
        ausgabe.setPreferredSize(new Dimension(30, 50));
        ausgabe.setHorizontalAlignment(SwingConstants.CENTER);
        ausgabe.setFont(new Font("Courier New", Font.BOLD, 12));
        bottom.add(ausgabe);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
    }

    /**
     * Utility-Method to create a JDialog where the user should insert his name.
     * @param playernumber HEIGHT_LENGTH
     */
    private void getPlayername(final int playernumber) {
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

    /**
     * Utility-Method to add the 'place'-Button and a
     * ComboBox to the main frame.
     */
    private void placeShip() {
        this.setVisible(false);
        JButton place = new JButton("place");
        place.addActionListener(new PlaceListener());
        east = new JPanel();
        east.setLayout(new GridLayout(3, 1));
        container.add(east, BorderLayout.EAST);
        JPanel east_one = new JPanel();
        east_one.setLayout(new GridLayout(3, 1));
        orientation.addItem("horizontal");
        orientation.addItem("vertical");
        orientation.setPreferredSize(new Dimension(90, 15));
        east_one.add(orientation);
        east_one.add(new JPanel());
        east_one.add(place);
        east.add(east_one);
        this.setVisible(true);
    }
    
    /**
     * Utility-Method to reset the selected button in the place states.
     */
    private void resetPlaceButton() {
        if (shipPlacePosition != null) {
            shipPlacePosition.setBackground((new JButton()).getBackground());
            shipPlacePosition = null;
        }
    }
    
    private void updateGameField() {
    	
    }

    @Override
    public void update() {
        switch (master.getCurrentState()) {
            case START:
                break;
            case GETNAME1:
                menuFrame.dispose();
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
                ausgabe.setText(master.getPlayer1().getName()
                        + " now place the ship with the length of "
                        + (master.getPlayer1().getOwnBoard().getShips() + 2));
                break;
            case PLACE2:
                resetPlaceButton();
                activateListener(new PlaceListener());
                ausgabe.setText(master.getPlayer2().getName()
                        + "now place the ship with the length of "
                        + (master.getPlayer2().getOwnBoard().getShips() + 2));
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
                container.remove(east);
                this.setVisible(true);
                activateListener(new ShootListener());
                ausgabe.setText(master.getPlayer1().getName()
                        + " is now at the turn to shoot");
                break;
            case SHOOT2:
                activateListener(new ShootListener());
                ausgabe.setText(master.getPlayer2().getName()
                        + " is now at the turn to shoot");
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
            case END:
                this.setVisible(false);
                this.start.setText("Start a new Game");
                this.menuFrame.setVisible(true);
                this.menuFrame.toBack();
                break;
            case WRONGINPUT:
//                isn't needed in the GUI, help-state for a UI where you
//                can give input at the false states
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

    /**
     * ActionListener for the State of the game where the game is
     * over and the game starts.
     * START / END
     */
    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            menuFrame.setVisible(false);
            JButton target = (JButton) e.getSource();
            switch (target.getText()) {
                case "Start Game":
                    master.setCurrentState(GETNAME1);
                    setVisible(true);
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "Start a new Game":
                    setVisible(true);
                    master.setCurrentState(PLACE1);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * ActionListener for the GETNAME 1 / 2 - States.
     */
    private class PlayerListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            notifyframe.setVisible(false);
            master.setPlayerName(player.getText());
            notifyframe.dispose();
        }
    }

}
