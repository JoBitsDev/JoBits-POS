/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.areaventa;

import GUI.Views.AbstractDetailView;
import java.awt.Dialog;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.areaventa.AreaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.DuplicatedException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Area;
import restManager.persistencia.Carta;
import restManager.util.RestManagerComboBoxModel;
import restManager.util.RestManagerListModel;

/**
 *
 * @author Jorge
 */
public class AreaCreateEditView extends AbstractDetailView<Area> {

    public AreaCreateEditView(Area instance, AbstractDialogController controller, Dialog owner) {
        super(instance, DialogType.DEFINED, controller, owner);
        initComponents();
        pack();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSpinnerCantidad = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        jButtonAddMenu = new javax.swing.JButton();
        jButtonDeleteMenu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(267, 431));
        setMinimumSize(new java.awt.Dimension(267, 431));
        setPreferredSize(new java.awt.Dimension(267, 431));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jLabel4.setText(bundle.getString("label_nueva_area")); // NOI18N
        jLabel4.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        getContentPane().add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_informacion"))); // NOI18N
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabelID.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelID.setText("A-");
        jLabelID.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelID.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabelID.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel5.add(jLabelID);

        jPanel8.add(jPanel5);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText(bundle.getString("label_nombre")); // NOI18N
        jPanel1.add(jLabel1);

        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanel1.add(jTextFieldNombre);

        jPanel8.add(jPanel1);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText(bundle.getString("label_cantidad_de_mesas")); // NOI18N
        jPanel4.add(jLabel2);

        jSpinnerCantidad.setPreferredSize(new java.awt.Dimension(60, 26));
        jPanel4.add(jSpinnerCantidad);

        jPanel8.add(jPanel4);

        jPanel2.add(jPanel8);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_carta"))); // NOI18N
        jPanel6.setLayout(new java.awt.BorderLayout());

        jComboBox1.setToolTipText("Seleccione el menú a agregar");
        jPanel6.add(jComboBox1, java.awt.BorderLayout.PAGE_START);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonAddMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/aceptar16.png"))); // NOI18N
        jButtonAddMenu.setToolTipText("Agregar Menu");
        jButtonAddMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMenuActionPerformed(evt);
            }
        });
        jPanel7.add(jButtonAddMenu);

        jButtonDeleteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/borrar16.png"))); // NOI18N
        jButtonDeleteMenu.setToolTipText("Quitar Menu");
        jButtonDeleteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteMenuActionPerformed(evt);
            }
        });
        jPanel7.add(jButtonDeleteMenu);

        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel6);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new org.edisoncor.gui.util.DropShadowBorder());

        jButton2.setText(bundle.getString("label_aceptar")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jButton1.setText(bundle.getString("label_cancelar")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getController().createUpdateInstance();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDeleteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteMenuActionPerformed
        eliminarMenu();
    }//GEN-LAST:event_jButtonDeleteMenuActionPerformed

    private void jButtonAddMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMenuActionPerformed
        adicionarMenu();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddMenuActionPerformed

    @Override
    public void setEditingMode() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatingMode() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    
    public boolean validateData() {
        if(jTextFieldNombre.getText() == null || jTextFieldNombre.getText().isEmpty()){
            return false;
        }
        getInstance().setNombre(jTextFieldNombre.getText());
        getInstance().setCapacidad((Integer) jSpinnerCantidad.getValue());
        return true;
    }

    @Override
    public AreaDetailController getController() {
        return (AreaDetailController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateView() {
        jTextFieldNombre.setText(getInstance().getNombre());
        jLabelID.setText(getInstance().getCodArea());
        if (getInstance().getCapacidad() != null) {
            jSpinnerCantidad.setValue(getInstance().getCapacidad());
        }
        jComboBox1.setModel(new RestManagerComboBoxModel<>(getController().getCartaList()));
        jList1.setModel(new RestManagerListModel<>(getInstance().getCartaList()));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAddMenu;
    private javax.swing.JButton jButtonDeleteMenu;
    private javax.swing.JComboBox<Carta> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JList<Carta> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    private void eliminarMenu() {
        if (jList1.getSelectedIndex() == -1) {
            throw new NoSelectedException(this);
        }

        getInstance().getCartaList().remove(jList1.getSelectedValue());
        jList1.setModel(new RestManagerListModel<>(getInstance().getCartaList()));
    }

    private void adicionarMenu() {
        if (jComboBox1.getSelectedIndex() == -1) {
            throw new NoSelectedException(this);
        }
        Carta c = (Carta) jComboBox1.getSelectedItem();
        
        if (getInstance().getCartaList().contains(c)) {
            throw new DuplicatedException(this);
        }
        getInstance().getCartaList().add(c);
        jList1.setModel(new RestManagerListModel<>(getInstance().getCartaList()));
    }
}
