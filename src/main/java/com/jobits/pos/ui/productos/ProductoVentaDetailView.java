/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.productos.presenter.ProductoVentaDetailPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import static com.jobits.pos.ui.productos.presenter.ProductoVentaDetailViewModel.*;
import com.jobits.pos.ui.utils.AddFromPanel;
import com.jobits.pos.ui.utils.BindableTableModel;
import java.awt.BorderLayout;

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
        jPanel2 = new javax.swing.JPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonCrear = MaterialComponentsFactory.Buttons.getAcceptButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelInputs = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jXLabelPCod = new org.jdesktop.swingx.JXLabel();
        jPanel12 = new javax.swing.JPanel();
        jTextFieldNombre = MaterialComponentsFactory.Input.getTextField("", "Nombre");
        jPanelImage = new javax.swing.JPanel();
        jLabelIProductImage = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldPrecioVenta = MaterialComponentsFactory.Input.getTextFielPrecioVenta("0.00", "Precio venta",R.COIN_SUFFIX);
        jTextFieldPrecioCosto = MaterialComponentsFactory.Input.getTextFielPrecioVenta("0.00", "Precio de costo",R.COIN_SUFFIX);
        jComboBoxCOCINA = MaterialComponentsFactory.Displayers.getComboBox();
        jButtonAddCocina = MaterialComponentsFactory.Buttons.getAddButton();
        jComboBoxSECCION = MaterialComponentsFactory.Displayers.getComboBox();
        jButtonAddSeccion = MaterialComponentsFactory.Buttons.getAddButton();
        jPanelTable = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel6 = new javax.swing.JPanel();
        jCheckBoxProductoElaborado = MaterialComponentsFactory.Input.getCheckBox();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelCrossRef = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButtonAddInsumo = MaterialComponentsFactory.Buttons.getAddButton();
        jXLabelGasto = new org.jdesktop.swingx.JXLabel();
        jPanel11 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jTextFieldPagoPorVenta = MaterialComponentsFactory.Input.getTextFielPrecioVenta("", java.util.ResourceBundle.getBundle("Strings").getString("label_comision_por_venta"),R.COIN_SUFFIX);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBoxInventariarProducto.setText("Inventariar Producto");
        jCheckBoxInventariarProducto.setContentAreaFilled(false);
        jPanel3.add(jCheckBoxInventariarProducto);
        jPanel3.add(filler6);

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(570, 550));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(239, 70));
        jPanel2.setPreferredSize(new java.awt.Dimension(849, 70));

        jButtonCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jPanel2.add(jButtonCancelar);

        jButtonCrear.setMnemonic('c');
        jButtonCrear.setText(bundle.getString("label_crear_producto")); // NOI18N
        jPanel2.add(jButtonCrear);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanelInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelInputs.setLayout(new javax.swing.BoxLayout(jPanelInputs, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel7.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanel7.setMinimumSize(new java.awt.Dimension(14, 70));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(14, 120));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(44, 25));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabelPCod.setText("P-Cod");
        jPanel8.add(jXLabelPCod);

        jPanel7.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.BorderLayout());

        jTextFieldNombre.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jPanel12.add(jTextFieldNombre, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanelImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 30));
        jPanelImage.setOpaque(false);
        jPanelImage.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanelImage.setLayout(new java.awt.BorderLayout());

        jLabelIProductImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIProductImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/pregunta_color.png"))); // NOI18N
        jLabelIProductImage.setToolTipText(null);
        jLabelIProductImage.setPreferredSize(new java.awt.Dimension(70, 70));
        jLabelIProductImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelIProductImageMouseClicked(evt);
            }
        });
        jPanelImage.add(jLabelIProductImage, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanelImage, java.awt.BorderLayout.WEST);

        jPanelInputs.add(jPanel7);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        jTextFieldPrecioVenta.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldPrecioVenta.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioVenta.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel10.add(jTextFieldPrecioVenta);

        jTextFieldPrecioCosto.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldPrecioCosto.setMaximumSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioCosto.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldPrecioCosto.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel10.add(jTextFieldPrecioCosto);

        jComboBoxCOCINA.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3), "Elaborado en"));
        jComboBoxCOCINA.setMinimumSize(new java.awt.Dimension(250, 60));
        jComboBoxCOCINA.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel10.add(jComboBoxCOCINA);

        jButtonAddCocina.setText(bundle.getString("label_nuevo_pto_elaboracion")); // NOI18N
        jButtonAddCocina.setMaximumSize(new java.awt.Dimension(300, 60));
        jButtonAddCocina.setMinimumSize(new java.awt.Dimension(60, 50));
        jButtonAddCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCocinaActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonAddCocina);

        jComboBoxSECCION.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3), bundle.getString("label_categoria"))); // NOI18N
        jComboBoxSECCION.setMinimumSize(new java.awt.Dimension(250, 40));
        jComboBoxSECCION.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel10.add(jComboBoxSECCION);

        jButtonAddSeccion.setText(bundle.getString("label_nueva_categoria")); // NOI18N
        jButtonAddSeccion.setMaximumSize(new java.awt.Dimension(300, 60));
        jButtonAddSeccion.setMinimumSize(new java.awt.Dimension(60, 50));
        jButtonAddSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSeccionActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonAddSeccion);

        jPanelInputs.add(jPanel10);

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

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonAddInsumo.setText(bundle.getString("label_agregar_ingrediente")); // NOI18N
        jButtonAddInsumo.setMaximumSize(new java.awt.Dimension(130, 60));
        jButtonAddInsumo.setMinimumSize(new java.awt.Dimension(60, 50));
        jButtonAddInsumo.setPreferredSize(new java.awt.Dimension(130, 50));
        jButtonAddInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddInsumoActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonAddInsumo);

        jPanelCrossRef.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jXLabelGasto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabelGasto.setText("0.00"); // NOI18N
        jPanelCrossRef.add(jXLabelGasto, java.awt.BorderLayout.PAGE_END);

        jPanelTable.add(jPanelCrossRef, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Inventario", jPanelTable);

        jPanel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 121));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 100));

        jTextFieldPagoPorVenta.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldPagoPorVenta.setMaximumSize(new java.awt.Dimension(250, 60));
        jTextFieldPagoPorVenta.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldPagoPorVenta.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel11.add(jTextFieldPagoPorVenta);

        jTabbedPane1.addTab("Otros", jPanel11);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JButton jButtonAddCocina;
    private javax.swing.JButton jButtonAddInsumo;
    private javax.swing.JButton jButtonAddSeccion;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JCheckBox jCheckBoxInventariarProducto;
    private javax.swing.JCheckBox jCheckBoxProductoElaborado;
    private javax.swing.JComboBox<Cocina> jComboBoxCOCINA;
    private javax.swing.JComboBox<Seccion> jComboBoxSECCION;
    private javax.swing.JLabel jLabelIProductImage;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelCrossRef;
    private javax.swing.JPanel jPanelImage;
    private javax.swing.JPanel jPanelInputs;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPagoPorVenta;
    private javax.swing.JTextField jTextFieldPrecioCosto;
    private javax.swing.JTextField jTextFieldPrecioVenta;
    private org.jdesktop.swingx.JXLabel jXLabelGasto;
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
        Bindings.bind(jXLabelPCod, getPresenter().getModel(PROP_CODIGO_PRODUCTO));
        Bindings.bind(jTextFieldPrecioCosto, getPresenter().getModel(PROP_PRECIO_COSTO));
        jButtonAddCocina.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_PUNTO_ELABORACION));
        jButtonAddSeccion.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_CATEGORIA));

        //
        //Inventario
        //
        Bindings.bind(jCheckBoxInventariarProducto, getPresenter().getModel(PROP_CHECKBOX_INVENTARIAR_PRODUCTO));
        Bindings.bind(jCheckBoxProductoElaborado, getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_ELABORADO));
        Bindings.bind(jPanelCrossRef, "visible", getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_ELABORADO));
        Bindings.bind(jPanelInventario, "visible", getPresenter().getModel(PROP_CHECKBOX_INVENTARIAR_PRODUCTO));

        //TODO tabla y boton
        jButtonAddInsumo.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_INSUMO));
        Bindings.bind(jXLabelGasto, getPresenter().getModel(PROP_PRECIO_COSTO));

        //
        //Otros
        //
        Bindings.bind(jTextFieldPagoPorVenta, getPresenter().getModel(PROP_COMISION_POR_VENTA));
        jButtonCancelar.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_CANCELAR));
        jButtonCrear.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR));

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
                        return getRow(rowIndex).getCosto();
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
                        getRow(rowIndex).setCantidad((float) aValue);
                        getRow(rowIndex).setCosto(getRow(rowIndex).getInsumo().getCostoPorUnidad() * (float) aValue);
                        fireTableRowsUpdated(rowIndex, rowIndex);
                        break;
                }
            }
        };
        builder.tableModel(tableModel);

        crossReferencePanel = builder.build();
        
        crossReferencePanel.getjPanelOpciones().add(jButtonAddInsumo, 0);

        jPanelCrossRef.add(crossReferencePanel, BorderLayout.CENTER);

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
