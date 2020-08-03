/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo;

import com.jobits.pos.ui.AbstractDetailView;
import com.jobits.pos.ui.utils.OldAbstractCrossReferenePanel;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JSpinner;

import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.insumo.InsumoCreateEditController;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.domain.models.InsumoElaboradoPK;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoInsumoPK;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.RestManagerAbstractTableModel;
import com.jobits.ui.components.MaterialComponentsFactory;

/**
 *
 * @author Jorge
 */
public class InsumoCreateEditView extends AbstractDetailView<Insumo> {

    private OldAbstractCrossReferenePanel<InsumoElaborado, Insumo> tableIngElab;
    private OldAbstractCrossReferenePanel<ProductoInsumo, ProductoVenta> tableCrossReference;

    public InsumoCreateEditView(AbstractDialogController controller, Frame owner, boolean modal, Insumo ins) {
        super(ins, DialogType.DEFINED, controller, owner, modal);
        initComponents();
        hideCrossReferenceDialog();
        setElaboradoTable();

    }

    public InsumoCreateEditView(AbstractDialogController controller, Dialog owner, boolean modal, Insumo ins) {
        super(ins, DialogType.DEFINED, controller, owner, modal);
        initComponents();
        hideCrossReferenceDialog();
        setElaboradoTable();
    }

    /**
     * TODO: arreglar este mojon
     */
    @Override
    public void updateView() {
        updateTable(getInstance().getInsumoDerivadoList());
        updateComboBoxes();
        updatePanelInputs();
    }

    @Override
    public InsumoCreateEditController getController() {
        return (InsumoCreateEditController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateTable(List<InsumoElaborado> items) {
        jSpinnerCantidadCreada.setEnabled(false);

        tableIngElab = new OldAbstractCrossReferenePanel<InsumoElaborado, Insumo>("Ingredientes", getController().getItems()) {
            @Override
            public RestManagerAbstractTableModel<InsumoElaborado> getTableModel() {
                return new RestManagerAbstractTableModel<InsumoElaborado>(items, getjTableCrossReference()) {

                    @Override
                    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 3:
                                items.get(rowIndex).setCantidad((float) aValue);
                                break;
                            case 4:
                                items.get(rowIndex).setCosto((float) aValue);
                                updateLabelCost();
                                break;
                        }
                        fireTableCellUpdated(rowIndex, columnIndex);

                    }

                    @Override
                    public int getColumnCount() {
                        return 5;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 0:
                                return items.get(rowIndex).getInsumo_derivado_nombre().getCodInsumo();
                            case 1:
                                return items.get(rowIndex).getInsumo_derivado_nombre().getNombre();
                            case 2:
                                return items.get(rowIndex).getInsumo_derivado_nombre().getUm();
                            case 3:
                                return items.get(rowIndex).getCantidad();
                            case 4:
                                return items.get(rowIndex).getCosto();
                            default:
                                return null;

                        }
                    }

                    @Override
                    public String getColumnName(int column) {
                        switch (column) {
                            case 0:
                                return "Codigo";
                            case 1:
                                return "Nombre";
                            case 2:
                                return "UM";
                            case 3:
                                return "Cantidad";
                            case 4:
                                return "Costo";
                            default:
                                return null;
                        }
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        switch (columnIndex) {
                            case 0:
                                return String.class;
                            case 1:
                                return String.class;
                            case 2:
                                return String.class;
                            case 3:
                                return Float.class;
                            case 4:
                                return Float.class;
                            default:
                                return null;
                        }
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnIndex == 3 ? true : super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
                    }

                };
            }

            @Override
            public InsumoElaborado transformK_T(Insumo selected) {
                InsumoElaborado newInsumo = new InsumoElaborado();
                InsumoElaboradoPK pk = new InsumoElaboradoPK(instance.getCodInsumo(), selected.getCodInsumo());
                newInsumo.setInsumoElaboradoPK(pk);
                newInsumo.setInsumo(instance);
                newInsumo.setInsumo_derivado_nombre(selected);
                newInsumo.setCantidad(0);
                newInsumo.setCosto(0);
                return newInsumo;
            }
        };
        jPanelIngElab.add(tableIngElab);

