/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.productoventa;

import GUI.Components.JSpinner;
import GUI.Components.JTextField;
import GUI.Views.AbstractDetailView;
import java.awt.Dialog;
import java.awt.Frame;
import restManager.controller.AbstractController;
import restManager.controller.productoventa.ProductoVentaCreateEditController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Cocina;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.resources.R;
import restManager.util.RestManagerComboBoxModel;

/**
 *
 * @author Jorge
 */
public class ProductoVentaCreateEditView extends AbstractDetailView<ProductoVenta> {

    public ProductoVentaCreateEditView(ProductoVenta instance, AbstractController controller, Frame owner, boolean modal) {
        super(instance, DialogType.INPUT_LARGE, controller, owner, modal);
        initComponents();
        updateView();
    }

    public ProductoVentaCreateEditView(ProductoVenta instance, AbstractController controller, Dialog owner, boolean modal) {
        super(instance, DialogType.INPUT, controller, owner, modal);
        initComponents();
        updateView();
        fetchComponentData();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jXLabelPCod = new org.jdesktop.swingx.JXLabel();
        jPanel7 = new javax.swing.JPanel();
        jXLabelNombre = new org.jdesktop.swingx.JXLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jXLabelPrecio = new org.jdesktop.swingx.JXLabel();
        jSpinnerPrecio = new JSpinner();
        jXLabelMoneda = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jXLabelCocina = new org.jdesktop.swingx.JXLabel();
        jComboBoxCOCINA = new javax.swing.JComboBox<>();
        jXLabelSeccion = new org.jdesktop.swingx.JXLabel();
        jComboBoxSECCION = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jXLabelSeccion1 = new org.jdesktop.swingx.JXLabel();
        jXLabelSeccion2 = new org.jdesktop.swingx.JXLabel();
        jXLabelMoneda1 = new org.jdesktop.swingx.JXLabel();
        tabbedPaneRound1 = new org.edisoncor.gui.tabbedPane.TabbedPaneRound();
        TablaDeIngredientes = new javax.swing.JScrollPane();
        jTableListaIngredientes = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIngredientesElab = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonIngrediente = new javax.swing.JButton();
        jButtonCocina = new javax.swing.JButton();
        jButtonSeccion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonCrear = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        jXPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jXPanel1.setLayout(new javax.swing.BoxLayout(jXPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Básica"));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabelPCod.setText("P-Cod");
        jPanel8.add(jXLabelPCod);

        jPanel5.add(jPanel8);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jXLabelNombre.setText(bundle.getString("label_nombre")); // NOI18N
        jPanel7.add(jXLabelNombre);

        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel7.add(jTextFieldNombre);

        jXLabelPrecio.setText(bundle.getString("label_precio")); // NOI18N
        jPanel7.add(jXLabelPrecio);

        jSpinnerPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSpinnerPrecio.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanel7.add(jSpinnerPrecio);

        jXLabelMoneda.setText(R.coinSuffix);
        jXLabelMoneda.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel7.add(jXLabelMoneda);

        jPanel5.add(jPanel7);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

        jXLabelCocina.setText(bundle.getString("label_cocina")); // NOI18N
        jPanel2.add(jXLabelCocina);

        jComboBoxCOCINA.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanel2.add(jComboBoxCOCINA);

        jXLabelSeccion.setText(bundle.getString("label_seccion")); // NOI18N
        jPanel2.add(jXLabelSeccion);

        jComboBoxSECCION.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanel2.add(jComboBoxSECCION);

        jPanel5.add(jPanel2);

        jXPanel1.add(jPanel5);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        jXLabelSeccion1.setText(bundle.getString("label_costo")); // NOI18N
        jPanel6.add(jXLabelSeccion1);

        jXLabelSeccion2.setText(bundle.getString("label_seccion")); // NOI18N
        jPanel6.add(jXLabelSeccion2);

        jXLabelMoneda1.setText(R.coinSuffix);
        jXLabelMoneda1.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel6.add(jXLabelMoneda1);

        jPanel4.add(jPanel6);

        jTableListaIngredientes.setAutoCreateRowSorter(true);
        jTableListaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccionar", "Codigo", "Nombre", "U/M", "Cantidad", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaIngredientes.setColumnSelectionAllowed(true);
        jTableListaIngredientes.getTableHeader().setReorderingAllowed(false);
        jTableListaIngredientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableListaIngredientesFocusLost(evt);
            }
        });
        jTableListaIngredientes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableListaIngredientesPropertyChange(evt);
            }
        });
        TablaDeIngredientes.setViewportView(jTableListaIngredientes);

        tabbedPaneRound1.addTab("Insumos", TablaDeIngredientes);

        jTableIngredientesElab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccionar", "Codigo", "Nombre", "U/M", "Cantidad", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableIngredientesElab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableIngredientesElabPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableIngredientesElab);

        tabbedPaneRound1.addTab("Insumos Elaborados", jScrollPane1);

        jPanel4.add(tabbedPaneRound1);

        jXPanel1.add(jPanel4);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jButtonIngrediente.setText(bundle.getString("label_agregar_ingrediente")); // NOI18N
        jPanel3.add(jButtonIngrediente);

        jButtonCocina.setText(bundle.getString("label_agregar_cocina")); // NOI18N
        jPanel3.add(jButtonCocina);

        jButtonSeccion.setText(bundle.getString("label_agregar_seccion")); // NOI18N
        jPanel3.add(jButtonSeccion);

        jXPanel1.add(jPanel3);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 5));

        jButtonCrear.setText(bundle.getString("label_crear")); // NOI18N
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCrear);

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancelar);

        jXPanel1.add(jPanel1);

        getContentPane().add(jXPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaIngredientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableListaIngredientesFocusLost
        //        TableCellEditor tce = jTableListaIngredientes.getCellEditor();
        //        if (jTableListaIngredientes.getSelectedColumn() != 2) {
        //            if (tce != null) {
        //                tce.stopCellEditing();
        //            }
        //        }
    }//GEN-LAST:event_jTableListaIngredientesFocusLost

    private void jTableListaIngredientesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableListaIngredientesPropertyChange
        if (evt.getPropertyName().matches("tableCellEditor")) {
            if (jTableListaIngredientes.getEditingColumn() == 5 && evt.getNewValue() == null) {
                // ActCosto();
            }
        }
    }//GEN-LAST:event_jTableListaIngredientesPropertyChange

    private void jTableIngredientesElabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableIngredientesElabPropertyChange
        if (evt.getPropertyName().matches("tableCellEditor")) {
            if (jTableIngredientesElab.getEditingColumn() == 5 && evt.getNewValue() == null) {
                //ActCostoIngElab();
                // ActCosto();
            }
        }
    }//GEN-LAST:event_jTableIngredientesElabPropertyChange

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        getController().createUpdateInstance();
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    @Override
    public void setEditingMode() {
        jButtonCrear.setText(R.RESOURCE_BUNDLE.getString("label_editar"));
    }

    @Override
    public void setCreatingMode() {
        jButtonCrear.setText(R.RESOURCE_BUNDLE.getString("label_crear"));
    }

    @Override
    public boolean validateData() {
        
        
        
        
        
        
        return true;
    }

    @Override
    public void updateView() {
        this.jXLabelPCod.setText(instance.getPCod());
        this.jTextFieldNombre.setText(instance.getNombre());
        jSpinnerPrecio.setValue(instance.getPrecioVenta());
        jComboBoxCOCINA.setSelectedItem(instance.getCocinacodCocina());
        jComboBoxSECCION.setSelectedItem(instance.getSeccionnombreSeccion());
    }

    @Override
    public void fetchComponentData() {
        jComboBoxCOCINA.setModel(new RestManagerComboBoxModel<>(((ProductoVentaCreateEditController) getController()).getCocinaList()));
        jComboBoxSECCION.setModel(new RestManagerComboBoxModel<>(((ProductoVentaCreateEditController) getController()).getSeccionList()));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane TablaDeIngredientes;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCocina;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonIngrediente;
    private javax.swing.JButton jButtonSeccion;
    private javax.swing.JComboBox<Cocina> jComboBoxCOCINA;
    private javax.swing.JComboBox<Seccion> jComboBoxSECCION;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerPrecio;
    private javax.swing.JTable jTableIngredientesElab;
    private javax.swing.JTable jTableListaIngredientes;
    private javax.swing.JTextField jTextFieldNombre;
    private org.jdesktop.swingx.JXLabel jXLabelCocina;
    private org.jdesktop.swingx.JXLabel jXLabelMoneda;
    private org.jdesktop.swingx.JXLabel jXLabelMoneda1;
    private org.jdesktop.swingx.JXLabel jXLabelNombre;
    private org.jdesktop.swingx.JXLabel jXLabelPCod;
    private org.jdesktop.swingx.JXLabel jXLabelPrecio;
    private org.jdesktop.swingx.JXLabel jXLabelSeccion;
    private org.jdesktop.swingx.JXLabel jXLabelSeccion1;
    private org.jdesktop.swingx.JXLabel jXLabelSeccion2;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.edisoncor.gui.tabbedPane.TabbedPaneRound tabbedPaneRound1;
    // End of variables declaration//GEN-END:variables
}
