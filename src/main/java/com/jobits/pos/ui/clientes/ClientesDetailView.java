/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.cliente.core.domain.DireccionEnvioDomain;
import com.jobits.pos.ui.AbstractViewPanel;
import static com.jobits.pos.ui.clientes.presenter.ClientesDetailViewModel.*;
import static com.jobits.pos.ui.clientes.presenter.ClientesDetailViewPresenter.*;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.CardLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

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
        jPanelAddress = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel8 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldLineAddress = MaterialComponentsFactory.Input.getTextField("", java.util.ResourceBundle.getBundle("Strings").getString("label_direccion"));
        jTextFieldMunicipio = MaterialComponentsFactory.Input.getTextField("", "Municipio");
        jTextFieldCiudad = MaterialComponentsFactory.Input.getTextField(" ", "Ciudad");
        jPanel4 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonEdit = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanel9 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelPersonalData = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel7 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelNombreApellidos = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldNombre = MaterialComponentsFactory.Input.getTextField("", "Nombre*");
        jTextFieldApellidos = MaterialComponentsFactory.Input.getTextField("", "Apellidos");
        jPanelInfoPersonal = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel2 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldTelefono = MaterialComponentsFactory.Input.getTextField(" ", java.util.ResourceBundle.getBundle("Strings").getString("label_telefono")+"*");
        jTextFieldAlias = MaterialComponentsFactory.Input.getTextField("", "Alias");
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel6 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanelTable = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel5 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonDelete = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonToAdd = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jScrollPaneDirecciones = MaterialComponentsFactory.Containers.getScrollPane();
        jListDirecciones = new javax.swing.JList<>();
        jPanel10 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel11 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jTextFieldNombreDirEn = MaterialComponentsFactory.Input.getTextField(" ", "Nombre*");
        jTextFieldApellidosDirEn = MaterialComponentsFactory.Input.getTextField(" ", "Apellidos");
        jTextFieldTelefonoDirEn = MaterialComponentsFactory.Input.getTextField(" ", java.util.ResourceBundle.getBundle("Strings").getString("label_telefono_movil")+"*");
        jTextFieldAliasDirEn = MaterialComponentsFactory.Input.getTextField(" ", "Alias*");
        jPanel12 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jTextFieldDireccion = MaterialComponentsFactory.Input.getTextField(" ", "Direccion*");
        jTextFieldDireccionAdicional = MaterialComponentsFactory.Input.getTextField(" ", "Direccion Adicional");
        jPanel13 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldCiudadDirEn = MaterialComponentsFactory.Input.getTextField(" ", "Ciudad*");
        jTextFieldProvinciaDirEn = MaterialComponentsFactory.Input.getTextField(" ", "Provincia*");
        jPanel15 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldEmpresaDirEn = MaterialComponentsFactory.Input.getTextField(" ", "Empresa");
        jPanel14 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonAdd = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanelOpciones = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonCrear = MaterialComponentsFactory.Buttons.getAcceptButton();

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBoxInventariarProducto.setText("Inventariar Producto");
        jCheckBoxInventariarProducto.setContentAreaFilled(false);
        jPanel3.add(jCheckBoxInventariarProducto);
        jPanel3.add(filler6);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jPanelAddress.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20), bundle.getString("direccion"))); // NOI18N
        jPanelAddress.setPreferredSize(new java.awt.Dimension(519, 300));
        jPanelAddress.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 100));
        jPanel8.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

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

        jPanelAddress.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/calle_indigo.png"))); // NOI18N
        jPanel4.add(jLabel1);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/edificio_indigo.png"))); // NOI18N
        jPanel4.add(jLabel2);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/estado_indigo.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        jPanelAddress.add(jPanel4, java.awt.BorderLayout.WEST);

        jButtonEdit.setIcon(MaterialIcons.EDIT);
        jButtonEdit.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonEdit.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonEdit.setPreferredSize(new java.awt.Dimension(40, 40));

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(625, 575));
        setMinimumSize(new java.awt.Dimension(625, 575));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(625, 575));
        setLayout(new java.awt.BorderLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setOpaque(true);

        jPanelPersonalData.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelPersonalData.setLayout(new javax.swing.BoxLayout(jPanelPersonalData, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelNombreApellidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 20));
        jPanelNombreApellidos.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jTextFieldNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldNombre.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jPanelNombreApellidos.add(jTextFieldNombre);

        jTextFieldApellidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldApellidos.setToolTipText(bundle.getString("tooltip_Nombre")); // NOI18N
        jTextFieldApellidos.setMinimumSize(new java.awt.Dimension(250, 60));
        jTextFieldApellidos.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanelNombreApellidos.add(jTextFieldApellidos);

        jPanel7.add(jPanelNombreApellidos);

        jPanelPersonalData.add(jPanel7);

        jPanelInfoPersonal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20), "Detalles"));
        jPanelInfoPersonal.setPreferredSize(new java.awt.Dimension(519, 300));
        jPanelInfoPersonal.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 100));
        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

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

        jPanelInfoPersonal.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jPanel6.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/telefono_indigo.png"))); // NOI18N
        jPanel6.add(jLabel5);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/hombre_indigo.png"))); // NOI18N
        jPanel6.add(jLabel6);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/cumpleanos_indigo.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel6.add(jLabel7);

        jPanelInfoPersonal.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanelPersonalData.add(jPanelInfoPersonal);

        jTabbedPane1.addTab("Datos Personales", jPanelPersonalData);

        jPanelTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelTable.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        jPanelTable.setMinimumSize(new java.awt.Dimension(438, 100));
        jPanelTable.setLayout(new java.awt.CardLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        jButtonDelete.setIcon(MaterialIcons.DELETE);
        jButtonDelete.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jButtonDelete);

        jButtonToAdd.setIcon(MaterialIcons.ADD_CIRCLE);
        jButtonToAdd.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonToAdd.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonToAdd.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jButtonToAdd);

        jPanel5.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPaneDirecciones.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneDirecciones.setPreferredSize(new java.awt.Dimension(200, 120));

        jListDirecciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListDirecciones.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jListDirecciones.setVisibleRowCount(-1);
        jScrollPaneDirecciones.setViewportView(jListDirecciones);

        jPanel5.add(jScrollPaneDirecciones, java.awt.BorderLayout.CENTER);

        jPanelTable.add(jPanel5, "Tabla");

        jPanel10.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 30, 15, 30));
        jPanel11.setMinimumSize(new java.awt.Dimension(391, 130));
        jPanel11.setPreferredSize(new java.awt.Dimension(391, 130));
        jPanel11.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        jTextFieldNombreDirEn.setToolTipText("El nombre no debe exceder de 30 caracteres");
        jTextFieldNombreDirEn.setBorder(null);
        jTextFieldNombreDirEn.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldNombreDirEn.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextFieldNombreDirEn.setName(""); // NOI18N
        jTextFieldNombreDirEn.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel11.add(jTextFieldNombreDirEn);

        jTextFieldApellidosDirEn.setBorder(null);
        jTextFieldApellidosDirEn.setMaximumSize(new java.awt.Dimension(150, 50));
        jTextFieldApellidosDirEn.setMinimumSize(new java.awt.Dimension(150, 50));
        jTextFieldApellidosDirEn.setPreferredSize(new java.awt.Dimension(170, 50));
        jPanel11.add(jTextFieldApellidosDirEn);

        jTextFieldTelefonoDirEn.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldTelefonoDirEn.setPreferredSize(new java.awt.Dimension(150, 80));
        jTextFieldTelefonoDirEn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoDirEnKeyTyped(evt);
            }
        });
        jPanel11.add(jTextFieldTelefonoDirEn);

        jTextFieldAliasDirEn.setBorder(null);
        jTextFieldAliasDirEn.setMaximumSize(new java.awt.Dimension(150, 50));
        jTextFieldAliasDirEn.setMinimumSize(new java.awt.Dimension(150, 50));
        jTextFieldAliasDirEn.setPreferredSize(new java.awt.Dimension(170, 50));
        jPanel11.add(jTextFieldAliasDirEn);

        jPanel10.add(jPanel11, java.awt.BorderLayout.NORTH);

        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 30, 15, 30));
        jPanel12.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        jTextFieldDireccion.setToolTipText("El nombre no debe exceder de 30 caracteres");
        jTextFieldDireccion.setBorder(null);
        jTextFieldDireccion.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldDireccion.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextFieldDireccion.setName(""); // NOI18N
        jTextFieldDireccion.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel12.add(jTextFieldDireccion);

        jTextFieldDireccionAdicional.setToolTipText("El nombre no debe exceder de 30 caracteres");
        jTextFieldDireccionAdicional.setBorder(null);
        jTextFieldDireccionAdicional.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldDireccionAdicional.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextFieldDireccionAdicional.setName(""); // NOI18N
        jTextFieldDireccionAdicional.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel12.add(jTextFieldDireccionAdicional);

        jPanel13.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jTextFieldCiudadDirEn.setBorder(null);
        jTextFieldCiudadDirEn.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldCiudadDirEn.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextFieldCiudadDirEn.setName(""); // NOI18N
        jTextFieldCiudadDirEn.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel13.add(jTextFieldCiudadDirEn);

        jTextFieldProvinciaDirEn.setBorder(null);
        jTextFieldProvinciaDirEn.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldProvinciaDirEn.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextFieldProvinciaDirEn.setName(""); // NOI18N
        jTextFieldProvinciaDirEn.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel13.add(jTextFieldProvinciaDirEn);

        jPanel12.add(jPanel13);

        jPanel15.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jTextFieldEmpresaDirEn.setToolTipText("El nombre no debe exceder de 30 caracteres");
        jTextFieldEmpresaDirEn.setBorder(null);
        jTextFieldEmpresaDirEn.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldEmpresaDirEn.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextFieldEmpresaDirEn.setName(""); // NOI18N
        jTextFieldEmpresaDirEn.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel15.add(jTextFieldEmpresaDirEn);

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 0, 100));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setIcon(MaterialIcons.ADD_CIRCLE);
        jButtonAdd.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel14.add(jButtonAdd, new java.awt.GridBagConstraints());

        jPanel15.add(jPanel14);

        jPanel12.add(jPanel15);

        jPanel10.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanelTable.add(jPanel10, "Agregar");

        jTabbedPane1.addTab("Direcciones de Envío", jPanelTable);

        jPanel9.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanelOpciones.setMinimumSize(new java.awt.Dimension(239, 70));
        jPanelOpciones.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanelOpciones.setLayout(new java.awt.GridBagLayout());

        jButtonCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanelOpciones.add(jButtonCancelar, new java.awt.GridBagConstraints());

        jButtonCrear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCrear.setMnemonic('c');
        jButtonCrear.setText(bundle.getString("label_crear_producto")); // NOI18N
        jButtonCrear.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanelOpciones.add(jButtonCrear, new java.awt.GridBagConstraints());

        jPanel9.add(jPanelOpciones, java.awt.BorderLayout.SOUTH);

        add(jPanel9, java.awt.BorderLayout.CENTER);
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

    private void jTextFieldTelefonoDirEnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoDirEnKeyTyped
        char c = evt.getKeyChar();
        int l = jTextFieldTelefonoDirEn.getText().length();
        if (((l >= 8) || (c < '0') || (c > '9')) && (c != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoDirEnKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonToAdd;
    private javax.swing.JCheckBox jCheckBoxInventariarProducto;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<DireccionEnvioDomain> jListDirecciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAddress;
    private javax.swing.JPanel jPanelInfoPersonal;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelNombreApellidos;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPersonalData;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPaneDirecciones;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldAlias;
    private javax.swing.JTextField jTextFieldAliasDirEn;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldApellidosDirEn;
    private javax.swing.JTextField jTextFieldCiudad;
    private javax.swing.JTextField jTextFieldCiudadDirEn;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldDireccionAdicional;
    private javax.swing.JTextField jTextFieldEmpresaDirEn;
    private javax.swing.JTextField jTextFieldLineAddress;
    private javax.swing.JTextField jTextFieldMunicipio;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombreDirEn;
    private javax.swing.JTextField jTextFieldProvinciaDirEn;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTelefonoDirEn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jTextFieldNombre, getPresenter().getModel(PROP_NOMBRE));
        Bindings.bind(jTextFieldApellidos, getPresenter().getModel(PROP_APELLIDOS));
        Bindings.bind(jTextFieldAlias, getPresenter().getModel(PROP_ALIAS));
        Bindings.bind(jTextFieldTelefono, getPresenter().getModel(PROP_TELEFONO));
        Bindings.bind(jDateChooser1, "date", getPresenter().getModel(PROP_CUMPLEANOS));
//        Bindings.bind(jTextFieldLineAddress, getPresenter().getModel(PROP_DIRECCION));
//        Bindings.bind(jTextFieldMunicipio, getPresenter().getModel(PROP_MUNICIPIO));
//        Bindings.bind(jTextFieldCiudad, getPresenter().getModel(PROP_CIUDAD));
        Bindings.bind(jListDirecciones, new SelectionInList<DireccionEnvioDomain>(
                getPresenter().getModel(PROP_LISTA_DIRECCIONES_ENVIO),
                getPresenter().getModel(PROP_DIRECCION_ENVIO_SELECCIONADA)));

        Bindings.bind(jTextFieldAliasDirEn, getPresenter().getModel(PROP_DE_ALIAS));
        Bindings.bind(jTextFieldNombreDirEn, getPresenter().getModel(PROP_DE_NOMBRE));
        Bindings.bind(jTextFieldApellidosDirEn, getPresenter().getModel(PROP_DE_APELLIDOS));
        Bindings.bind(jTextFieldCiudadDirEn, getPresenter().getModel(PROP_DE_CIUDAD));
        Bindings.bind(jTextFieldProvinciaDirEn, getPresenter().getModel(PROP_DE_PROVINCIA));
        Bindings.bind(jTextFieldTelefonoDirEn, getPresenter().getModel(PROP_DE_TELEFONO));
        Bindings.bind(jTextFieldEmpresaDirEn, getPresenter().getModel(PROP_DE_EMPRESA));
        Bindings.bind(jTextFieldDireccion, getPresenter().getModel(PROP_DE_DIRECCION));
        Bindings.bind(jTextFieldDireccionAdicional, getPresenter().getModel(PROP_DE_DIRECCION_ADICIONAL));

        jButtonCancelar.addActionListener(getPresenter().getOperation(ACTION_CANCELAR));
        jButtonCrear.addActionListener(getPresenter().getOperation(ACTION_AGREGAR));
        jButtonToAdd.addActionListener(getPresenter().getOperation(ACTION_SWITCH_TO_AGREGAR_DIRECCION_ENVIO));
        jButtonAdd.addActionListener(getPresenter().getOperation(ACTION_AGREGAR_DIRECCION_ENVIO));
        jButtonDelete.addActionListener(getPresenter().getOperation(ACTION_ELIMINAR_DIRECCION_ENVIO));
    }

    @Override
    public void uiInit() {
        initComponents();
        jListDirecciones.setCellRenderer(new ListCellRenderer<DireccionEnvioDomain>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends DireccionEnvioDomain> list, DireccionEnvioDomain value, int index, boolean isSelected, boolean cellHasFocus) {
                return new CellRenderDireccionEnvio(value, isSelected);
            }
        });

        getPresenter().addPropertyChangeListener(ACTION_AGREGAR_DIRECCION_ENVIO, (PropertyChangeEvent evt) -> {
            changeToTable();
        });
        getPresenter().addPropertyChangeListener(ACTION_SWITCH_TO_AGREGAR_DIRECCION_ENVIO, (PropertyChangeEvent evt) -> {
            changeToAdd();
        });
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    private void changeToTable() {
        CardLayout cards = (CardLayout) jPanelTable.getLayout();
        cards.show(jPanelTable, "Tabla");
    }

    private void changeToAdd() {
        CardLayout cards = (CardLayout) jPanelTable.getLayout();
        cards.show(jPanelTable, "Agregar");
    }

}
