package de.htwg.battleship.aview.gui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.StatCollection.createMap;
import static de.htwg.battleship.util.StatCollection.heightLenght;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private static final long DISPLAYTIME = 1000L;

    /**
     * width/height for the description labels.
     */
    private static final int DESCRIPTION_WIDTH_HEIGHT = 40;

    /**
     * space for yAxis.
     */
    private static final int Y_AXIS_GAP = 30;

    /**
     * space for xAxis.
     */
    private static final int X_AXIS_GAP = 10;

    /**
     * height of the bottom label.
     */
    private static final int BOTTOM_HEIGHT = 50;

    /**
     * width of the east label.
     */
    private static final int EAST_WIDTH = 100;

    /**
     * width of the mainframe.
     */
    private static final int FRAME_WIDTH = 1000;

    /**
     * height of the mainframe.
     */
    private static final int FRAME_HEIGHT = 610;

    /**
     * Dimension for Buttons in the JLabel east.
     */
    private static final Dimension EAST_BUTTONS = new Dimension(90, 30);

    /**
     * Dimension for playername frame.
     */
    private static final Dimension PLAYER_FRAME = new Dimension(300, 150);

    /**
     * Dimension for the menuFrame.
     */
    private static final Dimension MENU_FRAME_SIZE = new Dimension(800, 500);

    /**
     * set Bound for playerframe button.
     */
    private static final Rectangle PLAYER_FRAME_BUTTON
            = new Rectangle(75, 80, 150, 30);

    /**
     * set Bounds for playerframe label.
     */
    private static final Rectangle PLAYER_FRAME_LABEL
            = new Rectangle(25, 5, 250, 30);

    /**
     * set Bounds for playerframe textfield.
     */
    private static final Rectangle PLAYER_FRAME_TEXT
            = new Rectangle(25, 40, 250, 30);

    /**
     * set Bounds for menuframe startbutton.
     */
    private static final Rectangle MENU_FRAME_START_BUTTON
            = new Rectangle(300, 240, 200, 50);

    /**
     * set Bounds for menuframe exitbutton.
     */
    private static final Rectangle MENU_FRAME_EXIT_BUTTON
            = new Rectangle(300, 310, 200, 50);

    /**
     * default Font.
     */
    private static final Font FONT = new Font("Helvetica", Font.BOLD, 12);

    /**
     * Border for selected Field.
     */
    private static final LineBorder SELECTED = new LineBorder(Color.RED, 4);

    /**
     * JButton to start or later to restart the Game.
     */
    private final JButton start = new JButton("Start Game");

    /**
     * JButton to exit the whole game right at the beginning or at the end.
     */
    private final JButton exit = new JButton("Exit");

    /**
     * JButton to show the Gamefield of player1 after the Game.
     */
    private final JButton endPlayer1 = new JButton();

    /**
     * JButton to show the Gamefield of player2 after the Game.
     */
    private final JButton endPlayer2 = new JButton();

    /**
     * Button to place a ship in horizontal direction.
     */
    private final JButton hor = new JButton("horizontal");

    /**
     * Button to place a ship in vertical direction.
     */
    private final JButton ver = new JButton("vertical");

    /**
     * JButton[][] for the Field. Button named with: 'x + " " + y'
     */
    private final JButton[][] buttonField
            = new JButton[heightLenght][heightLenght];

    /**
     * default Background for mainframe.
     */
    private final ImageIcon background
            = new ImageIcon(getClass().getResource("background.jpg"));

    /**
     * default background for the menuframe.
     */
    private final ImageIcon backgroundMenu
            = new ImageIcon(getClass().getResource("background_menu.jpg"));

    /**
     * default ImageIcon for non-hitted fields.
     */
    private final ImageIcon wave
            = new ImageIcon(getClass().getResource("wave.jpg"));

    /**
     * ImageIcon for missed shots.
     */
    private final ImageIcon miss
            = new ImageIcon(getClass().getResource("miss.jpg"));

    /**
     * ImageIcon for ship placed fields.
     */
    private final ImageIcon ship
            = new ImageIcon(getClass().getResource("ship.jpg"));

    /**
     * ImageIcon for hitted fields with ship.
     */
    private final ImageIcon hit
            = new ImageIcon(getClass().getResource("ship_hit.jpg"));

    /**
     * ImageIcon for JLabels with invisible background.
     */
    private final ImageIcon invisible
            = new ImageIcon(getClass().getResource("invisible.png"));

    /**
     * To save the MasterController for all of the several UIs.
     */
    private final IMasterController master;

    /**
     * Container which contains all object of the mainframe.
     */
    private final Container container;

    /**
     * JLabel for the center of the mainframe.
     */
    private JLabel center;

    /**
     * JLabel for the east side of the mainframe.
     */
    private JLabel east;

    /**
     * JLabel for the x-Axis description.
     */
    private JLabel xAxis;

    /**
     * JLabel for the y-Axis description.
     */
    private JLabel yAxis;

    /**
     * JLabel to send out instructions.
     */
    private JLabel ausgabe;

    /**
     * JButton where the Ship should be placed.
     */
    private JButton shipPlacePosition;

    /**
     * JDialog which has to be saved that the program can dispose them after its
     * not in use anymore.
     */
    private JFrame notifyframe;

    /**
     * JTextField where the player should input his name.
     */
    private JTextField player;

    /**
     * JFrame for the menu.
     */
    private JFrame menuFrame;

    /**
     * Public Contructor to create a GUI.
     *
     * @param master MasterController which is the same for all UI
     */
    public GUI(final IMasterController master) {
        master.addObserver(this);
        this.master = master;
        //Initialize mainFrame
        this.setTitle("Battleship");
        this.setIconImage(new ImageIcon(
                getClass().getResource("frame_icon.jpg")).getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.container = new JLabel(background);
        this.add(container);
        //Initialize Menu
        this.menuFrame();
        //start Game
        this.newGame();
    }

    /**
     * Method build the menuframe.
     */
    private void menuFrame() {
        this.menuFrame = new JFrame("Battleship");
        this.menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //build Buttons
        this.start.setBounds(MENU_FRAME_START_BUTTON);
        this.exit.setBounds(MENU_FRAME_EXIT_BUTTON);
        this.start.setIcon(new ImageIcon(
                getClass().getResource("menubutton_1.jpg")));
        this.start.setRolloverIcon(new ImageIcon(
                getClass().getResource("menubutton_1_mouseover.jpg")));
        this.start.setBorder(null);
        this.exit.setIcon(new ImageIcon(
                getClass().getResource("menubutton_2.jpg")));
        this.exit.setRolloverIcon(new ImageIcon(
                getClass().getResource("menubutton_2_mouseover.jpg")));
        this.exit.setBorder(null);
        this.start.addActionListener(new MenuListener());
        this.exit.addActionListener(new MenuListener());
        //Container setup
        JLabel startContainer = new JLabel(backgroundMenu);
        startContainer.setLayout(null);
        startContainer.add(start);
        startContainer.add(exit);
        //Frame setup
        this.menuFrame.add(startContainer);
        this.menuFrame.setSize(MENU_FRAME_SIZE);
        this.menuFrame.setResizable(false);
        this.menuFrame.setLocationRelativeTo(null);
        this.menuFrame.setIconImage(new ImageIcon(
                getClass().getResource("frame_icon.jpg")).getImage());
        this.menuFrame.setVisible(true);
    }

    /**
     * Method to initialize the GUI for the fields.
     */
    public void newGame() {
        hor.addActionListener(new PlaceListener());
        ver.addActionListener(new PlaceListener());
        //new Layout
        container.setLayout(new BorderLayout(0, 0));

        //panel for the left description
        JLabel left = new JLabel();
        left.setPreferredSize(new Dimension(DESCRIPTION_WIDTH_HEIGHT,
                FRAME_HEIGHT
                - BOTTOM_HEIGHT
                - DESCRIPTION_WIDTH_HEIGHT));
        left.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        yAxis = new JLabel();
        yAxis.setPreferredSize(new Dimension(DESCRIPTION_WIDTH_HEIGHT,
                FRAME_HEIGHT
                - BOTTOM_HEIGHT
                - DESCRIPTION_WIDTH_HEIGHT
                - Y_AXIS_GAP));
        yAxis.setLayout(new GridLayout(heightLenght, 1));
        yAxis.setVerticalTextPosition(SwingConstants.CENTER);
        left.add(yAxis);
        container.add(left, BorderLayout.WEST);

        //panel for top description
        JLabel top = new JLabel();
        top.setPreferredSize(
                new Dimension(FRAME_WIDTH, DESCRIPTION_WIDTH_HEIGHT));
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        xAxis = new JLabel();
        xAxis.setLayout(new GridLayout(1, heightLenght));
        xAxis.setPreferredSize(new Dimension(
                FRAME_WIDTH
                - DESCRIPTION_WIDTH_HEIGHT
                - EAST_WIDTH
                - X_AXIS_GAP,
                DESCRIPTION_WIDTH_HEIGHT));
        //panel for the place in the higher left corner
        JLabel leftHigherCorner = new JLabel();
        leftHigherCorner.setPreferredSize(
                new Dimension(DESCRIPTION_WIDTH_HEIGHT,
                        DESCRIPTION_WIDTH_HEIGHT));
        // adding components
        top.add(leftHigherCorner);
        top.add(xAxis);
        container.add(top, BorderLayout.NORTH);

        //build gameField
        center = new JLabel();
        buildGameField();
        container.add(center, BorderLayout.CENTER);

        //bottom
        JLabel bottom = new JLabel();
        bottom.setPreferredSize(new Dimension(FRAME_WIDTH, BOTTOM_HEIGHT));
        bottom.setLayout(new FlowLayout());
        ausgabe = new JLabel();
        ausgabe.setHorizontalTextPosition(SwingConstants.CENTER);
        ausgabe.setVerticalTextPosition(SwingConstants.CENTER);
        ausgabe.setPreferredSize(new Dimension(FRAME_WIDTH, BOTTOM_HEIGHT));
        ausgabe.setHorizontalAlignment(SwingConstants.CENTER);
        ausgabe.setFont(GUI.FONT);
        ausgabe.setForeground(Color.WHITE);
        ausgabe.setIcon(new ImageIcon(
                getClass().getResource("invisible_ausgabe.png")));
        bottom.add(ausgabe);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        container.add(bottom, BorderLayout.SOUTH);

        //right
        east = new JLabel();
        east.setPreferredSize(new Dimension(EAST_WIDTH, 1));
        east.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(east, BorderLayout.EAST);
    }

    /**
     * Utility-Method to Build the main Gamefield.
     */
    private void buildGameField() {
        GridLayout gl = new GridLayout(heightLenght, heightLenght);
        center.setLayout(gl);
        for (int y = 0; y < heightLenght; y++) {
            JLabel xLabel = new JLabel();
            JLabel yLabel = new JLabel();
            xLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            xLabel.setVerticalTextPosition(SwingConstants.CENTER);
            yLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            yLabel.setVerticalTextPosition(SwingConstants.CENTER);
            xLabel.setForeground(Color.WHITE);
            yLabel.setForeground(Color.WHITE);
            xLabel.setText("             " + (y + 1));
            yLabel.setText("      " + (char) ('A' + y));
            yAxis.add(yLabel);
            xAxis.add(xLabel);
            for (int x = 0; x < heightLenght; x++) {
                String name = x + " " + y;
                this.buttonField[x][y] = new JButton();
                this.buttonField[x][y].setName(name);
                this.buttonField[x][y].setIcon(wave);
                center.add(buttonField[x][y]);
            }
        }
    }

    /**
     * Utility-Method to create a JDialog where the user should insert his name.
     *
     * @param playernumber indicates the player number
     */
    private void getPlayername(final int playernumber) {
    	this.setVisible(false);
        PlayerListener pl = new PlayerListener();
        JLabel icon = new JLabel(background);
        icon.setPreferredSize(PLAYER_FRAME);
        JLabel text = new JLabel(invisible);
        text.setHorizontalTextPosition(SwingConstants.CENTER);
        text.setForeground(Color.WHITE);
        text.setText("please insert playername " + playernumber);
        text.setBounds(PLAYER_FRAME_LABEL);
        player = new JTextField();
        player.setBorder(new LineBorder(Color.BLACK, 1));
        player.setFont(GUI.FONT);
        player.setBounds(PLAYER_FRAME_TEXT);
        JButton submit = new JButton("OK");
        submit.setIcon(new ImageIcon(
                getClass().getResource("playername_button.jpg")));
        submit.setRolloverIcon(new ImageIcon(
                getClass().getResource("playername_button_mouseover.jpg")));
        submit.setBorder(null);
        submit.setBounds(PLAYER_FRAME_BUTTON);
        submit.addActionListener(pl);
        notifyframe = new JFrame();
        notifyframe.setIconImage(new ImageIcon(
                getClass().getResource("frame_icon.jpg")).getImage());
        notifyframe.add(icon);
        notifyframe.setSize(PLAYER_FRAME);
        icon.setLayout(null);
        icon.add(text);
        icon.add(player);
        icon.add(submit);
        notifyframe.setResizable(false);
        notifyframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        notifyframe.setLocationRelativeTo(getParent());
        notifyframe.getRootPane().setDefaultButton(submit);
        notifyframe.toFront();
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
        resetPlaceButton();
        this.ver.setPreferredSize(EAST_BUTTONS);
        this.ver.setIcon(new ImageIcon(getClass().getResource("vertical.jpg")));
        this.ver.setRolloverIcon(new ImageIcon(
                getClass().getResource("vertical_mouseover.jpg")));
        this.ver.setBorder(null);
        this.hor.setPreferredSize(EAST_BUTTONS);
        this.hor.setIcon(new ImageIcon(
                getClass().getResource("horizontal.jpg")));
        this.hor.setRolloverIcon(new ImageIcon(
                getClass().getResource("horizontal_mouseover.jpg")));
        this.hor.setBorder(null);
        east.add(hor);
        east.add(ver);
        this.setVisible(true);
    }

    /**
     * Utility-Method to update the image-icons of the gamefield buttons.
     * @param player current player
     * @param hideShips boolean
     */
    private void updateGameField(final IPlayer player,
            final boolean hideShips) {
        IShip[] shipList = player.getOwnBoard().getShipList();
        Map<Integer, Set<Integer>> map = createMap();
        master.fillMap(shipList, map, player.getOwnBoard().getShips());
        for (int y = 0; y < heightLenght; y++) {
            for (int x = 0; x < heightLenght; x++) {
                boolean isShip = false;
                this.buttonField[x][y].setBorder(new JButton().getBorder());
                for (Integer value : map.get(y)) {
                    if (value == x) {
                        if (player.getOwnBoard().isHit(x, y)) {
                            this.buttonField[x][y].setIcon(hit);
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
     * Utility-Method to show the Gamefield after the Game.
     */
    private void endGame() {
        this.setVisible(false);
        for (JButton[] buttonArray : buttonField) {
            for (JButton button : buttonArray) {
                removeListener(button);
            }
        }
        this.endPlayer1.setPreferredSize(EAST_BUTTONS);
        this.endPlayer2.setPreferredSize(EAST_BUTTONS);
        this.endPlayer1.setBorder(null);
        this.endPlayer2.setBorder(null);
        this.endPlayer1.setIcon(new ImageIcon(
                getClass().getResource("end_playername.jpg")));
        this.endPlayer2.setIcon(new ImageIcon(
                getClass().getResource("end_playername.jpg")));
        this.endPlayer1.setRolloverIcon(new ImageIcon(
                getClass().getResource("end_playername_mouseover.jpg")));
        this.endPlayer2.setRolloverIcon(new ImageIcon(
                getClass().getResource("end_playername_mouseover.jpg")));
        this.endPlayer1.setVerticalTextPosition(SwingConstants.CENTER);
        this.endPlayer2.setVerticalTextPosition(SwingConstants.CENTER);
        this.endPlayer1.setHorizontalTextPosition(SwingConstants.CENTER);
        this.endPlayer2.setHorizontalTextPosition(SwingConstants.CENTER);
        this.endPlayer1.setText(master.getPlayer1().getName());
        this.endPlayer2.setText(master.getPlayer2().getName());
        this.endPlayer1.setFont(GUI.FONT);
        this.endPlayer2.setFont(GUI.FONT);
        this.endPlayer1.setForeground(Color.WHITE);
        this.endPlayer2.setForeground(Color.WHITE);
        this.endPlayer1.addActionListener(new WinListener());
        this.endPlayer2.addActionListener(new WinListener());
        JButton finish = new JButton();
        finish.setBorder(null);
        finish.setPreferredSize(EAST_BUTTONS);
        finish.setIcon(new ImageIcon(getClass().getResource("finish.jpg")));
        finish.setRolloverIcon(new ImageIcon(
                getClass().getResource("finish_mouseover.jpg")));
        finish.addActionListener(new WinListener());
        east.add(this.endPlayer1);
        east.add(this.endPlayer2);
        east.add(finish);
        this.setVisible(true);
    }

    /**
     * Utility-Method to get the Mainframe
     */
    private JFrame getThis() {
    	return this;
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
                this.setVisible(true);
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
                new JPopupDialog(this, "Placement error",
                        "<html>Cannot place a ship there due to a collision or <br>"
                        + "the ship is out of the field!</html>",
                        (DISPLAYTIME * 2), false);
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
                new JPopupDialog(this, "Shot Result",
                        "Your shot was a Hit!!", DISPLAYTIME, false);
                break;
            case MISS:
                new JPopupDialog(this, "Shot Result",
                        "Your shot was a Miss!!", DISPLAYTIME, false);
                break;
            case WIN1:
                updateGameField(master.getPlayer2(), false);
                String msg = master.getPlayer1().getName() + " has won!!";
                ausgabe.setText(msg);
                new JPopupDialog(this, "Winner!", msg,
                        DISPLAYTIME, false);
                break;
            case WIN2:
                updateGameField(master.getPlayer1(), false);
                msg = master.getPlayer2().getName() + " has won!!";
                ausgabe.setText(msg);
                new JPopupDialog(this, "Winner!", msg,
                        DISPLAYTIME, false);
                break;
            case END:
                endGame();
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
            if (button.equals(hor) || button.equals(ver)) {
                if (shipPlacePosition != null) {
                    String[] parts = shipPlacePosition.getName().split(" ");
                    if (button.equals(hor)) {
                        master.placeShip(Integer.valueOf(parts[0]),
                                Integer.valueOf(parts[1]), true);
                    } else {
                        master.placeShip(Integer.valueOf(parts[0]),
                                Integer.valueOf(parts[1]), false);
                    }
                } else {
                    new JPopupDialog(getThis(), "Placement error",
                            "Please choose a field to place the ship",
                            DISPLAYTIME, false);
                }
            } else {
                if (shipPlacePosition != null
                        && !shipPlacePosition.equals(button)) {
                    switchColor(shipPlacePosition);
                }
                String[] parts = button.getName().split(" ");
                JButton select = buttonField
                        [Integer.valueOf(parts[0])][Integer.valueOf(parts[1])];
                switchColor(select);
            }
        }

        /**
         * Method to switch the colour of a button.
         *
         * @param select specified Button
         */
        private void switchColor(final JButton select) {
            if (!select.getBorder().equals(SELECTED)) {
                select.setBorder(SELECTED);
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
            if (target.getText().equals("Exit")) {
            	System.exit(0);
            } else {
            	master.startGame();
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
        }
    }

    /**
     * ActionListener for the WIN 1 / 2 - States.
     */
    private class WinListener implements ActionListener {

        @Override
        public final void actionPerformed(final ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.equals(endPlayer1)) {
                updateGameField(master.getPlayer2(), false);
            } else if (button.equals(endPlayer2)) {
                updateGameField(master.getPlayer1(), false);
            } else {
                setVisible(false);
                east.removeAll();
                menuFrame.setVisible(true);
                menuFrame.toBack();
                menuFrame.toFront();
            }
        }
    }
}
