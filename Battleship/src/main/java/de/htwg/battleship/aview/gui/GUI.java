package de.htwg.battleship.aview.gui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.createMap;
import static de.htwg.battleship.util.StatCollection.fillMap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Graphical User Interface for the Game
 *
 * @version 1.00
 * @since 2014-12-20
 */
@SuppressWarnings("serial")
public final class GUI extends JFrame implements IObserver {

    /**
     * Constant that indicates how long the JPopupDialogs should be shown before
     * they close automatically.
     */
    private static final long displaytime = 1000L;

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
     * JButton[][] for the Field. Button named with: 'x + " " + y'
     */
    private final JButton[][] buttonField
            = new JButton[HEIGTH_LENGTH][HEIGTH_LENGTH];

    /**
     * default Background for mainframe
     */
    private final ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));

    /**
     * default background for the menuframe
     */
    private final ImageIcon background_menu = new ImageIcon(getClass().getResource("background_menu.jpg"));

    /**
     * default ImageIcon for non-hitted fields
     */
    private final ImageIcon wave = new ImageIcon(getClass().getResource("wave.jpg"));

    /**
     * ImageIcon for missed shots
     */
    private final ImageIcon miss = new ImageIcon(getClass().getResource("miss.jpg"));

    /**
     * ImageIcon for ship placed fields
     */
    private final ImageIcon ship = new ImageIcon(getClass().getResource("ship.jpg"));

    /**
     * ImageIcon for hitted fields with ship
     */
    private final ImageIcon ship_hit = new ImageIcon(getClass().getResource("ship_hit.jpg"));
    
    /**
     * ImageIcon for JLabels with invisible background
     */
    
    private final ImageIcon invisible = new ImageIcon(getClass().getResource("invisible.png"));

    /**
     * Border for selected Field
     */
    private final LineBorder selected = new LineBorder(Color.RED, 4);

    /**
     * To save the MasterController for all of the several UIs.
     */
    private final IMasterController master;
    
    /**
     * Button to place a ship in horizontal direction
     */
    private final JButton hor = new JButton("horizontal");
    
    /**
     * Button to place a ship in vertical direction
     */
    private final JButton ver = new JButton("vertical");

    /**
     * JPanel for the east side of the mainframe.
     */
    private JLabel east;

    /**
     * JButton where the Ship should be placed.
     */
    private JButton shipPlacePosition;

    /**
     * JDialog which has to be saved that the program can dispose them after its
     * not in use anymore.
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
     * JFrame for the menu
     */
    private final JFrame menuFrame;

    /**
     * Container which includes the menu components
     */
    private final Container startContainer;

    /**
     * Public Contructor to create a GUI.
     *
     * @param master MasterController which is the same for all UI
     */
    public GUI(final IMasterController master) {
        master.addObserver(this);
        this.master = master;
        this.setTitle("Battleship");
        this.setIconImage(new ImageIcon(getClass().getResource("frame_icon.jpg")).getImage());
//        initialize menu
        this.menuFrame = new JFrame("Battleship");
        this.setResizable(false);
//        close operations
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        inizialize main container + set background
        this.container = new JLabel(background);
        this.add(container);
//        add background + layout manager for menuframe
        this.startContainer = new JLabel(background_menu);
        this.startContainer.setLayout(null);
//        set bounds for the menubuttons
        this.start.setBounds(new Rectangle(300, 240, 200, 50));
        this.exit.setBounds(new Rectangle(300, 310, 200, 50));
//        adding button icons and remove border
        this.start.setIcon(new ImageIcon(getClass().getResource("menubutton_1.jpg")));
        this.start.setRolloverIcon(new ImageIcon(getClass().getResource("menubutton_1_mouseover.jpg")));
        this.start.setBorder(null);
        
        this.exit.setIcon(new ImageIcon(getClass().getResource("menubutton_2.jpg")));
        this.exit.setRolloverIcon(new ImageIcon(getClass().getResource("menubutton_2_mouseover.jpg")));
        this.exit.setBorder(null);
//        add actionlisteners
        this.start.addActionListener(new MenuListener());
        this.exit.addActionListener(new MenuListener());
//        add buttons to container
        this.startContainer.add(start);
        this.startContainer.add(exit);
//        add container to frame
        this.menuFrame.add(startContainer);
//        set frame size
        this.menuFrame.setSize(800, 500);
//        set options
        this.menuFrame.setResizable(false);
        this.menuFrame.setLocationRelativeTo(null);
        this.menuFrame.setIconImage(new ImageIcon(getClass().getResource("frame_icon.jpg")).getImage());
//        show
        this.menuFrame.setVisible(true);
//        initialize field
        this.newGame(HEIGTH_LENGTH);
    }

    /**
     * Method to initialize the GUI for the fields.
     *
     * @param boardsize usually StatCollection.HEIGTH_LENGTH
     */
    public void newGame(final int boardsize) {
        //new Layout
        container.setLayout(new BorderLayout(0, 0));
        JPanel main = new JPanel();
        container.add(main, BorderLayout.CENTER);

        //panel for the left description
        JLabel left = new JLabel();
        left.setPreferredSize(new Dimension(40, 520));
        left.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel beschriftung1 = new JLabel();
        beschriftung1.setPreferredSize(new Dimension(40, 493));
        beschriftung1.setLayout(new GridLayout(boardsize, 1));
        beschriftung1.setVerticalTextPosition(JLabel.CENTER);
        

        //panel for top description
        JLabel top = new JLabel();
        top.setPreferredSize(new Dimension(1000, 40));
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel beschriftung2 = new JLabel();
        beschriftung2.setLayout(new GridLayout(1, boardsize));
        beschriftung2.setPreferredSize(new Dimension(860, 40));
        //panel for the place in the higher left corner
        JLabel left_higher_corner = new JLabel();
        left_higher_corner.setPreferredSize(new Dimension(40,40));
        top.add(left_higher_corner);
        top.add(beschriftung2);
        
        //center
        GridLayout gl = new GridLayout(boardsize, boardsize);
        main.setLayout(gl);
        for (int y = 0; y < boardsize; y++) {
            JLabel xLabel = new JLabel();
            JLabel yLabel = new JLabel();
            xLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            xLabel.setVerticalTextPosition(SwingConstants.CENTER);
            yLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            yLabel.setVerticalTextPosition(SwingConstants.CENTER);
            xLabel.setForeground(Color.WHITE);
            yLabel.setForeground(Color.WHITE);
            xLabel.setText("" + (y + 1));
            yLabel.setText("" + (char)('A' + y));
            beschriftung1.add(yLabel);
            beschriftung2.add(xLabel);
            for (int x = 0; x < boardsize; x++) {
                String name = x + " " + y;
                this.buttonField[x][y] = new JButton();
                this.buttonField[x][y].setName(name);
                this.buttonField[x][y].setIcon(wave);
                main.add(buttonField[x][y]);
            }
        }
        
        left.add(beschriftung1);
        container.add(left, BorderLayout.WEST);
        container.add(top, BorderLayout.NORTH);

        //bottom
        JLabel bottom = new JLabel();
        bottom.setPreferredSize(new Dimension(1000, 50));
        bottom.setLayout(new GridLayout(1, 3));
        container.add(bottom, BorderLayout.SOUTH);
        ausgabe = new JLabel();
        ausgabe.setHorizontalTextPosition(SwingConstants.CENTER);
        ausgabe.setVerticalTextPosition(SwingConstants.CENTER);
        ausgabe.setPreferredSize(new Dimension(1000, 50));
        ausgabe.setHorizontalAlignment(SwingConstants.CENTER);
        ausgabe.setFont(new Font("Helvetica", Font.BOLD, 12));
        ausgabe.setForeground(Color.WHITE);
        ausgabe.setIcon(new ImageIcon(getClass().getResource("invisible_ausgabe.png")));
        bottom.add(ausgabe);
        this.setSize(1000, 610);
        this.setLocationRelativeTo(null);
        
        //right
        east = new JLabel();
        east.setPreferredSize(new Dimension(100, 30));
        east.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(east, BorderLayout.EAST);
    }

    /**
     * Utility-Method to create a JDialog where the user should insert his name.
     *
     * @param playernumber
     */
    private void getPlayername(final int playernumber) {
        PlayerListener pl = new PlayerListener();
        JLabel icon = new JLabel(background);
        icon.setBounds(new Rectangle(0, 0, 300, 150));
        JLabel text = new JLabel(invisible);
        text.setHorizontalTextPosition(SwingConstants.CENTER);
        text.setForeground(Color.WHITE);
        text.setText("please insert playername " + playernumber);
        text.setBounds(new Rectangle(25, 5, 250, 30));
        player = new JTextField();
        player.setBorder(new LineBorder(Color.BLACK, 1));
        player.setFont(new Font("Helvetica", Font.BOLD, 12));
        player.setBounds(new Rectangle(25, 40, 250, 30));
        JButton submit = new JButton("OK");
        submit.setIcon(new ImageIcon(getClass().getResource("playername_button.jpg")));
        submit.setRolloverIcon(new ImageIcon(getClass().getResource("playername_button_mouseover.jpg")));
        submit.setBorder(null);
        submit.setBounds(new Rectangle(75, 80, 150, 30));
        submit.addActionListener(pl);
        notifyframe = new JDialog();
        notifyframe.add(icon);
        notifyframe.setModal(true);
        notifyframe.setSize(300, 150);
        icon.setLayout(null);
        icon.add(text);
        icon.add(player);
        icon.add(submit);
        notifyframe.setResizable(false);
        notifyframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        notifyframe.setLocationRelativeTo(getParent());
        notifyframe.getRootPane().setDefaultButton(submit);
        notifyframe.setVisible(true);
    }

    /**
     * Utility-Method to add the 'place'-Button and a ComboBox to the main
     * frame.
     */
    private void placeShip() {
        this.setVisible(false);
        east.remove(hor);
        east.remove(ver);
	    hor.addActionListener(new PlaceListener());
	    ver.addActionListener(new PlaceListener());
	    resetPlaceButton();
	    this.ver.setPreferredSize(new Dimension(90, 30));
	    this.ver.setIcon(new ImageIcon(getClass().getResource("vertical.jpg")));
	    this.ver.setRolloverIcon(new ImageIcon(getClass().getResource("vertical_mouseover.jpg")));
	    this.ver.setBorder(null);
	    this.hor.setPreferredSize(new Dimension(90, 30));
	    this.hor.setIcon(new ImageIcon(getClass().getResource("horizontal.jpg")));
	    this.hor.setRolloverIcon(new ImageIcon(getClass().getResource("horizontal_mouseover.jpg")));
	    this.hor.setBorder(null);
	    east.add(hor);
	    east.add(ver);
        this.setVisible(true);
    }

    /**
     * Utility-Method to reset the selected button in the place states.
     */
    private void resetPlaceButton() {
        if (shipPlacePosition != null) {
            shipPlacePosition.setBorder(new JButton().getBorder());
            shipPlacePosition = null;
        }
    }

    /**
     * Utility-Method to update the image-icons of the gamefield buttons
     *
     * @param player
     */
    private void updateGameField(IPlayer player, boolean hideShips) {
        IShip[] shipList = player.getOwnBoard().getShipList();
        Map<Integer, Set<Integer>> map = createMap();
        fillMap(shipList, map, player.getOwnBoard().getShips());
        for (int y = 0; y < HEIGTH_LENGTH; y++) {
            for (int x = 0; x < HEIGTH_LENGTH; x++) {
                boolean isShip = false;
                this.buttonField[x][y].setBorder(new JButton().getBorder());
                for (Integer value : map.get(y)) {
                    if (value == x) {
                        if (player.getOwnBoard().isHit(x, y)) {
                            this.buttonField[x][y].setIcon(ship_hit);
                        } else {
                            if (hideShips) {
                                this.buttonField[x][y].setIcon(wave);
                            } else {
                                this.buttonField[x][y].setIcon(ship);
                            }
                        }
                        isShip = true;
                    }
                }

                if (!isShip) {
                    if (player.getOwnBoard().isHit(x, y)) {
                        this.buttonField[x][y].setIcon(miss);
                    } else {
                        this.buttonField[x][y].setIcon(wave);
                    }
                }
            }
        }
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
                updateGameField(master.getPlayer1(), false);
                placeShip();
                ausgabe.setText(master.getPlayer1().getName()
                        + " now place the ship with the length of "
                        + (master.getPlayer1().getOwnBoard().getShips() + 2));
                break;
            case PLACE2:
                resetPlaceButton();
                activateListener(new PlaceListener());
                updateGameField(master.getPlayer2(), false);
                placeShip();
                ausgabe.setText(master.getPlayer2().getName()
                        + " now place the ship with the length of "
                        + (master.getPlayer2().getOwnBoard().getShips() + 2));
                break;
            case FINALPLACE1:
                updateGameField(master.getPlayer1(), false);
                resetPlaceButton();
                break;
            case FINALPLACE2:
                updateGameField(master.getPlayer2(), false);
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
                east.remove(hor);
                east.remove(ver);
                this.setVisible(true);
                activateListener(new ShootListener());
                updateGameField(master.getPlayer2(), true);
                ausgabe.setText(master.getPlayer1().getName()
                        + " is now at the turn to shoot");
                break;
            case SHOOT2:
                activateListener(new ShootListener());
                updateGameField(master.getPlayer1(), true);
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
     * Method to activate a new Action Listener to the JButton[][]-Matrix. uses
     * the removeListener-Method
     *
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
     *
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
            if (button == hor || button == ver) {
            	System.out.println(shipPlacePosition);
                if (shipPlacePosition != null) {
                    String[] parts = shipPlacePosition.getName().split(" ");
                    if (button == hor) {
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
            	System.out.println(shipPlacePosition);
                if (shipPlacePosition != null && shipPlacePosition != button) {
                    switchColor(shipPlacePosition);
                }
                String[] parts = button.getName().split(" ");
                JButton select = buttonField[new Integer(parts[0])][new Integer(parts[1])];
                switchColor(select);
            }
        }

        /**
         * Method to switch the colour of a button.
         *
         * @param select specified Button
         */
        private void switchColor(final JButton select) {
            if (!select.getBorder().equals(selected)) {
                select.setBorder(selected);
                shipPlacePosition = select;
            } else {
                select.setBorder(new JButton().getBorder());
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
     * ActionListener for the State of the game where the game is over and the
     * game starts. START / END
     */
    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            menuFrame.setVisible(false);
            JButton target = (JButton) e.getSource();
            switch (target.getText()) {
                case "Start Game":
                    master.startGame();
                    setVisible(true);
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "Start a new Game":
                    setVisible(true);
                    master.startGame();
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
