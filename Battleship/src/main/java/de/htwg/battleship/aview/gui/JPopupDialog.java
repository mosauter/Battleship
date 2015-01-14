// JPopupDialog.java
package de.htwg.battleship.aview.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * JPopupDialog modifies JDialog that the dialog shows only a specified time and
 * closes by his own after that. implements Runnable extends JDialog
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-28
 */
@SuppressWarnings({ "serial" })
public class JPopupDialog extends JDialog implements Runnable {

	/**
     * Constant for the font size of the text.
     */
    private static final int FONT_SIZE = 16;
    /**
     * Constant for the width of the dialog.
     */
    private static final int FRAME_WIDTH = 500;
    /**
     * Constant for the height of the dialog.
     */
    private static final int FRAME_HEIGHT = 200;
    /**
     * Saves the time how long the message should be shown to the user before
     * dispose.
     */
    private final long displaytime;

    /**
     * Creates a dialog, which shows a message a specified time to the user,
     * with the specified title, owner {@code Frame} and modality. If
     * {@code owner} is {@code null}, a shared, hidden frame will be set as the
     * owner of this dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox}, {@code JPopupMenu},
     * {@code JMenuBar}) created within a modal dialog will be forced to be
     * lightweight.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog} you must use either
     * the {@code JDialog(Window)} or {@code JDialog(Dialog)} constructor with
     * an argument of {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @param title the {@code String} to display in the dialog's title bar
     * @param modal specifies whether dialog blocks user input to other
     *              top-level windows when shown. If {@code true}, the
     *              modality type property is set to
     *              {@code DEFAULT_MODALITY_TYPE} otherwise the dialog
     *              is modeless
     * @param milliseconds the time how long the message should be displayed to
     *              the user in milliseconds
     * @param message the message which should be displayed to the user
     *
     * @see javax.swing.JDialog
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     *
     * Modified JDialog for my own uses. Comments mostly from the Java API.
     */
    public JPopupDialog(final JFrame owner, final String title,
            final String message, final long milliseconds,
            final boolean modal) {
        super(owner, title, modal);
        this.displaytime = milliseconds;
        JLabel text = new JLabel(message);
        text.setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE));
        text.setForeground(Color.WHITE);
        text.setIcon(new ImageIcon(
        		getClass().getResource("background_dialog.jpg")));
        text.setHorizontalTextPosition(SwingConstants.CENTER);
        text.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setContentPane(text);
        this.setLocationRelativeTo(owner);
        this.setLocation(new Point(
        		((int) this.getLocation().getX() - FRAME_WIDTH / 2),
        		((int) this.getLocation().getY() - FRAME_HEIGHT / 2)));
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        Thread t = new Thread(this, "Popup-Disposer");
        t.start();
    }

    @Override
    public final void run() {
        try {
            Thread.sleep(displaytime);
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(
                    JPopupDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
