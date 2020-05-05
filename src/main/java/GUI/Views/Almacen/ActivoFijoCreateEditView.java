/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractDetailView;
import java.awt.Dialog;
import java.util.Date;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.almacen.ActivoFijoController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.ActivoFijo;
import restManager.persistencia.Ubicacion;
import restManager.resources.values.Colors;
import restManager.util.RestManagerComboBoxModel;
import restManager.util.utils;

/**
 *
 * @author Jorge
 */
public class ActivoFijoCreateEditView extends AbstractDetailView<ActivoFijo> {

    private boolean editingMode = false;

    public ActivoFijoCreateEditView(ActivoFijo instance, AbstractDialogController controller) {
        super(instance, DialogType.DEFINED, controller);
    }

    public ActivoFijoCreateEditView(ActivoFijo instance, AbstractDialogController controller, Dialog owner) {
        super(instance, DialogType.DEFINED, controller, owner);
        editingMode = true;
        initComponents();
        setEditingMode();
        updateView();
        setVisible(true);

    }

    public ActivoFijoCreateEditView(AbstractDialogController controller, Dialog owner) {
        super(null, DialogType.DEFINED, controller, owner);
        initComponents();
        setCreatingMode();
        updateView();
        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextFieldSerie = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxgrupo = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxUbicacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextFieldInicial = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextFieldResidual = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jSpinnerUtil = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabelDepreciACION = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabelUltDepreciacion = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabelVactual = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooserAlta = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jButtonAceptar = new components.buttons.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(538, 497));
        setMinimumSize(new java.awt.Dimension(538, 497));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(538, 497));
        setSize(new java.awt.Dimension(538, 497));
        getContentPane().setLayout(new java.awt.BorderLayout(0, 20));

        jPanel4.setBorder(new javax.swing.border.LineBorder(Colors.WINDOW_BORDER_COLOR, 3, true));
        jPanel4.setLayout(new java.awt.BorderLayout(0, 20));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Básico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel7.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("No. Serie");
        jPanel9.add(jLabel1);

        jFormattedTextFieldSerie.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldSerie.setEnabled(!editingMode);
        jFormattedTextFieldSerie.setPreferredSize(new java.awt.Dimension(80, 26));
        jPanel9.add(jFormattedTextFieldSerie);

        jLabel3.setText("Descripción");
        jPanel9.add(jLabel3);

        jTextFieldDescripcion.setToolTipText("");
        jTextFieldDescripcion.setEnabled(!editingMode);
        jTextFieldDescripcion.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel9.add(jTextFieldDescripcion);

        jPanel7.add(jPanel9);

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setText("Grupo");
        jPanel16.add(jLabel5);

        jComboBoxgrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MUEBLES", "ILUMINACION", "CRISTALERIA", "CUBERTERIA", "VAJILLAS", "EQUIPOS ELECTRICOS", "EQUIPOS DE COMPUTO", "EQUIPOS DE COMUNICACION", "EQUIPOS DE RED", "HERRAMIENTAS", "SEGURIDAD", "TRANSPORTE", "MAQUINARIA", "UTILES", "CONTRUCCIONES", "OTROS" }));
        jComboBoxgrupo.setToolTipText("Grupo");
        jComboBoxgrupo.setEnabled(!editingMode);
        jPanel16.add(jComboBoxgrupo);

        jPanel10.add(jPanel16);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setText("Ubicación");
        jPanel8.add(jLabel4);

        jComboBoxUbicacion.setEnabled(!editingMode);
        jComboBoxUbicacion.setPreferredSize(new java.awt.Dimension(150, 27));
        jComboBoxUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUbicacionActionPerformed(evt);
            }
        });
        jPanel8.add(jComboBoxUbicacion);

        jButton2.setText("Nueva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2);

        jPanel10.add(jPanel8);

        jPanel7.add(jPanel10);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel6.setText("Tipo");
        jPanel12.add(jLabel6);

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tangible", "Intangible" }));
        jComboBoxTipo.setEnabled(!editingMode);
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });
        jPanel12.add(jComboBoxTipo);

        jLabel7.setText("Estado");
        jPanel12.add(jLabel7);

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "POR INSTALAR", "ALMACEN", "REPARACION" }));
        jComboBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEstadoActionPerformed(evt);
            }
        });
        jPanel12.add(jComboBoxEstado);

        jPanel7.add(jPanel12);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel8.setText("V. inicial");
        jPanel13.add(jLabel8);

        jFormattedTextFieldInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextFieldInicial.setEnabled(!editingMode);
        jFormattedTextFieldInicial.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanel13.add(jFormattedTextFieldInicial);

        jLabel9.setText("V. residual");
        jPanel13.add(jLabel9);

        jFormattedTextFieldResidual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextFieldResidual.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanel13.add(jFormattedTextFieldResidual);

        jLabel10.setText("Vida Útil");
        jPanel13.add(jLabel10);

        jSpinnerUtil.setModel(new javax.swing.SpinnerNumberModel(1.0f, 0.0f, null, 1.0f));
        jSpinnerUtil.setPreferredSize(new java.awt.Dimension(50, 26));
        jPanel13.add(jSpinnerUtil);

        jLabel11.setText("Año(s)");
        jPanel13.add(jLabel11);

        jPanel7.add(jPanel13);

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setLayout(new java.awt.GridLayout(2, 2));

        jLabel12.setText("Depreciación:");
        jPanel6.add(jLabel12);

        jLabelDepreciACION.setForeground(new java.awt.Color(204, 0, 0));
        jLabelDepreciACION.setText("-");
        jPanel6.add(jLabelDepreciACION);

        jLabel16.setText("Ultima Depreciación");
        jPanel6.add(jLabel16);

        jLabelUltDepreciacion.setForeground(new java.awt.Color(204, 0, 0));
        jLabelUltDepreciacion.setText("-");
        jPanel6.add(jLabelUltDepreciacion);

        jPanel11.add(jPanel6);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jLabel13.setText("Valor Actual:");
        jPanel5.add(jLabel13);

        jLabelVactual.setForeground(new java.awt.Color(0, 153, 0));
        jLabelVactual.setText("-");
        jPanel5.add(jLabelVactual);

        jPanel11.add(jPanel5);

        jPanel7.add(jPanel11);

        jPanel1.add(jPanel7);

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, java.awt.BorderLayout.WEST);

        jLabel2.setText("Fecha Alta");
        jPanel14.add(jLabel2);

        jDateChooserAlta.setDate(new Date());
        jDateChooserAlta.setEnabled(!editingMode);
        jPanel14.add(jDateChooserAlta);

        jPanel2.add(jPanel14, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jButtonAceptar.setBackground(new java.awt.Color(51, 255, 255));
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonAceptar);

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        crearEditarActivo();
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jComboBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEstadoActionPerformed
    }//GEN-LAST:event_jComboBoxEstadoActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jComboBoxUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUbicacionActionPerformed
    }//GEN-LAST:event_jComboBoxUbicacionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jComboBoxUbicacion.addItem(getController().createNewUbicacion());
    }//GEN-LAST:event_jButton2ActionPerformed

    @Override
    public void setEditingMode() {
        jButtonAceptar.setText("Editar");
    }

    @Override
    public void setCreatingMode() {
        jButtonAceptar.setText("Crear");
    }

    @Override
    public boolean validateData() {
        return true;
    }

    @Override
    public void updateView() {
        getController().setView(this);
        if (editingMode) {
            jDateChooserAlta.setDate(instance.getFechaAlta());
            jFormattedTextFieldSerie.setText(instance.getNumeroActivo().toString());
            jFormattedTextFieldResidual.setText(instance.getValorResidual().toString());
            jTextFieldDescripcion.setText(instance.getDescripcion());
            jComboBoxgrupo.setSelectedItem(instance.getGrupo());
            jComboBoxUbicacion.setSelectedItem(instance.getUbicacion());
            jComboBoxTipo.setSelectedItem(instance.getTipoActivo());
            jComboBoxEstado.setSelectedItem(instance.getEstadoActivo());
            jFormattedTextFieldInicial.setText(instance.getValorInicial().toString());
            jSpinnerUtil.setValue((int)instance.getVidaUtil().floatValue());
            jLabelVactual.setText("" + utils.setDosLugaresDecimalesFloat(instance.getValorActual()));
            jLabelDepreciACION.setText(instance.getDepreciacion().toString());
        } else {

        }
        jComboBoxUbicacion.setModel(new RestManagerComboBoxModel<>(getController().getUbicacionesList()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private components.buttons.MaterialButton jButtonAceptar;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JComboBox<Ubicacion> jComboBoxUbicacion;
    private javax.swing.JComboBox<String> jComboBoxgrupo;
    private com.toedter.calendar.JDateChooser jDateChooserAlta;
    private javax.swing.JFormattedTextField jFormattedTextFieldInicial;
    private javax.swing.JFormattedTextField jFormattedTextFieldResidual;
    private javax.swing.JFormattedTextField jFormattedTextFieldSerie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDepreciACION;
    private javax.swing.JLabel jLabelUltDepreciacion;
    private javax.swing.JLabel jLabelVactual;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner jSpinnerUtil;
    private javax.swing.JTextField jTextFieldDescripcion;
    // End of variables declaration//GEN-END:variables

    private void crearEditarActivo() {

        if (!editingMode) {
            instance = new ActivoFijo();
            instance.setDepreciacionAcumulada((float) 0);
        }

        instance.setDescripcion(jTextFieldDescripcion.getText());
        instance.setEstadoActivo(jComboBoxEstado.getSelectedItem().toString());
        instance.setFechaAlta(jDateChooserAlta.getDate());
        instance.setGrupo(jComboBoxgrupo.getSelectedItem().toString());
        instance.setNumeroActivo(Integer.parseInt(jFormattedTextFieldSerie.getText()));
        instance.setTipoActivo(jComboBoxTipo.getSelectedItem().toString());
        instance.setUbicacion((Ubicacion) jComboBoxUbicacion.getSelectedItem());
        instance.setValorInicial(Float.parseFloat(jFormattedTextFieldInicial.getText()));
        instance.setValorResidual(Float.parseFloat(jFormattedTextFieldResidual.getText()));
        instance.setVidaUtil((Float) jSpinnerUtil.getValue());

        if (editingMode) {
            getController().update(instance);
        } else {
            getController().create(instance);
        }
        dispose();
    }

    @Override
    public ActivoFijoController getController() {
        return (ActivoFijoController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
