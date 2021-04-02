/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.licencia;

import com.jobits.pos.controller.licencia.impl.Licence;
import com.jobits.pos.controller.licencia.impl.LicenceController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class LicenceDialogView extends JPanel implements View {

    public static final String VIEW_NAME = "Licencia";

    private LicenceController controller;

    //TODO: falta presenter en esta clase
    public LicenceDialogView(LicenceController controller) {
        super();
        this.controller = controller;
        uiInit();
        wireUp();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelTop = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelInfo = MaterialComponentsFactory.Containers.getTransparentPanel();
        jideLabelIdentificador = new javax.swing.JLabel();
        jideLabelEstadoLic = new javax.swing.JLabel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanelActions = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonActivar = MaterialComponentsFactory.Buttons.getMaterialButton();

        setMaximumSize(new java.awt.Dimension(710, 210));
        setMinimumSize(new java.awt.Dimension(710, 173));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(710, 210));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanelTop.setBackground(new java.awt.Color(204, 204, 204));
        jPanelTop.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        jPanelTop.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Licencia");
        jLabel1.setOpaque(true);
        jPanelTop.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelInfo.setLayout(new java.awt.BorderLayout());

        jideLabelIdentificador.setFont(new java.awt.Font(".SF NS Text", 1, 14)); // NOI18N
        jideLabelIdentificador.setForeground(new java.awt.Color(204, 0, 0));
        jideLabelIdentificador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jideLabelIdentificador.setText("R-XXXX-XXXX-XXXX");
        jPanelInfo.add(jideLabelIdentificador, java.awt.BorderLayout.PAGE_END);

        jideLabelEstadoLic.setText("Estado de la licencia");
        jPanelInfo.add(jideLabelEstadoLic, java.awt.BorderLayout.PAGE_START);

        jTextField2.setColumns(4);
        jTextField2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField2.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");
        jPanel1.add(jLabel2);

        jTextField3.setColumns(4);
        jTextField3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField3.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField3);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("-");
        jPanel1.add(jLabel3);

        jTextField4.setColumns(4);
        jTextField4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField4.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField4);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("-");
        jPanel1.add(jLabel9);

        jTextField5.setColumns(4);
        jTextField5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField5.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField5);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("-");
        jPanel1.add(jLabel7);

        jTextField6.setColumns(4);
        jTextField6.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField6.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField6);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("-");
        jPanel1.add(jLabel5);

        jTextField7.setColumns(4);
        jTextField7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField7.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField7);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("-");
        jPanel1.add(jLabel6);

        jTextField8.setColumns(4);
        jTextField8.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField8.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField8);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");
        jPanel1.add(jLabel4);

        jTextField9.setColumns(4);
        jTextField9.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setMaximumSize(new java.awt.Dimension(80, 40));
        jTextField9.setPreferredSize(new java.awt.Dimension(80, 40));
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField9);

        jPanelInfo.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanelInfo, java.awt.BorderLayout.CENTER);

        jPanelActions.setPreferredSize(new java.awt.Dimension(275, 50));
        jPanelActions.setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(140, 40));
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonCancelar, new java.awt.GridBagConstraints());

        jButtonActivar.setText(bundle.getString("label_activar")); // NOI18N
        jButtonActivar.setPreferredSize(new java.awt.Dimension(140, 40));
        jButtonActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActivarActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonActivar, new java.awt.GridBagConstraints());

        jPanel2.add(jPanelActions, java.awt.BorderLayout.PAGE_END);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        validateInput(jTextField2, evt);
//        if (jTextField2.getText().length() > 2) {
//            jTextField2.transferFocus();
//        }// TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        validateInput(jTextField3, evt);
//        if (jTextField3.getText().length() > 2) {
//            jTextField3.transferFocus();
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        validateInput(jTextField4, evt);
//        if (jTextField4.getText().length() > 2) {
//            jTextField4.transferFocus();
//        }     // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        validateInput(jTextField5, evt);
//        if (jTextField5.getText().length() > 2) {
//            jTextField5.transferFocus();
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        validateInput(jTextField6, evt);
//        if (jTextField6.getText().length() > 2) {
//            jTextField6.transferFocus();
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        validateInput(jTextField7, evt);
//        if (jTextField7.getText().length() > 2) {
//            jTextField7.transferFocus();
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        validateInput(jTextField8, evt);
//        if (jTextField8.getText().length() > 2) {
//            jTextField8.transferFocus();
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        validateInput(jTextField9, evt);
//        if (jTextField9.getText().length() > 2) {
//            jTextField9.transferFocus();
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jButtonActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActivarActionPerformed
        String key = jTextField2.getText();
        key += jTextField3.getText();
        key += jTextField4.getText();
        key += jTextField5.getText();
        key += jTextField6.getText();
        key += jTextField7.getText();
        key += jTextField8.getText();
        key += jTextField9.getText();
        boolean result = controller.validateAndSafe(key);
        if (result) {
            Application.getInstance().getNotificationService().notify(
                    controller.getEstadoLic(), TipoNotificacion.SUCCESS);
            NavigationService.getInstance().navigateUp();
        } else {
            Application.getInstance().getNotificationService().notify(
                    controller.getEstadoLic(), TipoNotificacion.ERROR);

        }
    }//GEN-LAST:event_jButtonActivarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        NavigationService.getInstance().navigateUp(); //TODO:arreglar pifia
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActivar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel jideLabelEstadoLic;
    private javax.swing.JLabel jideLabelIdentificador;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        jideLabelEstadoLic.setText(controller.getEstadoLicencia(Licence.TipoLicencia.APLICACION));
        jideLabelIdentificador.setText(controller.getSoftwareUID());

    }

    @Override
    public void uiInit() {
        initComponents();
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public Component getViewComponent() {
        return this;
    }

    @Override
    public void setPresenter(AbstractViewPresenter presenter) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractViewPresenter getPresenter() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    private void validateInput(JTextField textField, KeyEvent evt) {
        switch (textField.getText().length()) {
            case 0:
                if (evt.getKeyChar() == '\b' & textField != jTextField2) {
                    textField.transferFocusBackward();
                    evt.consume();
                }
                break;
            case 4:
                if (textField != jTextField9) {
                    textField.transferFocus();
                }
                evt.consume();
                break;
        }
    }
}
