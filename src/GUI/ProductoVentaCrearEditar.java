/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import restManager.persistencia.*;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class ProductoVentaCrearEditar extends javax.swing.JDialog {

    /**
     * Creates new form ProductoVentaCrearEditar
     */
    //final String[] um = {"Lbs", "Kg", "Gr", "U", "Lts"};
    private final String codProductoVenta;
    private ProductoVenta prod;
    private List<Seccion> secciones;
    private List<Cocina> cocinas;
    private List<Insumo> insumos;
    private boolean nuevo = false;

    public ProductoVentaCrearEditar(java.awt.Dialog parent, boolean modal, ProductoVenta p) {
        super(parent, modal);
        initComponents();
        
        if (p == null) {
            nuevo = true;
            codProductoVenta = getCodProductoVenta();
            prod = new ProductoVenta(codProductoVenta);
            prod.setProductoInsumoList(new ArrayList<>());
            prod.setVisible(false);
            

        } else {
            codProductoVenta = p.getPCod();
            prod = p;
            buttonCrear.setText("Editar");
        }

        
        initDialog();
        updateDialog(prod);
        setVisible(true);

    }

    private void updateDialog(ProductoVenta p) {
        textFieldNOMBRE.setText("" + p.getNombre());
        textFieldPRECIO.setText("" + p.getPrecioVenta());
        try{
        jComboBoxCOCINA.setSelectedItem(p.getCocinacodCocina().getNombreCocina());
        jComboBoxSECCION.setSelectedItem(p.getSeccionnombreSeccion().getNombreSeccion());
        }catch(NullPointerException e){
         
        }

        for (ProductoInsumo i : p.getProductoInsumoList()) {
            boolean founded = false;
            if (!i.getInsumo().getElaborado()) {
                for (int j = 0; j < jTableListaIngredientes.getRowCount() && !founded; j++) {
                    if (jTableListaIngredientes.getValueAt(j, 1).equals(i.getInsumo().getCodInsumo())) {
                        jTableListaIngredientes.setValueAt(true, j, 0);
                        jTableListaIngredientes.setValueAt(i.getCantidad(), j, 4);
                        jTableListaIngredientes.setValueAt(i.getCosto(), j, 5);
                        founded = true;

                    }

                }
            } else {
                for (int j = 0; j < jTableIngredientesElab.getRowCount() && !founded; j++) {
                    if (jTableIngredientesElab.getValueAt(j, 1).equals(i.getInsumo().getCodInsumo())) {
                        jTableIngredientesElab.setValueAt(true, j, 0);
                        jTableIngredientesElab.setValueAt(i.getCantidad(), j, 4);
                        jTableIngredientesElab.setValueAt(i.getCosto(), j, 5);
                    }
                }
            }
        }
        ActCosto();

    }

    private void ActCosto() {
        float valor = 0;
        for (int i = 0; i < jTableListaIngredientes.getRowCount(); i++) {
            if ((Boolean) jTableListaIngredientes.getValueAt(i, 0)) {
                if (jTableListaIngredientes.getValueAt(i, 5) != null) {
                    valor += (float) jTableListaIngredientes.getValueAt(i, 5);
                }
            }
        }

        for (int i = 0; i < jTableIngredientesElab.getRowCount(); i++) {
            if ((Boolean) jTableIngredientesElab.getValueAt(i, 0)) {
                if ( jTableIngredientesElab.getValueAt(i, 5) != null) {
                    valor += (float) jTableIngredientesElab.getValueAt(i, 5);
                }
            }

        }
        labelValorCosto.setText("" + String.format("%.2f", valor) + Main.moneda);
        prod.setGasto(valor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        labelPCod = new org.edisoncor.gui.label.LabelMetric();
        jComboBoxTIPO = new javax.swing.JComboBox<>();
        labelTIPO = new org.edisoncor.gui.label.LabelMetric();
        labelNOMBRE = new org.edisoncor.gui.label.LabelMetric();
        textFieldNOMBRE = new org.edisoncor.gui.textField.TextFieldRectIcon();
        labelPRECIO = new org.edisoncor.gui.label.LabelMetric();
        labelCOCINA = new org.edisoncor.gui.label.LabelMetric();
        jComboBoxCOCINA = new javax.swing.JComboBox<>();
        jComboBoxSECCION = new javax.swing.JComboBox<>();
        labelSECCION = new org.edisoncor.gui.label.LabelMetric();
        labelLISTAINGR = new org.edisoncor.gui.label.LabelMetric();
        labelCosto = new org.edisoncor.gui.label.LabelMetric();
        labelValorCosto = new org.edisoncor.gui.label.LabelMetric();
        buttonADDIngrediente = new org.edisoncor.gui.button.ButtonTextDown();
        buttonADDSeccion = new org.edisoncor.gui.button.ButtonTextDown();
        buttonADDCocina = new org.edisoncor.gui.button.ButtonTextDown();
        buttonCancelar = new org.edisoncor.gui.button.ButtonTextDown();
        buttonCrear = new org.edisoncor.gui.button.ButtonTextDown();
        tabbedPaneRound1 = new org.edisoncor.gui.tabbedPane.TabbedPaneRound();
        TablaDeIngredientes = new javax.swing.JScrollPane();
        jTableListaIngredientes = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIngredientesElab = new javax.swing.JTable();
        textFieldPRECIO = new org.edisoncor.gui.textField.TextFieldRectIcon();
        labelMONEDA = new org.edisoncor.gui.label.LabelMetric();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Producto Venta");
        setMinimumSize(new java.awt.Dimension(605, 696));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        labelPCod.setText(codProductoVenta);

        jComboBoxTIPO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Producto de venta", "Ingrediente Elaborado" }));
        jComboBoxTIPO.setEnabled(false);

        labelTIPO.setText("Tipo:");

        labelNOMBRE.setText("Nombre: ");

        labelPRECIO.setText("Precio:");

        labelCOCINA.setText("Cocina:");

        jComboBoxCOCINA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nulo" }));

        jComboBoxSECCION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nulo" }));

        labelSECCION.setText("Sección:");

        labelLISTAINGR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLISTAINGR.setText("Lista Ingredientes");

        labelCosto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCosto.setText("Costo:");

        labelValorCosto.setForeground(new java.awt.Color(204, 51, 0));
        labelValorCosto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValorCosto.setText("0.0");
        labelValorCosto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        buttonADDIngrediente.setText("Agregar Ingrediente");
        buttonADDIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonADDIngredienteActionPerformed(evt);
            }
        });

        buttonADDSeccion.setText("Agregar Sección");
        buttonADDSeccion.setEnabled(false);

        buttonADDCocina.setText("Agregar Cocina");
        buttonADDCocina.setEnabled(false);

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        buttonCrear.setText("Crear");
        buttonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearActionPerformed(evt);
            }
        });

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
        jTableListaIngredientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

        labelMONEDA.setText(Main.moneda);

        javax.swing.GroupLayout panelRect1Layout = new javax.swing.GroupLayout(panelRect1);
        panelRect1.setLayout(panelRect1Layout);
        panelRect1Layout.setHorizontalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addComponent(labelSECCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxSECCION, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRect1Layout.createSequentialGroup()
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonADDIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(buttonADDCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonADDSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tabbedPaneRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelRect1Layout.createSequentialGroup()
                                    .addComponent(labelLISTAINGR, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(139, 139, 139)
                                    .addComponent(labelValorCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCOCINA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRect1Layout.createSequentialGroup()
                                .addComponent(labelTIPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxTIPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRect1Layout.createSequentialGroup()
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelRect1Layout.createSequentialGroup()
                                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxCOCINA, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(labelPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFieldPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelMONEDA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(45, 45, 45))))
        );
        panelRect1Layout.setVerticalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTIPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTIPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMONEDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCOCINA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCOCINA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSECCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSECCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLISTAINGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPaneRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonADDIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonADDSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonADDCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tabbedPaneRound1.getAccessibleContext().setAccessibleName("Insumos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRect1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRect1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        int r = JOptionPane.showConfirmDialog(this, "Los datos de la edición se perderán. "
                + "\n ¿Seguro que desea continuar? ");
        if (r == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void jTableListaIngredientesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableListaIngredientesPropertyChange
        if (evt.getPropertyName().matches("tableCellEditor")) {
            if (jTableListaIngredientes.getEditingColumn() == 5 && evt.getNewValue() == null) {
                ActCosto();
            }
        }
    }//GEN-LAST:event_jTableListaIngredientesPropertyChange

    private void jTableListaIngredientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableListaIngredientesFocusLost
//        TableCellEditor tce = jTableListaIngredientes.getCellEditor();
//        if (jTableListaIngredientes.getSelectedColumn() != 2) {
//            if (tce != null) {
//                tce.stopCellEditing();
//            }
//        }
    }//GEN-LAST:event_jTableListaIngredientesFocusLost

    private void jTableIngredientesElabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableIngredientesElabPropertyChange
        if (evt.getPropertyName().matches("tableCellEditor")) {
            if (jTableIngredientesElab.getEditingColumn() == 5 && evt.getNewValue() == null) {
                //ActCostoIngElab();
                ActCosto();
            }
        }
    }//GEN-LAST:event_jTableIngredientesElabPropertyChange

    private void buttonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearActionPerformed
        crearProductoVenta();
    }//GEN-LAST:event_buttonCrearActionPerformed

    private void buttonADDIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonADDIngredienteActionPerformed
        new InsumoLista(this).mostrarDialogParaAddInsumo();
    }//GEN-LAST:event_buttonADDIngredienteActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
    }//GEN-LAST:event_formWindowGainedFocus


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane TablaDeIngredientes;
    private org.edisoncor.gui.button.ButtonTextDown buttonADDCocina;
    private org.edisoncor.gui.button.ButtonTextDown buttonADDIngrediente;
    private org.edisoncor.gui.button.ButtonTextDown buttonADDSeccion;
    private org.edisoncor.gui.button.ButtonTextDown buttonCancelar;
    private org.edisoncor.gui.button.ButtonTextDown buttonCrear;
    private javax.swing.JComboBox<String> jComboBoxCOCINA;
    private javax.swing.JComboBox<String> jComboBoxSECCION;
    private javax.swing.JComboBox<String> jComboBoxTIPO;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableIngredientesElab;
    private javax.swing.JTable jTableListaIngredientes;
    private org.edisoncor.gui.label.LabelMetric labelCOCINA;
    private org.edisoncor.gui.label.LabelMetric labelCosto;
    private org.edisoncor.gui.label.LabelMetric labelLISTAINGR;
    private org.edisoncor.gui.label.LabelMetric labelMONEDA;
    private org.edisoncor.gui.label.LabelMetric labelNOMBRE;
    private org.edisoncor.gui.label.LabelMetric labelPCod;
    private org.edisoncor.gui.label.LabelMetric labelPRECIO;
    private org.edisoncor.gui.label.LabelMetric labelSECCION;
    private org.edisoncor.gui.label.LabelMetric labelTIPO;
    private org.edisoncor.gui.label.LabelMetric labelValorCosto;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    private org.edisoncor.gui.tabbedPane.TabbedPaneRound tabbedPaneRound1;
    private org.edisoncor.gui.textField.TextFieldRectIcon textFieldNOMBRE;
    private org.edisoncor.gui.textField.TextFieldRectIcon textFieldPRECIO;
    // End of variables declaration//GEN-END:variables

    private void initDialog() {
        this.labelPCod.setText(codProductoVenta);
        fillCocinaComboBox();
        fillSeccionesComboBox();
        fillTablaInsumos();
    }

    private void fillCocinaComboBox() {
        cocinas = staticContent.cocinaJPA.findCocinaEntities();
        Collections.sort(cocinas, (o1, o2) -> {
            return o1.getNombreCocina().compareTo(o2.getNombreCocina()); //To change body of generated lambdas, choose Tools | Templates.
        });
        for (Cocina x : cocinas) {
            jComboBoxCOCINA.addItem(x.getNombreCocina());
        }
    }

    private void fillSeccionesComboBox() {
        secciones = staticContent.seccionJPA.findSeccionEntities();
        Collections.sort(secciones, (o1, o2) -> {
            return o1.getNombreSeccion().compareTo(o2.getNombreSeccion()); //To change body of generated lambdas, choose Tools | Templates.
        });
        for (Seccion x : secciones) {
            jComboBoxSECCION.addItem(x.getNombreSeccion());
        }
    }

    private void fillTablaInsumos() {
        //inicializando data
        insumos = staticContent.insumoJPA.findInsumoEntities();
        ArrayList[] rowData = comun.initArray(new ArrayList[6]);
        ArrayList[] rowDataElaborado = comun.initArray(new ArrayList[6]);

        //convirtiendo a rowdata
        for (Insumo x : insumos) {
            if (!x.getElaborado()) {
                rowData[0].add(false);
                rowData[1].add(x.getCodInsumo());
                rowData[2].add(x.getNombre());
                rowData[3].add(x.getUm());
                rowData[4].add(0);
                rowData[5].add(0);
            } else {
                rowDataElaborado[0].add(false);
                rowDataElaborado[1].add(x.getCodInsumo());
                rowDataElaborado[2].add(x.getNombre());
                rowDataElaborado[3].add(x.getUm());
                rowDataElaborado[4].add(0);
                rowDataElaborado[5].add(0);
            }
        }

        //llenando la tabla
        try {
            //comun.limpiarTabla(jTableListaIngredientes);
            comun.AddToTable(rowData, jTableListaIngredientes);
            comun.AddToTable(rowDataElaborado, jTableIngredientesElab);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void crearProductoVenta() {
        try{
        int r = JOptionPane.showConfirmDialog(this, "Seguro que desea crear el producto de venta");
        
        if (r == JOptionPane.YES_OPTION) {
            //seteando nombre, precio y costo
            prod.setNombre(textFieldNOMBRE.getText());
            prod.setPrecioVenta(Float.parseFloat(textFieldPRECIO.getText()));
            prod.setGanancia(prod.getPrecioVenta() - prod.getGasto());

            //seteando cocina
            Cocina cocina = null;
            if(jComboBoxCOCINA.getSelectedIndex() == 0){
                throw new IllegalArgumentException("La cocina selecionada no puede ser la nula");
            }
            for (Cocina c : cocinas) {
                if (c.getNombreCocina().equals(jComboBoxCOCINA.getSelectedItem())) {
                    cocina = c;
                    break;
                }
            }
            prod.setCocinacodCocina(cocina);

            //seteando seccion
            
            Seccion seccion = null;
            if(jComboBoxSECCION.getSelectedIndex() == 0){
                throw new IllegalArgumentException("La seccion seleccionada no puede ser la nula");
            }
            for (Seccion s : secciones) {
                if (s.getNombreSeccion().equals(jComboBoxSECCION.getSelectedItem())) {
                    seccion = s;
                    break;
                }
            }
            prod.setSeccionnombreSeccion(seccion);

            //seteando insumos
            
            List<ProductoInsumo> productosInsumosAux = new ArrayList<>();

            for (int i = 0; i < jTableListaIngredientes.getRowCount(); i++) {
                if ((boolean) jTableListaIngredientes.getValueAt(i, 0)) {
                    String cod = (String) jTableListaIngredientes.getValueAt(i, 1);
                    float cantidad = (float) jTableListaIngredientes.getValueAt(i, 4);
                    float costo = (float) jTableListaIngredientes.getValueAt(i, 5);
                    ProductoInsumoPK piPK = new ProductoInsumoPK(codProductoVenta, cod);
                    ProductoInsumo pi = new ProductoInsumo(piPK);
                    pi.setCantidad(cantidad);
                    pi.setCosto(costo);
                    pi.setProductoVenta(prod);
                    pi.setInsumo(staticContent.insumoJPA.findInsumo(cod));
                    productosInsumosAux.add(pi);
                }
            }
            for (int i = 0; i < jTableIngredientesElab.getRowCount(); i++) {
                if ((boolean) jTableIngredientesElab.getValueAt(i, 0)) {
                    String cod = (String) jTableIngredientesElab.getValueAt(i, 1);
                    float cantidad = (float) jTableIngredientesElab.getValueAt(i, 4);
                    float costo = (float) jTableIngredientesElab.getValueAt(i, 5);
                    ProductoInsumoPK piPK = new ProductoInsumoPK(codProductoVenta, cod);
                    ProductoInsumo pi = new ProductoInsumo(piPK);
                    pi.setCantidad(cantidad);
                    pi.setCosto(costo);
                    pi.setProductoVenta(prod);
                    pi.setInsumo(staticContent.insumoJPA.findInsumo(cod));
                    productosInsumosAux.add(pi);
                }
            }
            
            //eliminando insumos que anteriormente tenia el producto de venta
            
            if(!nuevo){
                for (ProductoInsumo x : prod.getProductoInsumoList()) {
                    try {
                        staticContent.productoInsumo.destroy(x.getProductoInsumoPK());
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(ProductoVentaCrearEditar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                
            prod.getProductoInsumoList().clear();
            
            //persistiendo la instancia
            
                if (nuevo) {
                    staticContent.productoJPA.create(prod);
                } else {
                    staticContent.productoJPA.edit(prod);

                }
                for (ProductoInsumo x : productosInsumosAux) {
                    staticContent.productoInsumo.create(x);
                }
                prod.setProductoInsumoList(productosInsumosAux);
                staticContent.productoJPA.edit(prod);
                
                dispose();
        }
        }catch (Exception ex) {
                Logger.getLogger(ProductoVentaCrearEditar.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
        }

    private String getCodProductoVenta() {
    String retCode = "Pl-";
    int cont = 1;
    ProductoVenta pv = staticContent.productoJPA.findProductoVenta(retCode+""+cont);
    while(pv != null){
        cont++;
        pv = staticContent.productoJPA.findProductoVenta(retCode+ "" +cont);
    }
    
    return retCode + "" + cont;
    }

    }


