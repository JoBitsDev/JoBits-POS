/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.reserva;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import static com.jobits.pos.ui.clientes.reserva.presenter.ClientesReservaDetailViewModel.*;
import static com.jobits.pos.ui.clientes.reserva.presenter.ClientesReservaDetailViewPresenter.*;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.pos.ui.utils.BindableTableModel;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jorge
 */
public class ClientesReservaDetailView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Detalles Cliente Reserva";

    public ClientesReservaDetailView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jCheckBoxInventariarProducto = MaterialComponentsFactory.Input.getCheckBox();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelInventario = new javax.swing.JPanel();
        jPanel9 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanelPersonalData1 = new javax.swing.JPanel();
        jPanel10 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelNombreApellidos1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldNombre = MaterialComponentsFactory.Input.getTextField("", "Nombre*");
        jTextFieldApellidos = MaterialComponentsFactory.Input.getTextField("", "Apellidos*");
        jPanelInfoPersonal1 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel6 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldTelefono = MaterialComponentsFactory.Input.getTextField(" ", java.util.ResourceBundle.getBundle("Strings").getString("label_telefono")+"*");
        jPanelAddress1 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel8 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldLineAddress = MaterialComponentsFactory.Input.getTextField("", java.util.ResourceBundle.getBundle("Strings").getString("label_direccion"));
        jTextFieldMunicipio = MaterialComponentsFactory.Input.getTextField("", "Municipio");
        jTextFieldCiudad = MaterialComponentsFactory.Input.getTextField(" ", "Ciudad");
        jPanel4 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelTable1 = new javax.swing.JPanel();
        jScrollPane1 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableHistorial = new javax.swing.JTable();
        jPanelOpciones1 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonCrear = MaterialComponentsFactory.Buttons.getAcceptButton();

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBoxInventariarProducto.setText("Inventariar Producto");
        jCheckBoxInventariarProducto.setContentAreaFilled(false);
        jPanel3.add(jCheckBoxInventariarProducto);
        jPanel3.add(filler6);

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(490, 620));
        setLayout(new java.awt.BorderLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jTabbedPane2.setOpaque(true);

        jPanelPersonalData1.setLayout(new javax.swing.BoxLayout(jPanelPersonalData1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelNombreApellidos1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 20));
        jPanelNombreApellidos1.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jTextFieldNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jPanelNombreApellidos1.add(jTextFieldNombre);

        jTextFieldApellidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldApellidos.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldApellidos.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldApellidos.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanelNombreApellidos1.add(jTextFieldApellidos);

        jPanel10.add(jPanelNombreApellidos1);

        jPanelPersonalData1.add(jPanel10);

        jPanelInfoPersonal1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20), "Detalles"));
        jPanelInfoPersonal1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(1, 1, 0, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/telefono_indigo.png"))); // NOI18N
        jPanel6.add(jLabel5);

        jPanelInfoPersonal1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 100));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 1, 0, 20));

        jTextFieldTelefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldTelefono.setPreferredSize(new java.awt.Dimension(150, 80));
        jTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(jTextFieldTelefono);

        jPanelInfoPersonal1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanelPersonalData1.add(jPanelInfoPersonal1);

        jPanelAddress1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20), bundle.getString("direccion"))); // NOI18N
        jPanelAddress1.setLayout(new java.awt.BorderLayout());

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

        jPanelAddress1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/calle_indigo.png"))); // NOI18N
        jPanel4.add(jLabel1);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/edificio_indigo.png"))); // NOI18N
        jPanel4.add(jLabel2);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/estado_indigo.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        jPanelAddress1.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanelPersonalData1.add(jPanelAddress1);

        jTabbedPane2.addTab("Datos Personales", jPanelPersonalData1);

        jPanelTable1.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        jPanelTable1.setMinimumSize(new java.awt.Dimension(438, 100));
        jPanelTable1.setLayout(new java.awt.BorderLayout());

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

        jPanelTable1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane2.addTab("Historial de Compra", jPanelTable1);

        jPanel9.add(jTabbedPane2, java.awt.BorderLayout.CENTER);

        jPanelOpciones1.setMinimumSize(new java.awt.Dimension(239, 70));
        jPanelOpciones1.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanelOpciones1.setLayout(new java.awt.GridBagLayout());

        jButtonCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanelOpciones1.add(jButtonCancelar, new java.awt.GridBagConstraints());

        jButtonCrear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCrear.setMnemonic('c');
        jButtonCrear.setText(bundle.getString("label_crear_producto")); // NOI18N
        jButtonCrear.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanelOpciones1.add(jButtonCrear, new java.awt.GridBagConstraints());

        jPanel9.add(jPanelOpciones1, java.awt.BorderLayout.SOUTH);

        add(jPanel9, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCiudadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCiudadKeyTyped

    private void jTextFieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoKeyTyped
        char c = evt.getKeyChar();
        int l = jTextFieldTelefono.getText().length();
        if (((l >= 8) || (c < '0') || (c > '9')) && (c != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JCheckBox jCheckBoxInventariarProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAddress1;
    private javax.swing.JPanel jPanelInfoPersonal1;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelNombreApellidos1;
    private javax.swing.JPanel jPanelOpciones1;
    private javax.swing.JPanel jPanelPersonalData1;
    private javax.swing.JPanel jPanelTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableHistorial;
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
        Bindings.bind(jTextFieldTelefono, getPresenter().getModel(PROP_TELEFONO));
        Bindings.bind(jTextFieldLineAddress, getPresenter().getModel(PROP_DIRECCION));
        Bindings.bind(jTextFieldMunicipio, getPresenter().getModel(PROP_MUNICIPIO));
        Bindings.bind(jTextFieldCiudad, getPresenter().getModel(PROP_CIUDAD));
        Bindings.bind(jTableHistorial, new SelectionInList<String>(
                getPresenter().getModel(PROP_LISTA_RESERVAS),
                getPresenter().getModel(PROP_RESERVA_SELECCIONADA)));
        jButtonCancelar.addActionListener(getPresenter().getOperation(ACTION_CANCELAR));
        jButtonCrear.addActionListener(getPresenter().getOperation(ACTION_AGREGAR));
    }

    @Override
    public void uiInit() {
        initComponents();
        jTableHistorial.setModel(new BindableTableModel<Reserva>(jTableHistorial) {
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
                        return "Fecha/Hora";
                    case 2:
                        return "Categoria";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Reserva r = ((Reserva) getListModel().getElementAt(rowIndex));
                switch (columnIndex) {
                    case 0:
                        return r.getIdreserva();
                    case 1:
                        return r.getFechareserva().format(DateTimeFormatter.ofPattern("d/MM/yyyy "))
                                + r.getHorareserva().format(DateTimeFormatter.ofPattern("hh/mm a"));
                    case 2:
                        return r.getCategoriaidcategoria();
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
