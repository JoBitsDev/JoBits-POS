/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.backup;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jhw.swing.material.standars.MaterialIcons;
import com.jobits.pos.core.domain.UbicacionConexionModel;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.backup.presenter.BackUpViewModel;
import com.jobits.pos.ui.backup.presenter.BackUpViewPresenter;
import static com.jobits.pos.ui.backup.presenter.BackUpViewPresenter.*;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;

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

        jPanel3 = new javax.swing.JPanel();
        jButtonClose = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        jCheckBoxProductos = new javax.swing.JCheckBox();
        jCheckBoxPersonal = new javax.swing.JCheckBox();
        jCheckBoxVentas = new javax.swing.JCheckBox();
        jCheckBoxTodo = new javax.swing.JCheckBox();
        jCheckBoxBorrado = new javax.swing.JCheckBox();
        botonRealizarCopia = new org.pushingpixels.substance.internal.utils.SubstanceTitleButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setBorder(javax.swing.BorderFactory.createLineBorder(DefaultValues.PRIMARY_COLOR_LIGHT));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonClose.setIcon(MaterialIcons.CLOSE);
        jButtonClose.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonClose.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonClose.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel3.add(jButtonClose);

        add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 400));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Copiar a"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.add(jComboBox1, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jXPanel1.setLayout(new java.awt.BorderLayout());

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jXLabel1.setText(bundle.getString("label_realizar_copia_de")); // NOI18N
        jXPanel1.add(jXLabel1, java.awt.BorderLayout.PAGE_START);

        jXPanel2.setLayout(new java.awt.GridLayout(0, 1));

        jCheckBoxProductos.setText(bundle.getString("boton_ProductosdeVenta")); // NOI18N
        jXPanel2.add(jCheckBoxProductos);

        jCheckBoxPersonal.setText(bundle.getString("boton_personal")); // NOI18N
        jXPanel2.add(jCheckBoxPersonal);

        jCheckBoxVentas.setText(bundle.getString("boton_ventas")); // NOI18N
        jXPanel2.add(jCheckBoxVentas);

        jCheckBoxTodo.setText(bundle.getString("boton_todos")); // NOI18N
        jXPanel2.add(jCheckBoxTodo);

        jCheckBoxBorrado.setForeground(new java.awt.Color(204, 0, 0));
        jCheckBoxBorrado.setText(bundle.getString("boton_borrado")); // NOI18N
        jCheckBoxBorrado.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jCheckBoxBorrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBorradoActionPerformed(evt);
            }
        });
        jXPanel2.add(jCheckBoxBorrado);

        jXPanel1.add(jXPanel2, java.awt.BorderLayout.CENTER);

        botonRealizarCopia.setText(bundle.getString("boton_realizar_copia")); // NOI18N
        botonRealizarCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRealizarCopiaActionPerformed(evt);
            }
        });
        jXPanel1.add(botonRealizarCopia, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jXPanel1, java.awt.BorderLayout.CENTER);

        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 30));
        jProgressBar1.setStringPainted(true);
        jPanel2.add(jProgressBar1, java.awt.BorderLayout.PAGE_END);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRealizarCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRealizarCopiaActionPerformed


    }//GEN-LAST:event_botonRealizarCopiaActionPerformed

    private void jCheckBoxBorradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBorradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxBorradoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.pushingpixels.substance.internal.utils.SubstanceTitleButton botonRealizarCopia;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JCheckBox jCheckBoxBorrado;
    private javax.swing.JCheckBox jCheckBoxPersonal;
    private javax.swing.JCheckBox jCheckBoxProductos;
    private javax.swing.JCheckBox jCheckBoxTodo;
    private javax.swing.JCheckBox jCheckBoxVentas;
    private javax.swing.JComboBox<UbicacionConexionModel> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jComboBox1, new SelectionInList<UbicacionConexionModel>(
                getPresenter().getModel(BackUpViewModel.PROP_LISTA_UBICACIONES),
                getPresenter().getModel(BackUpViewModel.PROP_UBICACION_SELECCIONADA)));
        Bindings.bind(jCheckBoxTodo, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_TODO));
        Bindings.bind(jCheckBoxBorrado, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_LIMPIEZA_SERVIDOR));
        Bindings.bind(jCheckBoxPersonal, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_USUARIOS));
        Bindings.bind(jCheckBoxProductos, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_PRODUCTOS));
        Bindings.bind(jCheckBoxVentas, getPresenter().getModel(BackUpViewModel.PROP_CHECKBOX_VENTAS));
        this.botonRealizarCopia.addActionListener(getPresenter().getOperation(BackUpViewPresenter.ACTION_REALIZAR_COPIA_SEG));
        Bindings.bind(jProgressBar1, "value", getPresenter().getModel(BackUpViewModel.PROP_PROGRESS_INDICATOR));
        jButtonClose.addActionListener(getPresenter().getOperation(ACTION_CERRAR));
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