        tableCrossReference = new OldAbstractCrossReferenePanel< ProductoInsumo, ProductoVenta>("Productos de Venta", getController().getProductoList()) {
            @Override
            public RestManagerAbstractTableModel<ProductoInsumo> getTableModel() {
                return new RestManagerAbstractTableModel<ProductoInsumo>(instance.getProductoInsumoList(), getjTableCrossReference()) {
                    @Override
                    public int getColumnCount() {
                        return 4;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 0:
                                return items.get(rowIndex).getProductoVenta().getCodigoProducto();
                            case 1:
                                return items.get(rowIndex).getProductoVenta().getNombre();
                            case 2:
                                return items.get(rowIndex).getInsumo().getUm();
                            case 3:
                                return items.get(rowIndex).getCantidad();
                            default:
                                return null;
                        }
                    }

                    @Override
                    public String getColumnName(int column) {
                        switch (column) {
                            case 0:
                                return "Código";
                            case 1:
                                return "Nombre";
                            case 2:
                                return "U/M";
                            case 3:
                                return "Cantidad";
                            default:
                                return null;
                        }
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnIndex == 3;
                    }

                    @Override
                    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 3:
                                items.get(rowIndex).setCantidad(Float.parseFloat((String) aValue));

                                fireTableCellUpdated(rowIndex, columnIndex);
                                break;

                        }

                    }
                };
            }

            @Override
            public ProductoInsumo transformK_T(ProductoVenta selected) {
                ProductoInsumo ret = new ProductoInsumo();
                ProductoInsumoPK pInsPK = new ProductoInsumoPK(selected.getCodigoProducto(), instance.getCodInsumo());
                ret.setProductoInsumoPK(pInsPK);
                ret.setProductoVenta(selected);
                ret.setInsumo(instance);
                ret.setCantidad(1);
                ret.setCosto(instance.getCostoPorUnidad());
                return ret;
            }
        };
        jPanelCrossReference.add(tableCrossReference);
    }

    private void updatePanelInputs() {

        jTextFieldNombre.setText(instance.getNombre());
        jSpinnerCosto.setValue(instance.getCostoPorUnidad());
        jSpinnerEstimacionStock.setValue(instance.getStockEstimation());
        jComboBoxUM.setSelectedItem(R.UM.valueOf(instance.getUm()));
        if (instance.getElaborado()) {
            jToggleButtonElaborado.setSelected(true);
            jPanelCrossReference.setEnabled(true);
            jSpinnerCantidadCreada.setValue(instance.getCantidadCreada());
        }

    }

    private void updateComboBoxes() {
        for (R.UM x : R.UM.values()) {
            jComboBoxUM.addItem(x);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelInputs = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jLabelNombre = MaterialComponentsFactory.Displayers.getLabel();
        jTextFieldNombre = new GUI.Components.JTextField();
        jLabelUM = MaterialComponentsFactory.Displayers.getLabel();
        jComboBoxUM = MaterialComponentsFactory.Displayers.getComboBox();
        jLabelCostoU = MaterialComponentsFactory.Displayers.getLabel();
        jSpinnerCosto = new javax.swing.JSpinner();
        jLabelNombre4 = MaterialComponentsFactory.Displayers.getLabel();
        jSpinnerEstimacionStock = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jPanelTabla = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jLabelNombreCantCreada = MaterialComponentsFactory.Displayers.getLabel();
        jSpinnerCantidadCreada = new JSpinner();
        jLabelNombreCosto = MaterialComponentsFactory.Displayers.getLabel();
        jLabelNombreValorCosto = MaterialComponentsFactory.Displayers.getLabel();
        jToggleButtonCrossReference = new javax.swing.JToggleButton();
        jToggleButtonElaborado = new javax.swing.JToggleButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelCrossReference = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelIngElab = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelControles = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonAdd = MaterialComponentsFactory.Buttons.getAcceptButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        setTitle(bundle.getString("label_crear_insumo")); // NOI18N
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(590, 400));
        setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Insumo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 26))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(502, 900));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanelInputs.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20));
        jPanelInputs.setForeground(new java.awt.Color(255, 255, 255));
        jPanelInputs.setLayout(new java.awt.GridLayout(4, 2, 150, 10));

        jLabelNombre.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNombre.setText(bundle.getString("label_nombre")); // NOI18N
        jLabelNombre.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelNombre.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jLabelNombre);

        jTextFieldNombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldNombre.setBorder(null);
        jTextFieldNombre.setMaximumSize(new java.awt.Dimension(120, 16));
        jTextFieldNombre.setMinimumSize(new java.awt.Dimension(24, 20));
        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jTextFieldNombre);

        jLabelUM.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUM.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelUM.setText(bundle.getString("label_um")); // NOI18N
        jLabelUM.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelUM.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jLabelUM);

        jComboBoxUM.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBoxUM.setMinimumSize(new java.awt.Dimension(24, 20));
        jComboBoxUM.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jComboBoxUM);

        jLabelCostoU.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCostoU.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelCostoU.setText(bundle.getString("label_costo_unidad")); // NOI18N
        jLabelCostoU.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelCostoU.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jLabelCostoU);

        jSpinnerCosto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jSpinnerCosto.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jSpinnerCosto.setMinimumSize(new java.awt.Dimension(24, 20));
        jSpinnerCosto.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jSpinnerCosto);

        jLabelNombre4.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNombre4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNombre4.setText(bundle.getString("label_est_stock")); // NOI18N
        jLabelNombre4.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelNombre4.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jLabelNombre4);

        jSpinnerEstimacionStock.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jSpinnerEstimacionStock.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jSpinnerEstimacionStock.setMinimumSize(new java.awt.Dimension(24, 20));
        jSpinnerEstimacionStock.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanelInputs.add(jSpinnerEstimacionStock);

        jPanel2.add(jPanelInputs, java.awt.BorderLayout.NORTH);
        jPanel2.add(filler1, java.awt.BorderLayout.LINE_END);

        jPanelTabla.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelTabla.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTabla.setPreferredSize(new java.awt.Dimension(456, 2));
        jPanelTabla.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 1, 20));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(454, 0));
        jPanel1.setLayout(new java.awt.GridLayout(3, 2, 150, 10));

        jLabelNombreCantCreada.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNombreCantCreada.setText(bundle.getString("label_cantidad_creada")); // NOI18N
        jLabelNombreCantCreada.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelNombreCantCreada.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanel1.add(jLabelNombreCantCreada);

        jSpinnerCantidadCreada.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jSpinnerCantidadCreada.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jSpinnerCantidadCreada.setMinimumSize(new java.awt.Dimension(24, 20));
        jSpinnerCantidadCreada.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanel1.add(jSpinnerCantidadCreada);

        jLabelNombreCosto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNombreCosto.setText(bundle.getString("label_costo")); // NOI18N
        jLabelNombreCosto.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelNombreCosto.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanel1.add(jLabelNombreCosto);

        jLabelNombreValorCosto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabelNombreValorCosto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelNombreValorCosto.setText(bundle.getString("label_lista_ingredientes")); // NOI18N
        jLabelNombreValorCosto.setMinimumSize(new java.awt.Dimension(24, 20));
        jLabelNombreValorCosto.setPreferredSize(new java.awt.Dimension(0, 26));
        jPanel1.add(jLabelNombreValorCosto);

        jToggleButtonCrossReference.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleButtonCrossReference.setText("Usos");
        jToggleButtonCrossReference.setMinimumSize(new java.awt.Dimension(24, 20));
        jToggleButtonCrossReference.setPreferredSize(new java.awt.Dimension(0, 26));
        jToggleButtonCrossReference.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButtonCrossReferenceStateChanged(evt);
            }
        });
        jToggleButtonCrossReference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCrossReferenceActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonCrossReference);

        jToggleButtonElaborado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleButtonElaborado.setText("Derivados");
        jToggleButtonElaborado.setMinimumSize(new java.awt.Dimension(24, 20));
        jToggleButtonElaborado.setPreferredSize(new java.awt.Dimension(0, 26));
        jToggleButtonElaborado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButtonElaboradoStateChanged(evt);
            }
        });
        jToggleButtonElaborado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonElaboradoActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonElaborado);

        jPanelTabla.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(454, 300));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(454, 404));
        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        jPanelCrossReference.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCrossReference.setForeground(new java.awt.Color(255, 255, 255));
        jPanelCrossReference.setMinimumSize(new java.awt.Dimension(454, 0));
        jPanelCrossReference.setPreferredSize(new java.awt.Dimension(454, 40));
        jPanelCrossReference.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(jPanelCrossReference);

        jPanelIngElab.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIngElab.setForeground(new java.awt.Color(255, 255, 255));
        jPanelIngElab.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(jPanelIngElab);

        jPanelTabla.add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanelTabla, java.awt.BorderLayout.CENTER);

        jPanelControles.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButtonCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonCancelar);

        jButtonAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonAdd.setText(bundle.getString("label_crear")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonAdd);

        jPanel2.add(jPanelControles, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        getController().createUpdateInstance();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jToggleButtonCrossReferenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCrossReferenceActionPerformed

    }//GEN-LAST:event_jToggleButtonCrossReferenceActionPerformed

    private void jToggleButtonCrossReferenceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButtonCrossReferenceStateChanged
        if (jToggleButtonCrossReference.isSelected()) {
            showCrossReferenceDialog(instance.getProductoInsumoList());
        } else {
            hideCrossReferenceDialog();

        }
        setLocationRelativeTo(null);
    }//GEN-LAST:event_jToggleButtonCrossReferenceStateChanged

    private void jToggleButtonElaboradoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButtonElaboradoStateChanged
        instance.setElaborado(jToggleButtonElaborado.isSelected());
        setElaboradoTable();

        if (jToggleButtonElaborado.isSelected()) {
            jSpinnerCantidadCreada.setEnabled(true);
        } else {
            jSpinnerCantidadCreada.setEnabled(false);

        }

    }//GEN-LAST:event_jToggleButtonElaboradoStateChanged

    private void jToggleButtonElaboradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonElaboradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonElaboradoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox<R.UM> jComboBoxUM;
    private javax.swing.JLabel jLabelCostoU;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombre4;
    private javax.swing.JLabel jLabelNombreCantCreada;
    private javax.swing.JLabel jLabelNombreCosto;
    private javax.swing.JLabel jLabelNombreValorCosto;
    private javax.swing.JLabel jLabelUM;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelCrossReference;
    private javax.swing.JPanel jPanelIngElab;
    private javax.swing.JPanel jPanelInputs;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JSpinner jSpinnerCantidadCreada;
    private javax.swing.JSpinner jSpinnerCosto;
    private javax.swing.JSpinner jSpinnerEstimacionStock;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JToggleButton jToggleButtonCrossReference;
    private javax.swing.JToggleButton jToggleButtonElaborado;
    // End of variables declaration//GEN-END:variables

    public void showCrossReferenceDialog(List<ProductoInsumo> insList) {
        jPanelCrossReference.setVisible(true);
        jLayeredPane1.moveToFront(jPanelCrossReference);
        setSize(getWidth(), 680);
    }

    public void hideCrossReferenceDialog() {
        jPanelCrossReference.setVisible(false);
        jLayeredPane1.moveToBack(jPanelCrossReference);
        if (!jToggleButtonElaborado.isSelected()) {
            setSize(getMinimumSize());
        }

    }

    public void setElaboradoTable() {
        if (instance.getElaborado()) {
            jToggleButtonElaborado.setSelected(true);
        }

        jPanelIngElab.setVisible(jToggleButtonElaborado.isSelected());
        if (jToggleButtonElaborado.isSelected()) {
            setSize(getWidth(), 680);
            jLayeredPane1.moveToFront(jPanelIngElab);
        } else {
            if (!jToggleButtonCrossReference.isSelected()) {
                setSize(getMinimumSize());
            }
            jLayeredPane1.moveToBack(jPanelIngElab);
        }
        jSpinnerCosto.setEnabled(!jToggleButtonElaborado.isSelected());

    }

    @Override
    public void setEditingMode() {
        jButtonAdd.setText(R.RESOURCE_BUNDLE.getString("label_editar"));
    }

    @Override
    public void setCreatingMode() {
        jButtonAdd.setText(R.RESOURCE_BUNDLE.getString("label_crear"));
    }

    @Override
    public boolean validateData() {
        if (!jTextFieldNombre.getInputVerifier().verify(jTextFieldNombre)) {
            return false;
        }
        instance.setCantidadCreada((Float) jSpinnerCantidadCreada.getValue());
        instance.setCostoPorUnidad((Float) jSpinnerCosto.getValue());
        instance.setElaborado(jToggleButtonElaborado.isSelected());
        instance.setNombre(jTextFieldNombre.getText());
        instance.setUm(jComboBoxUM.getSelectedItem().toString());
        instance.setProductoInsumoList(tableCrossReference.getHandler().getTableModel().getItems());
        instance.setInsumoDerivadoList(instance.getInsumoDerivadoList());
        instance.setIpvList(instance.getIpvList());
        instance.setStockEstimation(Float.parseFloat(jSpinnerEstimacionStock.getValue().toString()));

        if (instance.getElaborado()) {
            instance.setInsumoDerivadoList(tableIngElab.getHandler().getTableModel().getItems());
        } else {
            instance.setInsumoDerivadoList(new ArrayList<>());
        }
        return true;
    }

    public void updateLabelCost() {
        float total = 0;
        total = tableIngElab.getHandler().getTableModel().getItems().stream().map((x) -> x.getCosto()).reduce(total, (accumulator, _item) -> accumulator + _item);
        jLabelNombreValorCosto.setText(R.formatoMoneda.format(total));
    }
}
