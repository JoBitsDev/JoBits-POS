/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes;

import com.jobits.pos.ui.productos.*;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
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
public class ClientesDetailView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Detalles Cliente";

    public ClientesDetailView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jCheckBoxInventariarProducto = MaterialComponentsFactory.Input.getCheckBox();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelInventario = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelPersonalData = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldNombre = MaterialComponentsFactory.Input.getTextField("", "Nombre*");
        jTextFieldApellidos = MaterialComponentsFactory.Input.getTextField("", "Apellidos*");
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 10), new java.awt.Dimension(0, 32767));
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldTelefono = MaterialComponentsFactory.Input.getTextField(" ", java.util.ResourceBundle.getBundle("Strings").getString("label_telefono")+"*");
        jTextFieldAlias = MaterialComponentsFactory.Input.getTextField("", "Alias");
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanelAddress = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldLineAddress = MaterialComponentsFactory.Input.getTextField("", java.util.ResourceBundle.getBundle("Strings").getString("label_direccion"));
        jTextFieldMunicipio = MaterialComponentsFactory.Input.getTextField("", "Municipio");
        jTextFieldCiudad = MaterialComponentsFactory.Input.getTextField(" ", "Ciudad");
        jTextFieldPais = MaterialComponentsFactory.Input.getTextField(" ", java.util.ResourceBundle.getBundle("Strings").getString("label_pais"));
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorial = new javax.swing.JTable();
        jPanelOpciones = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonCrear = MaterialComponentsFactory.Buttons.getAcceptButton();

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBoxInventariarProducto.setText("Inventariar Producto");
        jCheckBoxInventariarProducto.setContentAreaFilled(false);
        jPanel3.add(jCheckBoxInventariarProducto);
        jPanel3.add(filler6);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(DefaultValues.SECONDARY_DARK, 2, true));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(600, 500));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setOpaque(true);

        jPanelPersonalData.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelPersonalData.setLayout(new javax.swing.BoxLayout(jPanelPersonalData, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jTextFieldNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jPanel1.add(jTextFieldNombre);

        jTextFieldApellidos.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldApellidos.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldApellidos.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldApellidos.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel1.add(jTextFieldApellidos);

        jPanelPersonalData.add(jPanel1);
        jPanelPersonalData.add(filler1);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/telefono_indigo.png"))); // NOI18N
        jPanel6.add(jLabel5);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/hombre_indigo.png"))); // NOI18N
        jPanel6.add(jLabel6);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/cumpleanos_indigo.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel6.add(jLabel7);

        jPanel5.add(jPanel6, java.awt.BorderLayout.LINE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 100));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        jTextFieldTelefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldTelefono.setPreferredSize(new java.awt.Dimension(150, 80));
        jTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(jTextFieldTelefono);

        jTextFieldAlias.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldAlias.setMaximumSize(new java.awt.Dimension(250, 60));
        jTextFieldAlias.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldAlias.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel2.add(jTextFieldAlias);

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0), "Fecha Nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jDateChooser1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDateChooser1.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel2.add(jDateChooser1);

        jPanel5.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanelPersonalData.add(jPanel5);

        jTabbedPane1.addTab("Datos Personales", jPanelPersonalData);

        jPanelAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelAddress.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 100));
        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jTextFieldLineAddress.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldLineAddress.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jPanel8.add(jTextFieldLineAddress);

        jTextFieldMunicipio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldMunicipio.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldMunicipio.setMaximumSize(new java.awt.Dimension(250, 60));
        jTextFieldMunicipio.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldMunicipio.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel8.add(jTextFieldMunicipio);

        jTextFieldCiudad.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldCiudad.setPreferredSize(new java.awt.Dimension(150, 80));
        jTextFieldCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCiudadKeyTyped(evt);
            }
        });
        jPanel8.add(jTextFieldCiudad);

        jTextFieldPais.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldPais.setPreferredSize(new java.awt.Dimension(150, 80));
        jTextFieldPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPaisKeyTyped(evt);
            }
        });
        jPanel8.add(jTextFieldPais);

        jPanelAddress.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/calle_indigo.png"))); // NOI18N
        jPanel4.add(jLabel1);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/edificio_indigo.png"))); // NOI18N
        jPanel4.add(jLabel2);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/estado_indigo.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/mundo_indigo.png"))); // NOI18N
        jPanel4.add(jLabel4);

        jPanelAddress.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jTabbedPane1.addTab("Direccion", jPanelAddress);

        jPanelTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelTable.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        jPanelTable.setMinimumSize(new java.awt.Dimension(438, 100));
        jPanelTable.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setOpaque(false);

        jTableHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Fecha", "Valor Monetario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableHistorial);

        jPanelTable.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Historial de Compra", jPanelTable);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanelOpciones.setMinimumSize(new java.awt.Dimension(239, 70));

        jButtonCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jPanelOpciones.add(jButtonCancelar);

        jButtonCrear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCrear.setMnemonic('c');
        jButtonCrear.setText(bundle.getString("label_crear_producto")); // NOI18N
        jPanelOpciones.add(jButtonCrear);

        add(jPanelOpciones, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoKeyTyped
        char c = evt.getKeyChar();
        int l = jTextFieldTelefono.getText().length();
        if (((l >= 8) || (c < '0') || (c > '9')) && (c != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoKeyTyped

    private void jTextFieldCiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCiudadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCiudadKeyTyped

    private void jTextFieldPaisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPaisKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPaisKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JCheckBox jCheckBoxInventariarProducto;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAddress;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPersonalData;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableHistorial;
    private javax.swing.JTextField jTextFieldAlias;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCiudad;
    private javax.swing.JTextField jTextFieldLineAddress;
    private javax.swing.JTextField jTextFieldMunicipio;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPais;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        //
        //Basico
        //
//        Bindings.bind(jComboBoxCOCINA, new SelectionInList<>(getPresenter().getModel(PROP_LISTA_ELABORADO), getPresenter().getModel(PROP_ELABORADO_SELECCIONADO)));
//        Bindings.bind(jComboBoxSECCION, new SelectionInList<>(getPresenter().getModel(PROP_LISTA_CATEGORIAS), getPresenter().getModel(PROP_CATEGORIA_SELECCIONADA)));
//        Bindings.bind(jTextFieldNombre, getPresenter().getModel(PROP_NOMBRE_PRODUCTO));
//        Bindings.bind(jTextFieldApellidos, getPresenter().getModel(PROP_PRECIO_VENTA));
//        Bindings.bind(jXLabelPCod, getPresenter().getModel(PROP_CODIGO_PRODUCTO));
//        Bindings.bind(jTextFieldAlias, getPresenter().getModel(PROP_PRECIO_COSTO));
//        jButtonAddCocina.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_PUNTO_ELABORACION));
//        jButtonAddSeccion.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_CATEGORIA));

        //
        //Inventario
        //
//        Bindings.bind(jCheckBoxInventariarProducto, getPresenter().getModel(PROP_CHECKBOX_INVENTARIAR_PRODUCTO));
//        Bindings.bind(jCheckBoxProductoElaborado, getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_ELABORADO));
//        Bindings.bind(jPanelCrossRef, "visible", getPresenter().getModel(PROP_CHECKBOX_PRODUCTO_ELABORADO));
//        Bindings.bind(jPanelInventario, "visible", getPresenter().getModel(PROP_CHECKBOX_INVENTARIAR_PRODUCTO));

        //TODO tabla y boton
//        jButtonAddInsumo.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR_INSUMO));
//        Bindings.bind(jXLabelGasto, getPresenter().getModel(PROP_PRECIO_COSTO));

        //
        //Otros
        //
//        Bindings.bind(jTextFieldPagoPorVenta, getPresenter().getModel(PROP_COMISION_POR_VENTA));
//        jButtonCancelar.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_CANCELAR));
//        jButtonCrear.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR));

        //jButtonCrear.setAction(getPresenter().getOperation(ProductoVentaDetailPresenter.ACTION_AGREGAR));
    }

    @Override
    public void uiInit() {
        initComponents();
        jTableHistorial.setModel(new BindableTableModel<Orden>(jTableHistorial) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return java.util.ResourceBundle.getBundle("Strings").getString("label_codigo");
                    case 1:
                        return "Fecha";
                    case 2:
                        return "Valor Monetario";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ((Orden) getListModel().getElementAt(rowIndex)).getCodOrden();
                    case 1:
                        return ((Orden) getListModel().getElementAt(rowIndex)).getHoraComenzada();
                    case 2:
                        return ((Orden) getListModel().getElementAt(rowIndex)).getOrdenvalorMonetario();
                }
                return null;
            }
        });
        jTableHistorial.getRowSorter().toggleSortOrder(0);

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
