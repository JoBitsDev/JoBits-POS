/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.areaventa;

import GUI.Views.AbstractDetailView;
import GUI.Views.util.ComboBoxWithList;
import java.awt.Dialog;
import restManager.controller.AbstractDialogController;
import restManager.controller.areaventa.AreaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.DuplicatedException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Area;
import restManager.persistencia.Carta;
import restManager.util.RestManagerListModel;

/**
 *
 * @author Jorge
 */
public class AreaCreateEditView extends AbstractDetailView<Area> {

    ComboBoxWithList<Carta> model;
    
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
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSpinnerPorciento = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
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

        jSpinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        jSpinnerCantidad.setPreferredSize(new java.awt.Dimension(60, 26));
        jPanel4.add(jSpinnerCantidad);

        jPanel8.add(jPanel4);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setText(bundle.getString("label_porciento")); // NOI18N
        jPanel9.add(jLabel3);

        jSpinnerPorciento.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jSpinnerPorciento.setPreferredSize(new java.awt.Dimension(60, 26));
        jPanel9.add(jSpinnerPorciento);

        jPanel8.add(jPanel9);

        jPanel2.add(jPanel8);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_carta"))); // NOI18N
        jPanel6.setLayout(new java.awt.BorderLayout());
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
        getInstance().setPorcientoPorServicio((Integer) jSpinnerPorciento.getValue());
        getInstance().setCartaList(model.getListModel().getElements());
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
        if (getInstance().getPorcientoPorServicio() != null) {
            jSpinnerPorciento.setValue(getInstance().getPorcientoPorServicio());
        }
        model = new ComboBoxWithList<>(getController().getCartaList(), getInstance().getCartaList());
        jPanel6.add(model);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JSpinner jSpinnerPorciento;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

}
