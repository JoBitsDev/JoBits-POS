/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.productoventa;

import GUI.Components.JSpinner;
import GUI.Components.JTextField;
import GUI.Views.AbstractCrossReferenePanel;
import GUI.Views.AbstractDetailView;
import GUI.Views.AbstractReadOnlyView;
import GUI.Views.AbstractView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import org.jdesktop.swingx.JXPanel;
import restManager.controller.AbstractDialogController;
import restManager.controller.AbstractDetailController;
import restManager.controller.productoventa.ProductoVentaCreateEditController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoInsumoPK;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.printservice.ComponentPrinter;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerComboBoxModel;

/**
 *
 * @author Jorge
 */
public class ProductoVentaReadOnlyView extends AbstractReadOnlyView<ProductoVenta> {

    public ProductoVentaReadOnlyView(ProductoVenta instance, AbstractDialogController controller, Frame owner, boolean modal) {
        super(instance, DialogType.INPUT_LARGE, controller, owner, modal);
        initComponents();
        fetchComponentData();
    }

    public ProductoVentaReadOnlyView(ProductoVenta instance, AbstractDialogController controller, Dialog owner, boolean modal) {
        super(instance, DialogType.INPUT_LARGE, controller, owner, modal);
        initComponents();
        fetchComponentData();

    }

