package restManager.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import restManager.exceptions.DevelopingOperationException;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class LoadingWindow {

    private transient static JWindow LOADING_WINDOW;
    private static final LoadingWindow INSTANCE = new LoadingWindow();

    /**
     * constructor de la clase privado ya que esta se construye una sola vez
     */
    private LoadingWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException ex) {
            ex.addSuppressed(ex);
        }

        LOADING_WINDOW = new JWindow();
        URL image = this.getClass().getResource("/restManager/resources/images/loader.gif");
        LOADING_WINDOW.setAlwaysOnTop(true);
        LOADING_WINDOW.setBackground(new Color(0, 0, 0, 0));
        LOADING_WINDOW.setContentPane(new TranslucentPane());
        LOADING_WINDOW.add(new JLabel(new ImageIcon(image)));
        LOADING_WINDOW.pack();

    }

    public static LoadingWindow getInstance() {
        return INSTANCE;
    }

    /**
     * hide the loading window
     */
    public static void hide() {
        LOADING_WINDOW.setVisible(false);
    }

    /**
     * show the current window
     *
     * @param parent
     */
    public static void show(Component parent) {
            LOADING_WINDOW.setLocationRelativeTo(parent);
            LOADING_WINDOW.setVisible(true);
            LOADING_WINDOW.setAlwaysOnTop(true);
    }

    /**
     * class to remove the background of a panel
     */
    private class TranslucentPane extends JPanel {

        public TranslucentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.0f));
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());

        }

    }

}
