/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.statusbar;

import com.jgoodies.binding.adapter.Bindings;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import static com.jobits.pos.ui.statusbar.StatusBarViewModel.*;

/**
 *
 * @author Jorge
 */
public class StatusBarView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Status";

    public StatusBarView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonLlave =
        MaterialComponentsFactory.Buttons.getIconButton(new javax.swing.ImageIcon(getClass().
            getResource("/restManager/resources/images/key.png")));
    jLabelEstadoLicencia = MaterialComponentsFactory.Displayers.getLabel();
    jLabelUsuarioRegistrado = MaterialComponentsFactory.Displayers.getLabel();
    jLabelVersion = MaterialComponentsFactory.Displayers.getLabel();

    jButtonLlave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/key.png"))); // NOI18N
    jButtonLlave.setToolTipText("Activar");
    jButtonLlave.setBorderPainted(false);
    jButtonLlave.setEnabled(false);

    setMaximumSize(new java.awt.Dimension(2147483647, 30));
    setOpaque(false);
    setLayout(new java.awt.GridLayout(1, 3));

    jLabelEstadoLicencia.setForeground(new java.awt.Color(102, 102, 102));
    jLabelEstadoLicencia.setText("<Estado licencia>");
    add(jLabelEstadoLicencia);

    jLabelUsuarioRegistrado.setForeground(new java.awt.Color(102, 102, 102));
    jLabelUsuarioRegistrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelUsuarioRegistrado.setText("<Usuario registrado>");
    add(jLabelUsuarioRegistrado);

    jLabelVersion.setForeground(new java.awt.Color(102, 102, 102));
    jLabelVersion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabelVersion.setText("<Version x.x.x>");
    add(jLabelVersion);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLlave;
    private javax.swing.JLabel jLabelEstadoLicencia;
    private javax.swing.JLabel jLabelUsuarioRegistrado;
    private javax.swing.JLabel jLabelVersion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jLabelVersion, getPresenter().getModel(PROP_VERSION_SOFTWARE));
        Bindings.bind(jLabelUsuarioRegistrado, getPresenter().getModel(PROP_USUARIO_REGISTRADO));
        Bindings.bind(jLabelUsuarioRegistrado, "foreground", getPresenter().getModel(PROP_USUARIO_REGISTRADO_COLOR));
        Bindings.bind(jLabelEstadoLicencia, getPresenter().getModel(PROP_ESTADO_LICENCIA));
        Bindings.bind(jLabelEstadoLicencia, "foreground", getPresenter().getModel(PROP_ESTADO_LICENCIA_COLOR));
        jButtonLlave.setAction(getPresenter().getOperation(StatusBarPresenter.ACTION_LICENCIA));
        jButtonLlave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/key.png")));
    }

    @Override
    public void uiInit() {
        initComponents();
    }

    public void refreshView() {
        getPresenter().getOperation(StatusBarPresenter.ACTION_REFRESH_BEAN).doAction();

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