    public JXPanel getjXPanelRoot() {
        return jXPanelRoot;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanelRoot = new org.jdesktop.swingx.JXPanel();
        jPanelInputs = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jXLabelPCod = new org.jdesktop.swingx.JXLabel();
        jPanel7 = new javax.swing.JPanel();
        jXLabelNombre = new org.jdesktop.swingx.JXLabel();
        jTextFieldNombre = new JTextField();
        jXLabelPrecio = new org.jdesktop.swingx.JXLabel();
        jSpinnerPrecio = new JSpinner();
        jXLabelMoneda = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jXLabelCocina = new org.jdesktop.swingx.JXLabel();
        jComboBoxCOCINA = new javax.swing.JComboBox<>();
        jXLabelSeccion = new org.jdesktop.swingx.JXLabel();
        jComboBoxSECCION = new javax.swing.JComboBox<>();
        jPanelTable = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jXLabelCosto = new org.jdesktop.swingx.JXLabel();
        jXLabelGasto = new org.jdesktop.swingx.JXLabel();
        jXLabelMoneda1 = new org.jdesktop.swingx.JXLabel();
        jPanelCrossRef = new javax.swing.JPanel();
        jPanelActions = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        jXPanelRoot.setBackground(new java.awt.Color(0, 102, 102));
        jXPanelRoot.setLayout(new javax.swing.BoxLayout(jXPanelRoot, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelInputs.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Básica"));
        jPanelInputs.setLayout(new javax.swing.BoxLayout(jPanelInputs, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabelPCod.setText("P-Cod");
        jPanel8.add(jXLabelPCod);

        jPanelInputs.add(jPanel8);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jXLabelNombre.setText(bundle.getString("label_nombre")); // NOI18N
        jPanel7.add(jXLabelNombre);

        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldNombre.setEnabled(false);
        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel7.add(jTextFieldNombre);

        jXLabelPrecio.setText(bundle.getString("label_precio")); // NOI18N
        jPanel7.add(jXLabelPrecio);

        jSpinnerPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSpinnerPrecio.setEnabled(false);
        jSpinnerPrecio.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanel7.add(jSpinnerPrecio);

        jXLabelMoneda.setText(R.coinSuffix);
        jXLabelMoneda.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel7.add(jXLabelMoneda);

        jPanelInputs.add(jPanel7);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

        jXLabelCocina.setText(bundle.getString("label_cocina")); // NOI18N
        jPanel2.add(jXLabelCocina);

        jComboBoxCOCINA.setEditable(true);
        jComboBoxCOCINA.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanel2.add(jComboBoxCOCINA);

        jXLabelSeccion.setText(bundle.getString("label_seccion")); // NOI18N
        jPanel2.add(jXLabelSeccion);

        jComboBoxSECCION.setEditable(true);
        jComboBoxSECCION.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanel2.add(jComboBoxSECCION);

        jPanelInputs.add(jPanel2);

        jXPanelRoot.add(jPanelInputs);

        jPanelTable.setLayout(new javax.swing.BoxLayout(jPanelTable, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        jXLabelCosto.setText(bundle.getString("label_costo")); // NOI18N
        jPanel6.add(jXLabelCosto);

        jXLabelGasto.setText("0.00"); // NOI18N
        jPanel6.add(jXLabelGasto);

        jXLabelMoneda1.setText(R.coinSuffix);
        jXLabelMoneda1.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel6.add(jXLabelMoneda1);

        jPanelTable.add(jPanel6);

        jPanelCrossRef.setBorder(javax.swing.BorderFactory.createTitledBorder("Insumos"));
        jPanelCrossRef.setPreferredSize(new java.awt.Dimension(529, 300));
        jPanelCrossRef.setLayout(new java.awt.BorderLayout());
        jPanelTable.add(jPanelCrossRef);

        jXPanelRoot.add(jPanelTable);

        jPanelActions.setBackground(new java.awt.Color(204, 204, 204));
        jPanelActions.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanelActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 5));

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonCancelar);

        jXPanelRoot.add(jPanelActions);

        getContentPane().add(jXPanelRoot, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed



    @Override
    public void updateView() {
        this.jXLabelPCod.setText(instance.getPCod());
        this.jTextFieldNombre.setText(instance.getNombre());
        jSpinnerPrecio.setValue(instance.getPrecioVenta());
        if (instance.getGasto() == null) {
            jXLabelGasto.setText(String.valueOf(0.00));
        } else {
            jXLabelGasto.setText(String.format("%.2f ", instance.getGasto()));
        }
        jComboBoxCOCINA.setEditable(true);
        jComboBoxSECCION.setEditable(true);
        jComboBoxCOCINA.setSelectedItem(instance.getCocinacodCocina());
        jComboBoxSECCION.setSelectedItem(instance.getSeccionnombreSeccion());
    }

    @Override
    public void fetchComponentData() {
        AbstractCrossReferenePanel<ProductoInsumo, Insumo> crossReferencePanel
                = new AbstractCrossReferenePanel<ProductoInsumo, Insumo>("Insumos", new ArrayList<Insumo>()) {
            @Override
            public RestManagerAbstractTableModel getTableModel() {
                return new RestManagerAbstractTableModel<ProductoInsumo>(instance.getProductoInsumoList(), getjTableCrossReference()) {
                    @Override
                    public int getColumnCount() {
                        return 5;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 0:
                                return items.get(rowIndex).getInsumo().getCodInsumo();
                            case 1:
                                return items.get(rowIndex).getInsumo().getNombre();
                            case 2:
                                return items.get(rowIndex).getInsumo().getUm();
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
                                return "Código";
                            case 1:
                                return "Nombre";
                            case 2:
                                return "U/M";
                            case 3:
                                return "Cantidad";
                            case 4:
                                return "Costo";
                            default:
                                return null;
                        }
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnIndex == 3;
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        if (columnIndex == 3) {
                            return Float.class;
                        }
                        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 3:
                                items.get(rowIndex).setCantidad((float) aValue);
                                items.get(rowIndex).setCosto(items.get(rowIndex).getInsumo().getCostoPorUnidad() * (float) aValue);
                                fireTableRowsUpdated(rowIndex, rowIndex);
                                break;

                        }
                    }
                };
            }

            @Override
            public ProductoInsumo transformK_T(Insumo selected) {
                return null;
            }
        };
        jPanelCrossRef.add(crossReferencePanel);
    }
    
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox<Cocina> jComboBoxCOCINA;
    private javax.swing.JComboBox<Seccion> jComboBoxSECCION;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelCrossRef;
    private javax.swing.JPanel jPanelInputs;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JSpinner jSpinnerPrecio;
    private javax.swing.JTextField jTextFieldNombre;
    private org.jdesktop.swingx.JXLabel jXLabelCocina;
    private org.jdesktop.swingx.JXLabel jXLabelCosto;
    private org.jdesktop.swingx.JXLabel jXLabelGasto;
    private org.jdesktop.swingx.JXLabel jXLabelMoneda;
    private org.jdesktop.swingx.JXLabel jXLabelMoneda1;
    private org.jdesktop.swingx.JXLabel jXLabelNombre;
    private org.jdesktop.swingx.JXLabel jXLabelPCod;
    private org.jdesktop.swingx.JXLabel jXLabelPrecio;
    private org.jdesktop.swingx.JXLabel jXLabelSeccion;
    private org.jdesktop.swingx.JXPanel jXPanelRoot;
    // End of variables declaration//GEN-END:variables
}
