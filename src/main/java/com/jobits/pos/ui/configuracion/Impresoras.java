/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.servicios.impresion.Impresora;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.configuracion.presenter.ImpresorasViewModel;
import com.jobits.pos.ui.configuracion.presenter.ImpresorasViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.root101.swing.material.standards.MaterialIcons;

/**
 *
 * @author Home
 */
public class Impresoras extends AbstractViewPanel {

    public static final String VIEW_NAME = "Impresoras";

    /**
     * Creates new form Impresoras2
     *
     * @param presenter
     */
    public Impresoras(AbstractViewPresenter presenter) {
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelListaImpresoras = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel2 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jScrollPane1 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableImpresoras = new javax.swing.JTable();
        jPanelEliminar = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonEliminar = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanelNuevaImpresora = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel9 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelNombre = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextNombreImpresora = MaterialComponentsFactory.Input.getTextField("", "Nombre");
        jPanelSysPrinter = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxImpresorasSistema = MaterialComponentsFactory.Displayers.getComboBox("Impresoras Sistema");
        jPanelArea = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxGrupos = MaterialComponentsFactory.Displayers.getComboBox("Areas");
        jPanel1 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonAceptar = MaterialComponentsFactory.Buttons.getOutlinedButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(730, 525));
        setLayout(new java.awt.BorderLayout());

        jPanelListaImpresoras.setMinimumSize(new java.awt.Dimension(200, 250));
        jPanelListaImpresoras.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTableImpresoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableImpresoras);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelEliminar.setMinimumSize(new java.awt.Dimension(112, 40));
        jPanelEliminar.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanelEliminar.setLayout(new java.awt.GridBagLayout());

        jButtonEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanelEliminar.add(jButtonEliminar, new java.awt.GridBagConstraints());

        jPanel2.add(jPanelEliminar, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jToggleButton1.setIcon(MaterialIcons.ARROW_DROP_LEFT);
        jToggleButton1.setToolTipText("Agregar Impresora");
        jToggleButton1.setMaximumSize(new java.awt.Dimension(20, 20));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(20, 20));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jToggleButton1.setSelectedIcon(MaterialIcons.ARROW_DROP_RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        jPanel3.add(jToggleButton1, gridBagConstraints);

        jPanel2.add(jPanel3, java.awt.BorderLayout.EAST);

        jPanelListaImpresoras.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanelNuevaImpresora.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 20, 20));
        jPanelNuevaImpresora.setPreferredSize(new java.awt.Dimension(210, 51));
        jPanelNuevaImpresora.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanelNombre.setLayout(new java.awt.GridBagLayout());

        jTextNombreImpresora.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextNombreImpresora.setMaximumSize(new java.awt.Dimension(999999, 60));
        jTextNombreImpresora.setMinimumSize(new java.awt.Dimension(150, 26));
        jTextNombreImpresora.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanelNombre.add(jTextNombreImpresora, new java.awt.GridBagConstraints());

        jPanel9.add(jPanelNombre);

        jPanelSysPrinter.setLayout(new java.awt.GridBagLayout());

        jComboBoxImpresorasSistema.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jComboBoxImpresorasSistema.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanelSysPrinter.add(jComboBoxImpresorasSistema, new java.awt.GridBagConstraints());

        jPanel9.add(jPanelSysPrinter);

        jPanelArea.setLayout(new java.awt.GridBagLayout());

        jComboBoxGrupos.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jComboBoxGrupos.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanelArea.add(jComboBoxGrupos, new java.awt.GridBagConstraints());

        jPanel9.add(jPanelArea);

        jPanelNuevaImpresora.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAceptar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonAceptar.setText("Agregar");
        jButtonAceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAceptar.setPreferredSize(new java.awt.Dimension(102, 40));
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAceptar, new java.awt.GridBagConstraints());

        jPanelNuevaImpresora.add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanelListaImpresoras.add(jPanelNuevaImpresora, java.awt.BorderLayout.EAST);

        add(jPanelListaImpresoras, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
    }//GEN-LAST:event_jButtonAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JComboBox<String> jComboBoxGrupos;
    private javax.swing.JComboBox<String> jComboBoxImpresorasSistema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelArea;
    private javax.swing.JPanel jPanelEliminar;
    private javax.swing.JPanel jPanelListaImpresoras;
    private javax.swing.JPanel jPanelNombre;
    private javax.swing.JPanel jPanelNuevaImpresora;
    private javax.swing.JPanel jPanelSysPrinter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableImpresoras;
    private javax.swing.JTextField jTextNombreImpresora;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jTableImpresoras, new SelectionInList<String>(
                getPresenter().getModel(ImpresorasViewModel.PROP_LISTA_IMPRESORAS),
                getPresenter().getModel(ImpresorasViewModel.PROP_IMPRESORA_SELECCIONADA)));

        Bindings.bind(jTextNombreImpresora, getPresenter().getModel(ImpresorasViewModel.PROP_NOMBRE_IMPRESORA_ACTUAL));
        Bindings.bind(jComboBoxImpresorasSistema, new SelectionInList<String>(
                getPresenter().getModel(ImpresorasViewModel.PROP_LISTA_IMPRESORAS_SISTEMA),
                getPresenter().getModel(ImpresorasViewModel.PROP_IMPRESORA_SISTEMA_SELECCIONADA)));
        Bindings.bind(jComboBoxGrupos, new SelectionInList<String>(
                getPresenter().getModel(ImpresorasViewModel.PROP_LISTA_GRUPO),
                getPresenter().getModel(ImpresorasViewModel.PROP_GRUPO_SELECCIONADO)));

        Bindings.bind(jPanelNuevaImpresora, "visible", getPresenter().getModel(ImpresorasViewModel.PROP_PANEL_VISIBLE));

        jToggleButton1.addActionListener(getPresenter().getOperation(ImpresorasViewPresenter.ACTION_SET_PANEL_VISIBLE));
        jButtonAceptar.addActionListener(getPresenter().getOperation(ImpresorasViewPresenter.ACTION_AGREGAR));
        jButtonEliminar.addActionListener(getPresenter().getOperation(ImpresorasViewPresenter.ACTION_ELIMINAR));

    }

    @Override
    public void uiInit() {
        initComponents();
        jTableImpresoras.setModel(new BindableTableModel<Impresora>(jTableImpresoras) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Nombre";
                    case 1:
                        return "Impresora";
                    case 2:
                        return "Grupo";
                    case 3:
                        return "Defecto";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getNombreImpresoraVirtual();
                    case 1:
                        return getRow(rowIndex).getNombreImpresoraSistema();
                    case 2:
                        return getRow(rowIndex).getGrupo();
                    case 3:
                        return getRow(rowIndex).isPorDefecto();
                }
                return null;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 3;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 3) {
                    getPresenter().getOperation(ImpresorasViewPresenter.ACTION_CHANGE_DEFAULT).doAction();
                }
            }
        });
        jTableImpresoras.getRowSorter().toggleSortOrder(0);
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
