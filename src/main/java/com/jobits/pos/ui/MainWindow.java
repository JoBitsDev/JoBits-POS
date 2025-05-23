/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.main.ViewFacade;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.mainmenu.MenuBarClass;
import com.jobits.pos.ui.utils.PopUpDialog;
import com.jobits.pos.ui.venta.VentaDetailView;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Jorge
 */
public class MainWindow extends JFrame {

    /**
     * Creates new form MainWindow
     */
    private CardLayout cards = new CardLayout();
    private RootView rootView;
    private LogInView loginView;

    public MainWindow() {
        super();
        initComponents();
        setIcon();
        getContentPane().setLayout(new BorderLayout());
        setBackground(DefaultValues.PRIMARY_COLOR);
        // setResizable(false);
        setLayout(cards);
        loginView = LogInView.getInstance();
        addWindowListener(new WindowAdapter() {//TODO: buscar un listener para cuando se crea un popup y se pierde el focus
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e); //To change body of generated methods, choose Tools | Templates.
                getGlassPane().setVisible(true);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e); //To change body of generated methods, choose Tools | Templates.
                getGlassPane().setVisible(false);
            }
        });
        setGlassPane(new TransparentWhiteGlassPane());
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setWelcomeHeader(boolean b) {

    }

    public boolean showView(String viewUIDName, AbstractViewPresenter presenter, DisplayType displayType) {

        if (viewUIDName.equals(LogInView.VIEW_NAME)) {
            if (getJMenuBar() != null) {
                getJMenuBar().setVisible(false);
            }
//            PopUpDialog.showPopUP(ViewFacade.getView(viewUIDName, presenter));
            loginView = LogInView.getInstance();
            add(loginView, LogInView.VIEW_NAME);
            cards.show(getContentPane(), LogInView.VIEW_NAME);
            return true;
        }

        if (displayType == DisplayType.POPUP) {
            PopUpDialog.showPopUP(ViewFacade.getView(viewUIDName, presenter));
            return true;
        }

        MenuBarClass.getInstance().getjLabelFecha().setVisible(viewUIDName.equals(VentaDetailView.VIEW_NAME));
        MenuBarClass.getInstance().getjLabelFechaText().setVisible(viewUIDName.equals(VentaDetailView.VIEW_NAME));

        if (viewUIDName.equals(MainMenuView.VIEW_NAME)) {
            rootView = RootView.getInstance();
            add(rootView, RootView.VIEW_NAME);
            cards.show(getContentPane(), RootView.VIEW_NAME);
            if (getJMenuBar() == null) {
                setJMenuBar(MenuBarClass.getInstance().getMainManuBar());
            } else {
                getJMenuBar().setVisible(true);
            }
            return true;
        }
        if (rootView != null) {
            return rootView.showView(viewUIDName, presenter, displayType);
        }

        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private class TransparentWhiteGlassPane extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {
            Rectangle clip = g.getClipBounds();
            Color alphaWhite = new Color(1.0f, 1.0f, 1.0f, 0.65f);
            g.setColor(alphaWhite);
            g.fillRect(clip.x, clip.y, clip.width, clip.height);
        }

    }

    private void setIcon() {
        ImageIcon img = new ImageIcon("restManager/resources/logo/icon.ico");
        setIconImage(img.getImage());
    }
}
