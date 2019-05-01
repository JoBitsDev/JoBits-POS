/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Licence;

import GUI.Views.AbstractView;
import java.awt.Dialog;
import restManager.controller.AbstractDialogController;
import restManager.controller.Controller;
import restManager.controller.Licence.LicenceController;
import restManager.exceptions.DevelopingOperationException;

/**
 *
 * @author Jorge
 */
public class LicenceDialogView extends AbstractView {

    public LicenceDialogView(DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTop = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelInfo = new javax.swing.JPanel();
        jideLabelIdentificador = new com.jidesoft.swing.JideLabel();
        jideLabelEstadoLic = new com.jidesoft.swing.JideLabel();
        jPanel1 = new javax.swing.JPanel();
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
        jPanelActions = new javax.swing.JPanel();
        jButtonActivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(614, 167));
        setUndecorated(true);

        jPanelTop.setBackground(new java.awt.Color(204, 204, 204));
        jPanelTop.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jPanelTop.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        jPanelTop.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelTop.add(jButton1, java.awt.BorderLayout.LINE_START);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Licencia");
        jPanelTop.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelInfo.setLayout(new java.awt.BorderLayout());

        jideLabelIdentificador.setForeground(new java.awt.Color(204, 0, 0));
        jideLabelIdentificador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jideLabelIdentificador.setText("R-XXXX-XXXX-XXXX");
        jideLabelIdentificador.setToolTipText("identificador de la aplicacion");
        jideLabelIdentificador.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jPanelInfo.add(jideLabelIdentificador, java.awt.BorderLayout.PAGE_END);

        jideLabelEstadoLic.setText("Estado de la licencia");
        jPanelInfo.add(jideLabelEstadoLic, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jTextField2.setColumns(4);
        jTextField2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
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
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField9);

        jPanelInfo.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelInfo, java.awt.BorderLayout.CENTER);

        jPanelActions.setBackground(new java.awt.Color(204, 204, 204));
        jPanelActions.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonActivar.setText(bundle.getString("label_activar")); // NOI18N
        jButtonActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActivarActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonActivar);

        getContentPane().add(jPanelActions, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        if (jTextField2.getText().length() > 2) {
            jTextField2.transferFocus();
        }// TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        if (jTextField3.getText().length() > 2) {
            jTextField3.transferFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        if (jTextField4.getText().length() > 2) {
            jTextField4.transferFocus();
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        if (jTextField5.getText().length() > 2) {
            jTextField5.transferFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        if (jTextField6.getText().length() > 2) {
            jTextField6.transferFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        if (jTextField7.getText().length() > 2) {
            jTextField7.transferFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        if (jTextField8.getText().length() > 2) {
            jTextField8.transferFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        if (jTextField9.getText().length() > 2) {
            jTextField9.transferFocus();
        }        // TODO add your handling code here:
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
        getController().validateAndSafe(key);
    }//GEN-LAST:event_jButtonActivarActionPerformed

    @Override
    public void updateView() {
        jideLabelEstadoLic.setText(getController().getEstadoLicencia());
        jideLabelIdentificador.setText(getController().getSoftwareUID());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonActivar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private com.jidesoft.swing.JideLabel jideLabelEstadoLic;
    private com.jidesoft.swing.JideLabel jideLabelIdentificador;
    // End of variables declaration//GEN-END:variables

    @Override
    public LicenceController getController() {
        return (LicenceController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

}
