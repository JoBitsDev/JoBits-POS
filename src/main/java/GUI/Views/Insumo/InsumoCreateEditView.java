/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Insumo;

import GUI.Views.AbstractDetailView;
import GUI.Views.util.AbstractCrossReferenePanel;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JSpinner;

import restManager.controller.AbstractDialogController;
import restManager.controller.insumo.InsumoCreateEditController;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoElaborado;
import restManager.persistencia.InsumoElaboradoPK;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoInsumoPK;
import restManager.persistencia.ProductoVenta;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;

/**
 *
 * @author Jorge
 */
public class InsumoCreateEditView extends AbstractDetailView<Insumo> {

    private AbstractCrossReferenePanel<InsumoElaborado, Insumo> tableIngElab;
    private AbstractCrossReferenePanel<ProductoInsumo, ProductoVenta> tableCrossReference;

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
        tableIngElab = new AbstractCrossReferenePanel<InsumoElaborado, Insumo>("Ingredientes", getController().getItems()) {
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

        tableCrossReference = new AbstractCrossReferenePanel< ProductoInsumo, ProductoVenta>("Productos de Venta", getController().getProductoList()) {
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
            jCheckBoxElaborado.setSelected(true);
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

        jXPanelInputs = new org.jdesktop.swingx.JXPanel();
        jXLabelNombre = new org.jdesktop.swingx.JXLabel();
        jTextFieldNombre = new GUI.Components.JTextField();
        jXLabelUM = new org.jdesktop.swingx.JXLabel();
        jComboBoxUM = new javax.swing.JComboBox<>();
        jXLabelCostoU = new org.jdesktop.swingx.JXLabel();
        jSpinnerCosto = new javax.swing.JSpinner();
        jXLabelNombre4 = new org.jdesktop.swingx.JXLabel();
        jSpinnerEstimacionStock = new javax.swing.JSpinner();
        jXPanelTabla = new org.jdesktop.swingx.JXPanel();
        jPanel1 = new javax.swing.JPanel();
        jCheckBoxElaborado = new javax.swing.JCheckBox();
        jXLabelNombreCantCreada = new org.jdesktop.swingx.JXLabel();
        jSpinnerCantidadCreada = new JSpinner();
        jXLabelNombreCosto = new org.jdesktop.swingx.JXLabel();
        jXLabelNombreValorCosto = new org.jdesktop.swingx.JXLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelCrossReference = new javax.swing.JPanel();
        jPanelIngElab = new javax.swing.JPanel();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jButtonAdd = new javax.swing.JButton();
        jToggleButtonCrossReference = new javax.swing.JToggleButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        setTitle(bundle.getString("label_crear_insumo")); // NOI18N
        setMinimumSize(new java.awt.Dimension(590, 242));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(590, 242));
        getContentPane().setLayout(new java.awt.BorderLayout(0, 5));

        jXPanelInputs.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelInputs.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jXPanelInputs.setForeground(new java.awt.Color(204, 255, 255));
        jXPanelInputs.setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        jXLabelNombre.setText(bundle.getString("label_nombre")); // NOI18N
        jXPanelInputs.add(jXLabelNombre);

        jTextFieldNombre.setBorder(null);
        jTextFieldNombre.setMaximumSize(new java.awt.Dimension(120, 16));
        jTextFieldNombre.setMinimumSize(new java.awt.Dimension(120, 16));
        jXPanelInputs.add(jTextFieldNombre);

        jXLabelUM.setText(bundle.getString("label_um")); // NOI18N
        jXPanelInputs.add(jXLabelUM);

        jXPanelInputs.add(jComboBoxUM);

        jXLabelCostoU.setText(bundle.getString("label_costo_unidad")); // NOI18N
        jXPanelInputs.add(jXLabelCostoU);

        jSpinnerCosto.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jXPanelInputs.add(jSpinnerCosto);

        jXLabelNombre4.setText(bundle.getString("label_est_stock")); // NOI18N
        jXPanelInputs.add(jXLabelNombre4);

        jSpinnerEstimacionStock.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jXPanelInputs.add(jSpinnerEstimacionStock);

        getContentPane().add(jXPanelInputs, java.awt.BorderLayout.PAGE_START);

        jXPanelTabla.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(454, 0));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jCheckBoxElaborado.setText(bundle.getString("label_derivados")); // NOI18N
        jCheckBoxElaborado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxElaboradoStateChanged(evt);
            }
        });
        jCheckBoxElaborado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxElaboradoActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBoxElaborado);

        jXLabelNombreCantCreada.setText(bundle.getString("label_cantidad_creada")); // NOI18N
        jPanel1.add(jXLabelNombreCantCreada);

        jSpinnerCantidadCreada.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jSpinnerCantidadCreada.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel1.add(jSpinnerCantidadCreada);

        jXLabelNombreCosto.setText(bundle.getString("label_costo")); // NOI18N
        jPanel1.add(jXLabelNombreCosto);

        jXLabelNombreValorCosto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabelNombreValorCosto.setText(bundle.getString("label_lista_ingredientes")); // NOI18N
        jPanel1.add(jXLabelNombreValorCosto);

        jXPanelTabla.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(454, 404));
        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        jPanelCrossReference.setMinimumSize(new java.awt.Dimension(454, 0));
        jPanelCrossReference.setPreferredSize(new java.awt.Dimension(454, 40));
        jPanelCrossReference.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(jPanelCrossReference);

        jPanelIngElab.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(jPanelIngElab);

        jXPanelTabla.add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jXPanelTabla, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelControles.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());

        jButtonAdd.setText(bundle.getString("label_crear")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonAdd);

        jToggleButtonCrossReference.setText(bundle.getString("label_ver_uso_de_insumo_en_platos")); // NOI18N
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
        jXPanelControles.add(jToggleButtonCrossReference);

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonCancelar);

        getContentPane().add(jXPanelControles, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        getController().createUpdateInstance();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jCheckBoxElaboradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxElaboradoActionPerformed

    }//GEN-LAST:event_jCheckBoxElaboradoActionPerformed

    private void jCheckBoxElaboradoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxElaboradoStateChanged
        instance.setElaborado(jCheckBoxElaborado.isSelected());
        setElaboradoTable();
    }//GEN-LAST:event_jCheckBoxElaboradoStateChanged

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
    }//GEN-LAST:event_jToggleButtonCrossReferenceStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JCheckBox jCheckBoxElaborado;
    private javax.swing.JComboBox<R.UM> jComboBoxUM;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCrossReference;
    private javax.swing.JPanel jPanelIngElab;
    private javax.swing.JSpinner jSpinnerCantidadCreada;
    private javax.swing.JSpinner jSpinnerCosto;
    private javax.swing.JSpinner jSpinnerEstimacionStock;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JToggleButton jToggleButtonCrossReference;
    private org.jdesktop.swingx.JXLabel jXLabelCostoU;
    private org.jdesktop.swingx.JXLabel jXLabelNombre;
    private org.jdesktop.swingx.JXLabel jXLabelNombre4;
    private org.jdesktop.swingx.JXLabel jXLabelNombreCantCreada;
    private org.jdesktop.swingx.JXLabel jXLabelNombreCosto;
    private org.jdesktop.swingx.JXLabel jXLabelNombreValorCosto;
    private org.jdesktop.swingx.JXLabel jXLabelUM;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    private org.jdesktop.swingx.JXPanel jXPanelInputs;
    private org.jdesktop.swingx.JXPanel jXPanelTabla;
    // End of variables declaration//GEN-END:variables

    public void showCrossReferenceDialog(List<ProductoInsumo> insList) {
        jPanelCrossReference.setVisible(true);
        jLayeredPane1.moveToFront(jPanelCrossReference);
        setSize(getWidth(), 610);
    }

    public void hideCrossReferenceDialog() {
        jPanelCrossReference.setVisible(false);
        jLayeredPane1.moveToBack(jPanelCrossReference);
        if (!jCheckBoxElaborado.isSelected()) {
            setSize(getMinimumSize());
        }

    }

    public void setElaboradoTable() {
        if (instance.getElaborado()) {
            jCheckBoxElaborado.setSelected(true);
        }

        jPanelIngElab.setVisible(jCheckBoxElaborado.isSelected());
        if (jCheckBoxElaborado.isSelected()) {
            setSize(getWidth(), 610);
            jLayeredPane1.moveToFront(jPanelIngElab);
        } else {
            if (!jToggleButtonCrossReference.isSelected()) {
                setSize(getMinimumSize());
            }
            jLayeredPane1.moveToBack(jPanelIngElab);
        }
        jSpinnerCosto.setEnabled(!jCheckBoxElaborado.isSelected());

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
        instance.setElaborado(jCheckBoxElaborado.isSelected());
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
        jXLabelNombreValorCosto.setText(R.formatoMoneda.format(total));
    }
}
