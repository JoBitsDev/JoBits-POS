/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import static com.jobits.pos.ui.clientes.presenter.ClientesDetailViewModel.*;
import com.jobits.pos.ui.clientes.presenter.ClientesDetailViewPresenter;
import com.jobits.pos.ui.configuracion.presenter.ImpresorasViewModel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.pos.ui.utils.BindableTableModel;

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
        jPanel7 = new javax.swing.JPanel();
        jPanelNombreApellidos = new javax.swing.JPanel();
        jTextFieldNombre = MaterialComponentsFactory.Input.getTextField("", "Nombre*");
        jTextFieldApellidos = MaterialComponentsFactory.Input.getTextField("", "Apellidos*");
        jPanelInfoPersonal = new javax.swing.JPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldTelefono = MaterialComponentsFactory.Input.getTextField(" ", java.util.ResourceBundle.getBundle("Strings").getString("label_telefono")+"*");
        jTextFieldAlias = MaterialComponentsFactory.Input.getTextField("", "Alias");
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanelAddress = new javax.swing.JPanel();
        jPanel5 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldLineAddress = MaterialComponentsFactory.Input.getTextField("", java.util.ResourceBundle.getBundle("Strings").getString("label_direccion"));
        jTextFieldMunicipio = MaterialComponentsFactory.Input.getTextField("", "Municipio");
        jTextFieldCiudad = MaterialComponentsFactory.Input.getTextField(" ", "Ciudad");
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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
        setPreferredSize(new java.awt.Dimension(500, 720));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setOpaque(true);

        jPanelPersonalData.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelPersonalData.setLayout(new javax.swing.BoxLayout(jPanelPersonalData, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelNombreApellidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 20));
        jPanelNombreApellidos.setOpaque(false);
        jPanelNombreApellidos.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jTextFieldNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jPanelNombreApellidos.add(jTextFieldNombre);

        jTextFieldApellidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldApellidos.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldApellidos.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldApellidos.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanelNombreApellidos.add(jTextFieldApellidos);

        jPanel7.add(jPanelNombreApellidos);

        jPanelPersonalData.add(jPanel7);

        jPanelInfoPersonal.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles"));
        jPanelInfoPersonal.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setMinimumSize(new java.awt.Dimension(541, 200));
        jPanel1.setLayout(new java.awt.BorderLayout());

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
        jDateChooser1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jDateChooser1.setOpaque(false);
        jDateChooser1.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel2.add(jDateChooser1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/telefono_indigo.png"))); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel5.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel6.add(jLabel5);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/hombre_indigo.png"))); // NOI18N
        jLabel6.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel6.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel6.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel6.add(jLabel6);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/cumpleanos_indigo.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jLabel7.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel7.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel6.add(jLabel7);

        jPanel1.add(jPanel6, java.awt.BorderLayout.LINE_START);

        jPanelInfoPersonal.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanelPersonalData.add(jPanelInfoPersonal);

        jPanelAddress.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("direccion"))); // NOI18N
        jPanelAddress.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel5.setMinimumSize(new java.awt.Dimension(541, 200));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 100));
        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

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

        jPanel5.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/calle_indigo.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel1.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel4.add(jLabel1);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/edificio_indigo.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel2.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel4.add(jLabel2);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/estado_indigo.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel4.add(jLabel3);

        jPanel5.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanelAddress.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanelPersonalData.add(jPanelAddress);

        jTabbedPane1.addTab("Datos Personales", jPanelPersonalData);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JCheckBox jCheckBoxInventariarProducto;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAddress;
    private javax.swing.JPanel jPanelInfoPersonal;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelNombreApellidos;
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
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jTextFieldNombre, getPresenter().getModel(PROP_NOMBRE));
        Bindings.bind(jTextFieldApellidos, getPresenter().getModel(PROP_APELLIDOS));
        Bindings.bind(jTextFieldAlias, getPresenter().getModel(PROP_ALIAS));
        Bindings.bind(jTextFieldTelefono, getPresenter().getModel(PROP_TELEFONO));
        Bindings.bind(jDateChooser1, "date", getPresenter().getModel(PROP_CUMPLEANOS));
        Bindings.bind(jTextFieldLineAddress, getPresenter().getModel(PROP_DIRECCION));
        Bindings.bind(jTextFieldMunicipio, getPresenter().getModel(PROP_MUNICIPIO));
        Bindings.bind(jTextFieldCiudad, getPresenter().getModel(PROP_CIUDAD));
        Bindings.bind(jTableHistorial, new SelectionInList<String>(
                getPresenter().getModel(PROP_LISTA_ORDENES),
                getPresenter().getModel(PROP_ORDEN_SELECCIONADA)));
        jButtonCancelar.addActionListener(getPresenter().getOperation(ClientesDetailViewPresenter.ACTION_CANCELAR));
        jButtonCrear.addActionListener(getPresenter().getOperation(ClientesDetailViewPresenter.ACTION_AGREGAR));
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
