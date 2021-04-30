/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.backup;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.backup.presenter.BackUpViewModel;
import com.jobits.pos.ui.backup.presenter.BackUpViewPresenter;
import static com.jobits.pos.ui.backup.presenter.BackUpViewPresenter.*;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import org.jobits.db.core.domain.ConexionPropertiesModel;

/**
 *
 * @author Jorge
 */
public class BackUpView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Copias de seguridad";

    /**
     * Creates new form BackUpView
     *
     * @param presenter
     */
    public BackUpView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel3 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonClose = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanel2 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBox1 = MaterialComponentsFactory.Displayers.getComboBox("Copiar a:");
        jPanel6 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jButtonRealizarCopia = MaterialComponentsFactory.Buttons.getMaterialButton();
        jPanel5 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jCheckBoxProductos = new javax.swing.JCheckBox();
        jCheckBoxPersonal = new javax.swing.JCheckBox();
        jCheckBoxVentas = new javax.swing.JCheckBox();
        jCheckBoxTodo = new javax.swing.JCheckBox();
        jCheckBoxBorrado = new javax.swing.JCheckBox();
        jProgressBar1 = new javax.swing.JProgressBar();

        setMinimumSize(new java.awt.Dimension(400, 450));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(400, 450));
        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonClose.setIcon(MaterialIcons.CLOSE);
        jButtonClose.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonClose.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonClose.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel3.add(jButtonClose);

        jPanel4.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 20, 20));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 400));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setPreferredSize(new java.awt.Dimension(33, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jXLabel1.setText(bundle.getString("label_realizar_copia_de")); // NOI18N
        jPanel6.add(jXLabel1, java.awt.BorderLayout.PAGE_START);

        jButtonRealizarCopia.setText("Realizar copia de seguridad");
        jPanel6.add(jButtonRealizarCopia, java.awt.BorderLayout.SOUTH);

        jPanel5.setLayout(new java.awt.GridLayout(5, 1));

        jCheckBoxProductos.setText(bundle.getString("boton_ProductosdeVenta")); // NOI18N
        jPanel5.add(jCheckBoxProductos);

        jCheckBoxPersonal.setText(bundle.getString("boton_personal")); // NOI18N
        jPanel5.add(jCheckBoxPersonal);

        jCheckBoxVentas.setText(bundle.getString("boton_ventas")); // NOI18N
        jPanel5.add(jCheckBoxVentas);

        jCheckBoxTodo.setText(bundle.getString("boton_todos")); // NOI18N
        jPanel5.add(jCheckBoxTodo);

        jCheckBoxBorrado.setForeground(new java.awt.Color(204, 0, 0));
        jCheckBoxBorrado.setText(bundle.getString("boton_borrado")); // NOI18N
        jCheckBoxBorrado.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jCheckBoxBorrado.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBoxBorrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBorradoActionPerformed(evt);
            }
        });
        jPanel5.add(jCheckBoxBorrado);

        jPanel6.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 30));
        jProgressBar1.setStringPainted(true);
        jPanel2.add(jProgressBar1, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxBorradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBorradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxBorradoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonRealizarCopia;
    private javax.swing.JCheckBox jCheckBoxBorrado;
    private javax.swing.JCheckBox jCheckBoxPersonal;
    private javax.swing.JCheckBox jCheckBoxProductos;
    private javax.swing.JCheckBox jCheckBoxTodo;
    private javax.swing.JCheckBox jCheckBoxVentas;
    private javax.swing.JComboBox<ConexionPropertiesModel> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jComboBox1, new SelectionInList<ConexionPropertiesModel>(
                getPresenter().getModel(BackUpViewModel.PROP_LISTA_UBICACIONES),
                getPresenter().getModel(BackUpViewModel.PROP_UBICACION_SELECCIONADA)));
        Bindings.bind(jCheckBoxTodo, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_TODO));
        Bindings.bind(jCheckBoxBorrado, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_LIMPIEZA_SERVIDOR));
        Bindings.bind(jCheckBoxPersonal, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_USUARIOS));
        Bindings.bind(jCheckBoxProductos, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_PRODUCTOS));
        Bindings.bind(jCheckBoxVentas, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_VENTAS));
        Bindings.bind(jProgressBar1, "value", getPresenter().getModel(BackUpViewModel.PROP_PROGRESS_INDICATOR));
        jButtonClose.addActionListener(getPresenter().getOperation(ACTION_CERRAR));
        jButtonRealizarCopia.addActionListener(getPresenter().getOperation(BackUpViewPresenter.ACTION_REALIZAR_COPIA_SEG));
    }

    @Override
    public void uiInit() {
        initComponents();

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}
