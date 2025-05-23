/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.ipv;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.core.domain.InsumoPedidoModel;
import com.jobits.pos.core.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.IpvVentaRegistro;
import com.jobits.pos.ui.AbstractViewPanel;
import static com.jobits.pos.ui.almacen.ipv.presenter.IPVPedidoVentasViewPresenter.*;
import static com.jobits.pos.ui.almacen.ipv.presenter.IPVPedidoVentasViewModel.*;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.AddFromPanel;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.pos.utils.utils;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;

/**
 *
 * @author Home
 */
public class IPVPedidoVentasView extends AbstractViewPanel {

    public static final String VIEW_NAME = "IPV Pedido Ventas";
    private AddFromPanel<ProdcutoVentaPedidoModel, IpvVentaRegistro> tableAPedir;

    /**
     * Creates new form IPVPedidoVentasView
     *
     * @param presenter
     */
    public IPVPedidoVentasView(AbstractViewPresenter presenter) {
        super(presenter);
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
        jPanelTop = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBox1 = MaterialComponentsFactory.Displayers.getComboBox("Pedir de Almacen");
        jPanelMain = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelAPedir = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelPedido = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jScrollPane1 = MaterialComponentsFactory.Containers.getScrollPane();
        jTablePedido = new javax.swing.JTable();
        jPanelOpciones = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonAceptar = MaterialComponentsFactory.Buttons.getMaterialButton();

        setMaximumSize(new java.awt.Dimension(810, 550));
        setMinimumSize(new java.awt.Dimension(810, 550));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(810, 550));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanelTop.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanelTop.setLayout(new java.awt.GridBagLayout());

        jComboBox1.setToolTipText("Pedir del almcen");
        jComboBox1.setPreferredSize(new java.awt.Dimension(250, 50));
        jPanelTop.add(jComboBox1, new java.awt.GridBagConstraints());

        jPanel2.add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 0, 10));
        jPanelMain.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jPanelAPedir.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15), "A pedir"));
        jPanelAPedir.setPreferredSize(new java.awt.Dimension(100, 0));
        jPanelAPedir.setLayout(new java.awt.BorderLayout());
        jPanelMain.add(jPanelAPedir);

        jPanelPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15), "Pedido"));
        jPanelPedido.setPreferredSize(new java.awt.Dimension(300, 0));
        jPanelPedido.setLayout(new java.awt.BorderLayout());

        jTablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Insumo", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePedido);

        jPanelPedido.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelMain.add(jPanelPedido);

        jPanel2.add(jPanelMain, java.awt.BorderLayout.CENTER);

        jPanelOpciones.setPreferredSize(new java.awt.Dimension(168, 50));
        jPanelOpciones.setLayout(new java.awt.GridBagLayout());

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setBorderPainted(false);
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(140, 40));
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jButtonCancelar, new java.awt.GridBagConstraints());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonAceptar.setText(bundle.getString("label_confirmar")); // NOI18N
        jButtonAceptar.setBorderPainted(false);
        jButtonAceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAceptar.setPreferredSize(new java.awt.Dimension(140, 40));
        jButtonAceptar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonAceptar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jButtonAceptar, new java.awt.GridBagConstraints());

        jPanel2.add(jPanelOpciones, java.awt.BorderLayout.SOUTH);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
//        if (getController().realizarPedidoDeIpv(listaPedido.getItems(),
//            porPedirTable.getHandler().getTableModel().getItems(),
//            elaboracion, (Almacen) jComboBox1.getSelectedItem())) {
//        dispose();
//        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
//        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox<Almacen> jComboBox1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAPedir;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPedido;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePedido;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jComboBox1, new SelectionInList<>(
                getPresenter().getModel(PROP_LISTA_ALMACEN),
                getPresenter().getModel(PROP_SELECCIONADO_ALMACEN)));

        Bindings.bind(jTablePedido, new SelectionInList<String>(
                getPresenter().getModel(PROP_LISTA_INSUMO_PEDIDO_MODEL),
                getPresenter().getModel(PROP_SELECCIONADO_INSUMO_PEDIDO)));

        jButtonAceptar.addActionListener(getPresenter().getOperation(ACTION_ACEPTAR));
        jButtonCancelar.addActionListener(getPresenter().getOperation(ACTION_CANCELAR));

    }

    @Override
    public void uiInit() {
        initComponents();

        AddFromPanel.AddFromPanelBuilder<ProdcutoVentaPedidoModel, IpvVentaRegistro> builder = AddFromPanel.builder();

        builder.addAction(getPresenter().getOperation(ACTION_AGREGAR_IPV));
        builder.removeAction(getPresenter().getOperation(ACTION_ELIMINAR_IPV));
        builder.autoCompletitionData(getPresenter().getModel(PROP_LISTA_IPV_VENTA_REGISTRO));
        builder.autoCOmpletitionDataSelection(getPresenter().getModel(PROP_SELECCIONADO_IPV_VENTAS));
        builder.jTextFieldDataName("Productos de IPV");
        builder.tableDataHolder(getPresenter().getModel(PROP_LISTA_PRODUCTO_VENTA_MODEL));
        builder.tableSelectionDataHolder(getPresenter().getModel(PROP_SELECCIONADO_PRODUCTO_VENTA));

        BindableTableModel<ProdcutoVentaPedidoModel> tableModel
                = new BindableTableModel<ProdcutoVentaPedidoModel>() {
            @Override
            public int getColumnCount() {
                return 2;

            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getIpvProducto().getProductoVenta();
                    case 1:
                        return getRow(rowIndex).getCantidad();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Producto IPV";
                    case 1:
                        return "Cantidad pedida";
                    default:
                        return null;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                        return Float.class;
                    default:
                        return super.getColumnClass(columnIndex);
                }
            }
        };
        builder.tableModel(tableModel);

        tableAPedir = builder.build();
        tableAPedir.getjTableCrossReference().getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
//        tableAPedir.getjButtonAgregarProd().setText("Registrar");
        jPanelAPedir.add(tableAPedir, BorderLayout.CENTER);

        jTablePedido.setModel(new BindableTableModel<InsumoPedidoModel>(jTablePedido) {
            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Insumo en almacen";
                    case 1:
                        return "Cantidad";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ((InsumoPedidoModel) getListModel().getElementAt(rowIndex)).getInsumo();
                    case 1:
                        return ((InsumoPedidoModel) getListModel().getElementAt(rowIndex)).getCantidad();
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                        return Float.class;
                    default:
                        return super.getColumnClass(columnIndex);
                }
            }
        });
        jTablePedido.getRowSorter().toggleSortOrder(0);
        jTablePedido.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;

    }
}
