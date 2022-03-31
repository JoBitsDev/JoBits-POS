/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.SpinnerToValueModelConnector;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.productos.presenter.ProductoVentaDetailPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import static com.jobits.pos.ui.productos.presenter.ProductoVentaDetailViewModel.*;
import com.jobits.pos.ui.utils.AddFromPanel;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.pos.utils.utils;
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Jorge
 */
public class ProductoVentaDetailView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Crear Producto";

    private AddFromPanel<ProductoInsumo, Insumo> crossReferencePanel;
    private ProductoVenta instance;

    public ProductoVentaDetailView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jCheckBoxInventariarProducto = MaterialComponentsFactory.Input.getCheckBox();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelInventario = new javax.swing.JPanel();
        jButtonAddInsumo = MaterialComponentsFactory.Buttons.getAddButton();
        jPanelCosto = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelGasto = new javax.swing.JLabel();
        jPanel14 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jTabbedPane1 = MaterialComponentsFactory.Containers.getTabPane();
        jPanelInputs = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jXLabelPCod = new org.jdesktop.swingx.JXLabel();
        jPanel15 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel7 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel8 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldNombre = MaterialComponentsFactory.Input.getTextField("", "Nombre");
        jPanel17 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabelIProductImage = new javax.swing.JLabel();
        jPanel10 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel19 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldPrecioVenta = MaterialComponentsFactory.Input.getTextFielPrecioVenta("", "Precio venta",R.COIN_SUFFIX);
        jPanel20 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldPrecioCosto = MaterialComponentsFactory.Input.getTextFielPrecioVenta("", "Precio de costo",R.COIN_SUFFIX);
        jPanel21 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonAddCocina = MaterialComponentsFactory.Buttons.getAddButton();
        jComboBoxCOCINA = MaterialComponentsFactory.Displayers.getComboBox("Elaborado en:");
        jPanel26 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonAddSeccion = MaterialComponentsFactory.Buttons.getAddButton();
        jComboBoxSECCION = MaterialComponentsFactory.Displayers.getComboBox("Categoria");
        jPanelTable = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel6 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jCheckBoxProductoElaborado = MaterialComponentsFactory.Input.getCheckBox();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelCrossRef = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel11 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel9 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jCheckBoxExentoAImpuestos = MaterialComponentsFactory.Input.getCheckBox();
        jPanel4 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel3 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jSpinner1 = new javax.swing.JSpinner();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldPagoPorVenta = MaterialComponentsFactory.Input.getTextFielPrecioVenta("", java.util.ResourceBundle.getBundle("Strings").getString("label_comision_por_venta"),R.COIN_SUFFIX);
        jPanel16 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel5 = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jSpinnerComisionVentaPorcentual = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonCrear = MaterialComponentsFactory.Buttons.getAcceptButton();

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBoxInventariarProducto.setText("Inventariar Producto");
        jCheckBoxInventariarProducto.setContentAreaFilled(false);
        jPanel3.add(jCheckBoxInventariarProducto);
        jPanel3.add(filler6);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonAddInsumo.setText(bundle.getString("label_agregar_ingrediente")); // NOI18N
        jButtonAddInsumo.setMaximumSize(new java.awt.Dimension(130, 60));
        jButtonAddInsumo.setMinimumSize(new java.awt.Dimension(60, 50));
        jButtonAddInsumo.setPreferredSize(new java.awt.Dimension(130, 50));
        jButtonAddInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddInsumoActionPerformed(evt);
            }
        });

        jPanelCosto.setLayout(new java.awt.BorderLayout());

        jLabel1.setForeground(DefaultValues.PRIMARY_COLOR_DARK);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/dolar_indigo.png"))); // NOI18N
        jLabel1.setText(":   ");
        jLabel1.setOpaque(true);
        jPanelCosto.add(jLabel1, java.awt.BorderLayout.WEST);

        jLabelGasto.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelGasto.setForeground(DefaultValues.PRIMARY_COLOR_DARK);
        jLabelGasto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelGasto.setText("0.0");
        jLabelGasto.setOpaque(true);
        jPanelCosto.add(jLabelGasto, java.awt.BorderLayout.CENTER);

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(570, 550));
        setLayout(new java.awt.BorderLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanelInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelInputs.setLayout(new java.awt.BorderLayout());

        jXLabelPCod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jXLabelPCod.setText("P-Cod");
        jPanelInputs.add(jXLabelPCod, java.awt.BorderLayout.PAGE_START);

        jPanel15.setOpaque(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(44, 25));
        jPanel15.setLayout(new java.awt.GridLayout(4, 0));

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel7.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanel7.setMinimumSize(new java.awt.Dimension(14, 70));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(14, 120));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jTextFieldNombre.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldNombre.setMaximumSize(new java.awt.Dimension(350, 60));
        jTextFieldNombre.setMinimumSize(new java.awt.Dimension(350, 60));
        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(350, 60));
        jPanel8.add(jTextFieldNombre, new java.awt.GridBagConstraints());

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabelIProductImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIProductImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/pregunta_color.png"))); // NOI18N
        jLabelIProductImage.setToolTipText(null);
        jLabelIProductImage.setPreferredSize(new java.awt.Dimension(70, 70));
        jLabelIProductImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelIProductImageMouseClicked(evt);
            }
        });
        jPanel17.add(jLabelIProductImage, new java.awt.GridBagConstraints());

        jPanel7.add(jPanel17, java.awt.BorderLayout.WEST);

        jPanel15.add(jPanel7);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.GridLayout(1, 2, 10, 10));

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jTextFieldPrecioVenta.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldPrecioVenta.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioVenta.setPreferredSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPrecioVentaFocusGained(evt);
            }
        });
        jPanel19.add(jTextFieldPrecioVenta, new java.awt.GridBagConstraints());

        jPanel10.add(jPanel19);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jTextFieldPrecioCosto.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldPrecioCosto.setMaximumSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioCosto.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioCosto.setPreferredSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioCosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPrecioCostoFocusGained(evt);
            }
        });
        jPanel20.add(jTextFieldPrecioCosto, new java.awt.GridBagConstraints());

        jPanel10.add(jPanel20);

        jPanel15.add(jPanel10);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jButtonAddCocina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/olla_indigo.png"))); // NOI18N
        jButtonAddCocina.setToolTipText("Nuevo Punto de Elaboracion");
        jButtonAddCocina.setMaximumSize(new java.awt.Dimension(300, 60));
        jButtonAddCocina.setMinimumSize(new java.awt.Dimension(50, 50));
        jButtonAddCocina.setPreferredSize(new java.awt.Dimension(80, 80));
        jButtonAddCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCocinaActionPerformed(evt);
            }
        });
        jPanel21.add(jButtonAddCocina, new java.awt.GridBagConstraints());

        jComboBoxCOCINA.setMinimumSize(new java.awt.Dimension(250, 60));
        jComboBoxCOCINA.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel21.add(jComboBoxCOCINA, new java.awt.GridBagConstraints());

        jPanel15.add(jPanel21);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        jButtonAddSeccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/categoria_indigo.png"))); // NOI18N
        jButtonAddSeccion.setToolTipText("Nueva Categoria");
        jButtonAddSeccion.setMaximumSize(new java.awt.Dimension(300, 60));
        jButtonAddSeccion.setMinimumSize(new java.awt.Dimension(50, 50));
        jButtonAddSeccion.setPreferredSize(new java.awt.Dimension(80, 80));
        jButtonAddSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSeccionActionPerformed(evt);
            }
        });
        jPanel26.add(jButtonAddSeccion, new java.awt.GridBagConstraints());

        jComboBoxSECCION.setMinimumSize(new java.awt.Dimension(250, 40));
        jComboBoxSECCION.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel26.add(jComboBoxSECCION, new java.awt.GridBagConstraints());

        jPanel15.add(jPanel26);

        jPanelInputs.add(jPanel15, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(bundle.getString("label_basico"), jPanelInputs); // NOI18N

        jPanelTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelTable.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        jPanelTable.setMinimumSize(new java.awt.Dimension(438, 100));
        jPanelTable.setLayout(new java.awt.BorderLayout());

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBoxProductoElaborado.setText("Producto elaborado");
        jCheckBoxProductoElaborado.setToolTipText("");
        jCheckBoxProductoElaborado.setContentAreaFilled(false);
        jCheckBoxProductoElaborado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxProductoElaboradoStateChanged(evt);
            }
        });
        jPanel6.add(jCheckBoxProductoElaborado);
        jPanel6.add(filler7);

        jPanelTable.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanelCrossRef.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanelCrossRef.setOpaque(false);
        jPanelCrossRef.setLayout(new java.awt.BorderLayout());
        jPanelTable.add(jPanelCrossRef, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Inventario", jPanelTable);

        jPanel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 121));
        jPanel11.setLayout(new java.awt.GridLayout(4, 1));

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.BorderLayout());

        jCheckBoxExentoAImpuestos.setText("Exento a impuestos");
        jCheckBoxExentoAImpuestos.setToolTipText("");
        jCheckBoxExentoAImpuestos.setContentAreaFilled(false);
        jCheckBoxExentoAImpuestos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBoxExentoAImpuestos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxExentoAImpuestosStateChanged(evt);
            }
        });
        jPanel9.add(jCheckBoxExentoAImpuestos, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel9);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Tiempo de Elaboracion:");
        jPanel4.add(jLabel3, new java.awt.GridBagConstraints());
        jPanel4.add(filler1, new java.awt.GridBagConstraints());

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2000, 1));
        jSpinner1.setPreferredSize(new java.awt.Dimension(90, 40));
        jPanel4.add(jSpinner1, new java.awt.GridBagConstraints());
        jPanel4.add(filler2, new java.awt.GridBagConstraints());

        jLabel2.setText("MIn");
        jPanel4.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel11.add(jPanel4);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTextFieldPagoPorVenta.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldPagoPorVenta.setMaximumSize(new java.awt.Dimension(250, 60));
        jTextFieldPagoPorVenta.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldPagoPorVenta.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel1.add(jTextFieldPagoPorVenta, new java.awt.GridBagConstraints());

        jPanel11.add(jPanel1);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Comision por Venta Porcentual");
        jPanel16.add(jLabel5, new java.awt.GridBagConstraints());
        jPanel16.add(filler3, new java.awt.GridBagConstraints());

        jSpinnerComisionVentaPorcentual.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jSpinnerComisionVentaPorcentual.setPreferredSize(new java.awt.Dimension(90, 40));
        jPanel16.add(jSpinnerComisionVentaPorcentual, new java.awt.GridBagConstraints());

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("%");
        jPanel16.add(jLabel6, new java.awt.GridBagConstraints());

        jPanel11.add(jPanel16);

        jTabbedPane1.addTab("Otros", jPanel11);

        jPanel14.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setMinimumSize(new java.awt.Dimension(239, 70));
        jPanel2.setPreferredSize(new java.awt.Dimension(849, 50));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanel2.add(jButtonCancelar, new java.awt.GridBagConstraints());

        jButtonCrear.setMnemonic('c');
        jButtonCrear.setText(bundle.getString("label_crear_producto")); // NOI18N
        jButtonCrear.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanel2.add(jButtonCrear, new java.awt.GridBagConstraints());

        jPanel14.add(jPanel2, java.awt.BorderLayout.SOUTH);

        add(jPanel14, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddInsumoActionPerformed

    }//GEN-LAST:event_jButtonAddInsumoActionPerformed

    private void jCheckBoxProductoElaboradoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxProductoElaboradoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxProductoElaboradoStateChanged

    private void jButtonAddSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddSeccionActionPerformed

    private void jButtonAddCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCocinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddCocinaActionPerformed

    private void jLabelIProductImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelIProductImageMouseClicked
        if (evt.getClickCount() == 2) {
            getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_EDITAR_IMAGEN).doAction();
        }
    }//GEN-LAST:event_jLabelIProductImageMouseClicked

    private void jTextFieldPrecioVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrecioVentaFocusGained
    }//GEN-LAST:event_jTextFieldPrecioVentaFocusGained

    private void jTextFieldPrecioCostoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrecioCostoFocusGained
    }//GEN-LAST:event_jTextFieldPrecioCostoFocusGained

    private void jCheckBoxExentoAImpuestosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxExentoAImpuestosStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxExentoAImpuestosStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JButton jButtonAddCocina;
    private javax.swing.JButton jButtonAddInsumo;
    private javax.swing.JButton jButtonAddSeccion;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JCheckBox jCheckBoxExentoAImpuestos;
    private javax.swing.JCheckBox jCheckBoxInventariarProducto;
    private javax.swing.JCheckBox jCheckBoxProductoElaborado;
    private javax.swing.JComboBox<Cocina> jComboBoxCOCINA;
    private javax.swing.JComboBox<Seccion> jComboBoxSECCION;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelGasto;
    private javax.swing.JLabel jLabelIProductImage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCosto;
    private javax.swing.JPanel jPanelCrossRef;
    private javax.swing.JPanel jPanelInputs;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinnerComisionVentaPorcentual;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPagoPorVenta;
    private javax.swing.JTextField jTextFieldPrecioCosto;
    private javax.swing.JTextField jTextFieldPrecioVenta;
    private org.jdesktop.swingx.JXLabel jXLabelPCod;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        //
        //Basico
        //
        Bindings.bind(jComboBoxCOCINA, new SelectionInList<>(getPresenter().getModel(PROP_LISTA_ELABORADO), getPresenter().getModel(PROP_ELABORADO_SELECCIONADO)));
        Bindings.bind(jComboBoxSECCION, new SelectionInList<>(getPresenter().getModel(PROP_LISTA_CATEGORIAS), getPresenter().getModel(PROP_CATEGORIA_SELECCIONADA)));
        Bindings.bind(jTextFieldNombre, getPresenter().getModel(PROP_NOMBRE_PRODUCTO));
        Bindings.bind(jTextFieldPrecioVenta, getPresenter().getModel(PROP_PRECIO_VENTA));
        Bindings.bind(jTextFieldPrecioCosto, getPresenter().getModel(PROP_PRECIO_COSTO));
        Bindings.bind(jXLabelPCod, getPresenter().getModel(PROP_CODIGO_PRODUCTO));
        jButtonAddCocina.addActionListener(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_PUNTO_ELABORACION));
        jButtonAddSeccion.addActionListener(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_CATEGORIA));

        //
        //Inventario
        //
        Bindings.bind(jCheckBoxInventariarProducto, getPresenter().getModel(PROP_CHECKBOX_INVENTARIAR_PRODUCTO));
        Bindings.bind(jCheckBoxProductoElaborado, getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_ELABORADO));
        Bindings.bind(jCheckBoxExentoAImpuestos, getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_LIBRE_IMPUESTOS));
        Bindings.bind(jPanelCrossRef, "visible", getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_ELABORADO));
        Bindings.bind(jPanelInventario, "visible", getPresenter().getModel(PROP_CHECKBOX_INVENTARIAR_PRODUCTO));

        //TODO tabla y boton
        jButtonAddInsumo.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_INSUMO));
        Bindings.bind(jLabelGasto, getPresenter().getModel(PROP_PRECIO_COSTO));

        //
        //Otros
        //
        Bindings.bind(jTextFieldPagoPorVenta, getPresenter().getModel(PROP_COMISION_POR_VENTA));
        SpinnerToValueModelConnector connector = new SpinnerToValueModelConnector(jSpinner1.getModel(),
                getPresenter().getModel(PROP_TIMEPO_ELABORACION), 0);
        Bindings.bind(jSpinner1, "value", getPresenter().getModel(PROP_TIMEPO_ELABORACION));
        SpinnerToValueModelConnector connector2 = new SpinnerToValueModelConnector(jSpinnerComisionVentaPorcentual.getModel(),
                getPresenter().getModel(PROP_COMISION_POR_VENTA_PORCENTUAL), 0);
        Bindings.bind(jSpinnerComisionVentaPorcentual, "value", getPresenter().getModel(PROP_COMISION_POR_VENTA_PORCENTUAL));

        //
        //Botones
        //
        jButtonCancelar.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_CANCELAR));
        jButtonCrear.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR));

        Bindings.bind(jButtonCrear, "text", getPresenter().getModel(PROP_CREAR_EDITAR_BUTTON_TEXT));
        Bindings.bind(jLabelIProductImage, "icon", getPresenter().getModel(PROP_IMAGEN_PRODUCTO));
    }

    @Override
    public void uiInit() {
        initComponents();

        jComboBoxCOCINA.setOpaque(false);
        jComboBoxSECCION.setOpaque(false);
        jButtonAddCocina.setBorderPainted(true);

        AddFromPanel.AddFromPanelBuilder<ProductoInsumo, Insumo> builder = AddFromPanel.builder();
        builder.addAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_INSUMO_FICHA));
        builder.autoCOmpletitionDataSelection(getPresenter().getModel(PROP_INSUMO_DISPONIBLE_SEL));
        builder.autoCompletitionData(getPresenter().getModel(PROP_LISTA_INSUMOS_DISPONIBLES));
        builder.jTextFieldDataName("Insumos");
        builder.removeAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_ELIMINAR_INSUMO_FICHA));
        builder.tableDataHolder(getPresenter().getModel(PROP_LISTA_INSUMOS_CONTENIDOS));
        builder.tableSelectionDataHolder(getPresenter().getModel(PROP_INSUMO_SELECCIONADO));

        BindableTableModel<ProductoInsumo> tableModel
                = new BindableTableModel<ProductoInsumo>() {
            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getInsumo().getCodInsumo();
                    case 1:
                        return getRow(rowIndex).getInsumo().getNombre();
                    case 2:
                        return getRow(rowIndex).getInsumo().getUm();
                    case 3:
                        return getRow(rowIndex).getCantidad();
                    case 4:
                        return utils.setDosLugaresDecimalesFloat(getRow(rowIndex).getCosto());
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
                if (columnIndex == 3 || columnIndex == 4) {
                    return Float.class;
                }
                return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 3:
                        getRow(rowIndex).setCantidad((float) aValue);
                        getRow(rowIndex).setCosto(getRow(rowIndex).getInsumo().getCostoPorUnidad() * (float) aValue);
                        fireTableRowsUpdated(rowIndex, rowIndex);
                        break;
                }
            }
        };
        builder.tableModel(tableModel);

        crossReferencePanel = builder.build();
        crossReferencePanel.getjTableCrossReference().getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
        crossReferencePanel.getjTableCrossReference().getColumnModel().getColumn(4).setCellRenderer(utils.numberColumCellRender());
        crossReferencePanel.getjPanelOpciones().add(jButtonAddInsumo, 0);
        jPanelCrossRef.add(crossReferencePanel, BorderLayout.CENTER);
        jTabbedPane1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jTextFieldNombre.requestFocusInWindow();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        getPresenter().addBeanPropertyChangeListener(PROP_INSUMO_DISPONIBLE_SEL, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() == null) {
                crossReferencePanel.getJTextFieldAutoComplete().setText("");
            }
        });
        getPresenter().addBeanPropertyChangeListener(PROP_IMAGEN_PRODUCTO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() == null) {
                jLabelIProductImage.repaint();
                jLabelIProductImage.revalidate();
            }
        });

        crossReferencePanel.getjPanelOpcionesContainer().add(jPanelCosto, java.awt.BorderLayout.WEST);
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
