/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta;

import com.jobits.pos.ui.venta.mesas.MesaListView;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import java.awt.Color;
import java.util.Date;
import javax.swing.JFileChooser;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.gastos.GastosView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.trabajadores.AsistenciaPersonalView;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.utils.utils;
import com.jobits.pos.ui.venta.orden.VentaListOrdenesView;
import com.jobits.pos.core.domain.venta.ResumenVentaAreaTablaModel;
import com.jobits.pos.core.domain.venta.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.core.domain.venta.ResumenVentaUsuarioTablaModel;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewModel.*;
import com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter.*;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.ui.components.swing.displayers.Card;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.Dimension;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class VentaDetailView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Comenzar Turno";
    String insumos, salarios, otro, ventasTotal;
    Date fechaFin;
    MesaListView mesaView;
    private JFileChooser fileChooser;

    public VentaDetailView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelResumenDetallado = new javax.swing.JPanel();
        jTabbedPaneResumen = new javax.swing.JTabbedPane();
        jComboBoxSeleccionarVentaPorTurno = new javax.swing.JComboBox<>();
        jPanelExtracciones = new javax.swing.JPanel();
        jPanelRoot = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelData = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTabbedPaneData = MaterialComponentsFactory.Containers.getTabPane();
        jPanelResumen = new javax.swing.JPanel();
        jTabbedPaneResumenD = MaterialComponentsFactory.Containers.getTabPane();
        jPanelGeneral = new javax.swing.JPanel();
        jPanelGraficaPieGenerales = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel15 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel14 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelVentas1 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanelVentasCards = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelGastos = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanelGastosCards = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel2 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldPropina = MaterialComponentsFactory.Input.getTextFielPrecioVenta("", "Propina", R.COIN_SUFFIX);
        jPanelVentasArea = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jScrollPane6 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasPorArea = new javax.swing.JTable();
        jPanel7 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonImprimirResumenArea = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelDependientes = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jScrollPane3 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasDependientes = new javax.swing.JTable();
        jPanel4 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonImprimirDptes = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonImpPagoVentas = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelPtoElab = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jScrollPane5 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasPorCocina = new javax.swing.JTable();
        jPanel5 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonImprimirResumenPto = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelVentas = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jTabbedPaneResumenD1 = new javax.swing.JTabbedPane();
        jPanelPagoTrabajadores = new javax.swing.JPanel();
        jPanelFooter = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanelTurnosTrabajo = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxVentas = MaterialComponentsFactory.Displayers.getComboBox("Turnos");
        jButtonCambiarTurno = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanelTerminarVentas = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonReabrirVentas = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonTerminar = MaterialComponentsFactory.Buttons.getMaterialButton();
        jPanelRefresh = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabelFecha = new javax.swing.JLabel();
        jButtonRefresh = MaterialComponentsFactory.Buttons.getOutlinedButton();

        jPanelResumenDetallado.setLayout(new java.awt.BorderLayout());
        jPanelResumenDetallado.add(jTabbedPaneResumen, java.awt.BorderLayout.CENTER);

        jComboBoxSeleccionarVentaPorTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Turno 1", "Turno 2" }));
        jComboBoxSeleccionarVentaPorTurno.setToolTipText("Seleccione el turno a visualizar");

        jPanelExtracciones.setLayout(new java.awt.BorderLayout());

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanelRoot.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelData.setLayout(new java.awt.BorderLayout());

        jTabbedPaneData.setToolTipText("");
        jTabbedPaneData.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTabbedPaneData.setOpaque(true);

        jPanelResumen.setLayout(new java.awt.BorderLayout());

        jTabbedPaneResumenD.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneResumenD.setToolTipText(null);
        jTabbedPaneResumenD.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTabbedPaneResumenD.setOpaque(true);

        jPanelGeneral.setLayout(new java.awt.BorderLayout());

        jPanelGraficaPieGenerales.setLayout(new java.awt.BorderLayout());
        jPanelGeneral.add(jPanelGraficaPieGenerales, java.awt.BorderLayout.CENTER);

        jPanel15.setMinimumSize(new java.awt.Dimension(200, 78));
        jPanel15.setPreferredSize(new java.awt.Dimension(200, 78));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel14.setToolTipText(null);
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanelVentas1.setMinimumSize(new java.awt.Dimension(200, 210));
        jPanelVentas1.setPreferredSize(new java.awt.Dimension(200, 210));
        jPanelVentas1.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Ventas");
        jPanelVentas1.add(jLabel10, java.awt.BorderLayout.NORTH);

        jPanelVentasCards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 15, 10));
        jPanelVentasCards.setLayout(new java.awt.GridLayout(2, 1));
        jPanelVentas1.add(jPanelVentasCards, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanelVentas1, java.awt.BorderLayout.NORTH);

        jPanelGastos.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Gastos");
        jPanelGastos.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanelGastosCards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 15, 10));
        jPanelGastosCards.setLayout(new java.awt.GridLayout(4, 1, 0, 5));
        jPanelGastos.add(jPanelGastosCards, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanelGastos, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTextFieldPropina.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jTextFieldPropina.setFocusTraversalPolicyProvider(true);
        jTextFieldPropina.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldPropina.setMinimumSize(new java.awt.Dimension(0, 50));
        jTextFieldPropina.setPreferredSize(new java.awt.Dimension(145, 50));
        jPanel2.add(jTextFieldPropina, new java.awt.GridBagConstraints());

        jPanel15.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanelGeneral.add(jPanel15, java.awt.BorderLayout.EAST);

        jTabbedPaneResumenD.addTab("General", jPanelGeneral);

        jPanelVentasArea.setLayout(new java.awt.BorderLayout());

        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasPorArea.setAutoCreateRowSorter(true);
        jTableVentasPorArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jTableVentasPorArea.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableVentasPorArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Neta", "Real"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVentasPorArea.setRowHeight(25);
        jTableVentasPorArea.setRowMargin(5);
        jScrollPane6.setViewportView(jTableVentasPorArea);

        jPanelVentasArea.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButtonImprimirResumenArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenArea.setText("Resumen de ventas");
        jButtonImprimirResumenArea.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel7.add(jButtonImprimirResumenArea, new java.awt.GridBagConstraints());

        jPanelVentasArea.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jTabbedPaneResumenD.addTab("Areas", jPanelVentasArea);

        jPanelDependientes.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasDependientes.setAutoCreateRowSorter(true);
        jTableVentasDependientes.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableVentasDependientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Monto", "Ordenes Atendidas", "Pago por venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVentasDependientes.setRowHeight(25);
        jTableVentasDependientes.setRowMargin(5);
        jScrollPane3.setViewportView(jTableVentasDependientes);

        jPanelDependientes.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButtonImprimirDptes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirDptes.setText("Resumen de ventas");
        jButtonImprimirDptes.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.add(jButtonImprimirDptes, new java.awt.GridBagConstraints());

        jButtonImpPagoVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpPagoVentas.setText("Pago por comision");
        jButtonImpPagoVentas.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.add(jButtonImpPagoVentas, new java.awt.GridBagConstraints());

        jPanelDependientes.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jTabbedPaneResumenD.addTab("Dependientes", jPanelDependientes);

        jPanelPtoElab.setLayout(new java.awt.BorderLayout());

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasPorCocina.setAutoCreateRowSorter(true);
        jTableVentasPorCocina.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableVentasPorCocina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVentasPorCocina.setRowHeight(25);
        jTableVentasPorCocina.setRowMargin(5);
        jScrollPane5.setViewportView(jTableVentasPorCocina);

        jPanelPtoElab.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonImprimirResumenPto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenPto.setText("Resumen de ventas");
        jButtonImprimirResumenPto.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel5.add(jButtonImprimirResumenPto, new java.awt.GridBagConstraints());

        jPanelPtoElab.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jTabbedPaneResumenD.addTab("Ptos Elaboracion", jPanelPtoElab);

        jPanelResumen.add(jTabbedPaneResumenD, java.awt.BorderLayout.CENTER);

        jTabbedPaneData.addTab("Resumen", jPanelResumen);

        jPanelVentas.setLayout(new java.awt.BorderLayout(20, 20));
        jTabbedPaneData.addTab("Ventas", jPanelVentas);

        jPanelOperaciones.setLayout(new java.awt.BorderLayout());

        jTabbedPaneResumenD1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneResumenD1.setToolTipText("");
        jTabbedPaneResumenD1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelPagoTrabajadores.setLayout(new java.awt.BorderLayout());
        jTabbedPaneResumenD1.addTab("Pago Empleados", jPanelPagoTrabajadores);

        jPanelOperaciones.add(jTabbedPaneResumenD1, java.awt.BorderLayout.CENTER);

        jTabbedPaneData.addTab("Operaciones", jPanelOperaciones);

        jPanelData.add(jTabbedPaneData, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelData, java.awt.BorderLayout.CENTER);

        jPanelFooter.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanelFooter.setPreferredSize(new java.awt.Dimension(597, 60));
        jPanelFooter.setLayout(new java.awt.BorderLayout());

        jPanelTurnosTrabajo.setMinimumSize(new java.awt.Dimension(180, 44));
        jPanelTurnosTrabajo.setPreferredSize(new java.awt.Dimension(180, 44));
        jPanelTurnosTrabajo.setLayout(new java.awt.GridBagLayout());

        jComboBoxVentas.setMinimumSize(new java.awt.Dimension(150, 50));
        jComboBoxVentas.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanelTurnosTrabajo.add(jComboBoxVentas, new java.awt.GridBagConstraints());

        jButtonCambiarTurno.setIcon(MaterialIcons.ADD);
        jButtonCambiarTurno.setToolTipText(null);
        jButtonCambiarTurno.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonCambiarTurno.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonCambiarTurno.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanelTurnosTrabajo.add(jButtonCambiarTurno, new java.awt.GridBagConstraints());

        jPanelFooter.add(jPanelTurnosTrabajo, java.awt.BorderLayout.WEST);

        jPanelTerminarVentas.setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonReabrirVentas.setText(bundle.getString("label_reabrir_ventas")); // NOI18N
        jButtonReabrirVentas.setEnabled(false);
        jButtonReabrirVentas.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanelTerminarVentas.add(jButtonReabrirVentas, new java.awt.GridBagConstraints());

        jButtonTerminar.setText("Terminar");
        jButtonTerminar.setPreferredSize(new java.awt.Dimension(140, 50));
        jButtonTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarActionPerformed(evt);
            }
        });
        jPanelTerminarVentas.add(jButtonTerminar, new java.awt.GridBagConstraints());

        jPanelFooter.add(jPanelTerminarVentas, java.awt.BorderLayout.CENTER);

        jPanelRefresh.setLayout(new java.awt.GridBagLayout());

        jLabelFecha.setText("Del 15/03/19 al 25/03/19");
        jLabelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));
        jPanelRefresh.add(jLabelFecha, new java.awt.GridBagConstraints());

        jButtonRefresh.setIcon(MaterialIcons.REFRESH);
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanelRefresh.add(jButtonRefresh, new java.awt.GridBagConstraints());

        jPanelFooter.add(jPanelRefresh, java.awt.BorderLayout.EAST);

        jPanelRoot.add(jPanelFooter, java.awt.BorderLayout.SOUTH);

        add(jPanelRoot, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarActionPerformed
        Object[] options = {"Si", "No"};
        int confirm = JOptionPane.showOptionDialog(
                Application.getInstance().getMainWindow(),
                R.RESOURCE_BUNDLE.getString("label_dialogo_terminar_exportar"),
                null,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (confirm == JOptionPane.YES_OPTION) {
            int result = fileChooser.showSaveDialog(jPanelRoot);
            if (result == JFileChooser.APPROVE_OPTION) {
                getPresenter().getOperation(ACTION_TERMINAR_EXPORTAR).doAction();
            }
        } else if (confirm == JOptionPane.NO_OPTION) {
            getPresenter().getOperation(ACTION_TERMINAR_VENTAS).doAction();
        }
    }//GEN-LAST:event_jButtonTerminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCambiarTurno;
    private javax.swing.JButton jButtonImpPagoVentas;
    private javax.swing.JButton jButtonImprimirDptes;
    private javax.swing.JButton jButtonImprimirResumenArea;
    private javax.swing.JButton jButtonImprimirResumenPto;
    private javax.swing.JButton jButtonReabrirVentas;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonTerminar;
    private javax.swing.JComboBox<String> jComboBoxSeleccionarVentaPorTurno;
    private javax.swing.JComboBox<String> jComboBoxVentas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDependientes;
    private javax.swing.JPanel jPanelExtracciones;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelGastos;
    private javax.swing.JPanel jPanelGastosCards;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelGraficaPieGenerales;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelPagoTrabajadores;
    private javax.swing.JPanel jPanelPtoElab;
    private javax.swing.JPanel jPanelRefresh;
    private javax.swing.JPanel jPanelResumen;
    private javax.swing.JPanel jPanelResumenDetallado;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JPanel jPanelTerminarVentas;
    private javax.swing.JPanel jPanelTurnosTrabajo;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JPanel jPanelVentas1;
    private javax.swing.JPanel jPanelVentasArea;
    private javax.swing.JPanel jPanelVentasCards;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPaneData;
    private javax.swing.JTabbedPane jTabbedPaneResumen;
    private javax.swing.JTabbedPane jTabbedPaneResumenD;
    private javax.swing.JTabbedPane jTabbedPaneResumenD1;
    private javax.swing.JTable jTableVentasDependientes;
    private javax.swing.JTable jTableVentasPorArea;
    private javax.swing.JTable jTableVentasPorCocina;
    private javax.swing.JTextField jTextFieldPropina;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Dimension dimension = new Dimension(190, 55);
        Action imprimirZ = getPresenter().getOperation(ACTION_IMPRIMIR_Z),
                imprimirAutorizos = getPresenter().getOperation(ACTION_IMPRIMIR_AUTORIZOS);

        addCard(null, "Venta Neta", dimension, jPanelVentasCards, getPresenter().getModel(PROP_VENTA_NETA));
        ventasTotal = addCard(null, "Total", dimension, jPanelVentasCards, getPresenter().getModel(PROP_VENTA_TOTAL), imprimirZ).getjLabelCardTitle().getText();
        addCard(null, "Autorizos", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_AUTORIZOS), imprimirAutorizos);
        insumos = addCard(null, "Insumos", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_GASTO_INSUMOS)).getjLabelCardTitle().getText();
        salarios = addCard(null, "Salarios", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_GASTO_SALARIO)).getjLabelCardTitle().getText();
        otro = addCard(null, "Otros", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_GASTO_OTROS)).getjLabelCardTitle().getText();

        Bindings.bind(jTextFieldPropina, getPresenter().getModel(PROP_PROPINA_TOTAL));//TODO: manejar enabled
        Bindings.bind(fileChooser, "selectedFile", getPresenter().getModel(PROP_FILE_FOR_EXPORT));
        Bindings.bind(jLabelFecha, getPresenter().getModel(PROP_FECHA));

//        jButtonImprimirZ.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_Z));
//        jButtonImprimirAutorizos.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_AUTORIZOS));
        Bindings.bind(jButtonReabrirVentas, "enabled", getPresenter().getModel(PROP_REABRIR_VENTAS_ENABLED));
        jButtonReabrirVentas.addActionListener(getPresenter().getOperation(ACTION_REABRIR_VENTA));

        //Areas
        Bindings.bind(jTableVentasPorArea,
                new SelectionInList<ResumenVentaAreaTablaModel>(
                        getPresenter().getModel(PROP_LISTA_RESUMEN_AREA_VENTA),
                        getPresenter().getModel(PROP_RESUMEM_AREA_SELECCIONADA)));
        jButtonImprimirResumenArea.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_AREA));

        //Dpte
        Bindings.bind(jTableVentasDependientes,
                new SelectionInList<ResumenVentaUsuarioTablaModel>(
                        getPresenter().getModel(PROP_LISTA_RESUMEN_PERSONAL_VENTA),
                        getPresenter().getModel(PROP_RESUMEN_USUARIO_SELECCIONADO)));
        jButtonImprimirDptes.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_USUARIO));
        jButtonImpPagoVentas.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION));

        //Pto elab
        Bindings.bind(jTableVentasPorCocina,
                new SelectionInList<ResumenVentaUsuarioTablaModel>(
                        getPresenter().getModel(PROP_LISTA_RESUMEN_PTO_VENTA),
                        getPresenter().getModel(PROP_RESUMEN_PTO_SELECCIONADO)));
        jButtonImprimirResumenPto.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_PTO));

        jButtonRefresh.addActionListener(getPresenter().getOperation(ACTION_REFRESCAR_VENTA));
        Bindings.bind(jComboBoxVentas, new SelectionInList<Venta>(
                getPresenter().getModel(PROP_LIST_VENTAS),
                getPresenter().getModel(PROP_VENTA_SELECCIONADA)));
        Bindings.bind(jButtonCambiarTurno, "enabled", getPresenter().getModel(PROP_CAMBIAR_TURNO_ENABLED));
        jButtonCambiarTurno.addActionListener(getPresenter().getOperation(ACTION_CREAR_NUEVO_TURNO));

        updateGraficasResumenGeneralVentas();
    }

    @Override
    public void uiInit() {
        initComponents();
        jTabbedPaneResumenD.setUI(MaterialComponentsFactory.UI.getTabbedPaneUI());
        jTabbedPaneData.setUI(MaterialComponentsFactory.UI.getTabbedPaneUI());
        initAreaTableUI();
        initUsuarioTablaUI();
        initPtoElaboracionUI();
        initOrdenTableUI();
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3 && !R.CAJERO_PERMISOS_ESPECIALES) {
            jTabbedPaneData.remove(0);
        }//TODO autorizacion en el view
        jPanelTurnosTrabajo.setVisible(R.VARIOS_TURNOS);//TODO:otro mojon
        jComboBoxSeleccionarVentaPorTurno.setEnabled(R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > 2);//TODO: otro mas
        fileChooser = new JFileChooser();
        //mesaView = new MesaListView(PresenterFacade.getPresenterFor(MesaListView.VIEW_NAME));
        if ((VentaDetailViewPresenter) getPresenter() != null) {
            jPanelVentas.add(new VentaListOrdenesView(((VentaDetailViewPresenter) getPresenter()).getVentaOrdenListViewPresenter()));
            jPanelPagoTrabajadores.add(new AsistenciaPersonalView(((VentaDetailViewPresenter) getPresenter()).getAsistenciaPersonalPresenter()));
            jTabbedPaneResumenD1.addTab("Extracciones Caja", jPanelExtracciones);
            jPanelExtracciones.add(new GastosView(((VentaDetailViewPresenter) getPresenter()).getGastosPresenter()));
        }
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    private void initPtoElaboracionUI() {
        BindableTableModel<ResumenVentaPtoElabTablaModel> ptoElabModel
                = new BindableTableModel<ResumenVentaPtoElabTablaModel>(jTableVentasPorCocina) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Monto";
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getCodigoPto();
                    case 1:
                        return getRow(rowIndex).getNombrePto();
                    case 2:
                        return getRow(rowIndex).getMontoRecaudado();
                    default:
                        return null;
                }
            }
        };
        jTableVentasPorCocina.setModel(ptoElabModel);
    }

    private void initUsuarioTablaUI() {
        BindableTableModel<ResumenVentaUsuarioTablaModel> ventasDpteModel
                = new BindableTableModel<ResumenVentaUsuarioTablaModel>(jTableVentasDependientes) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Usuario";
                    case 1:
                        return "Monto";
                    case 2:
                        return "Ordenes Atendidas";
                    case 3:
                        return "Comision por ventas";
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getUsuario();
                    case 1:
                        return getRow(rowIndex).getMontoRecaudado();
                    case 2:
                        return getRow(rowIndex).getOrdenesAtendidas();
                    case 3:
                        return getRow(rowIndex).getPagoPorVenta();
                    default:
                        return null;
                }
            }
        };
        jTableVentasDependientes.setModel(ventasDpteModel);
    }

    private void initAreaTableUI() {
        BindableTableModel<ResumenVentaAreaTablaModel> areaTablaModel
                = new BindableTableModel<ResumenVentaAreaTablaModel>(jTableVentasPorArea) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "V. Neta";
                    case 3:
                        return "V. Real";
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getCodArea();
                    case 1:
                        return getRow(rowIndex).getNombreArea();
                    case 2:
                        return getRow(rowIndex).getTotalReacuadadoNeta();
                    case 3:
                        return getRow(rowIndex).getTotalRecaudadoReal();
                    default:
                        return null;
                }
            }
        };
        jTableVentasPorArea.setModel(areaTablaModel);
    }

    private void initOrdenTableUI() {
        if (fechaFin != null) {// viene faltando cuando es un resumen detallado
//            jLabelFecha.setText("Del " + R.DATE_FORMAT.format(instance.getFecha()) + " Al "
//                    + R.DATE_FORMAT.format(fechaFin));
//            jTabbedPaneData.removeTabAt(1);
//            jButtonReabrirVentas.setVisible(false);
//            jButtonRefrescar.setVisible(false);

        }
    }

//TODO: agregar listener al bean para nada mas cambien los datos se actualize la tabla p mejor agregar los listener a los jtextfield
    private void updateGraficasResumenGeneralVentas() {
        PieChart chartPie = new PieChartBuilder().theme(Styler.ChartTheme.XChart).title("Ventas/Gastos ").build();
        chartPie.getStyler().setAnnotationType(PieStyler.AnnotationType.Percentage);
        chartPie.getStyler().setChartTitleBoxVisible(true);
        chartPie.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chartPie.getStyler().setLegendBackgroundColor(new Color(255, 255, 255, 0));

        float perdida = 0;
        System.out.println(insumos);
        System.out.println(otro);
        System.out.println(salarios);
        System.out.println(ventasTotal);
        if (insumos != null) {
            float insumo = Float.parseFloat(insumos.substring(0, insumos.lastIndexOf(" ")).replaceAll(" ", ""));
            perdida += insumo;
            chartPie.addSeries("Gastos de Insumo " + utils.setDosLugaresDecimales(insumo), insumo);
        }
        if (otro != null) {
            float otros = Float.parseFloat(otro.substring(0, otro.lastIndexOf(" ")).replaceAll(" ", ""));
            perdida += otros;
            chartPie.addSeries("Otros gastos " + utils.setDosLugaresDecimales(otros), otros);
        }
        if (salarios != null) {
            float trabajadores = Float.parseFloat(salarios.substring(0, salarios.lastIndexOf(" ")).replaceAll(" ", ""));
            perdida += trabajadores;
            chartPie.addSeries("Pago a trabajadores " + utils.setDosLugaresDecimales(trabajadores), trabajadores);
        }
        if (ventasTotal != null) {
            float gananciaRelativa = Float.parseFloat(ventasTotal.substring(0, ventasTotal.lastIndexOf(" ")).replaceAll(" ", "")) - perdida;
            chartPie.addSeries("Resto de efectivo " + utils.setDosLugaresDecimales(gananciaRelativa), gananciaRelativa).setFillColor(Color.GREEN);
        }

        XChartPanel wrapperPie = new XChartPanel(chartPie);
        jPanelGraficaPieGenerales.add(wrapperPie);
    }

    private void crearFrame() {
//        String hVentas,
//                hGastos,
//                cDate,
//                nombreMenu = R.REST_NAME;
//
//        if (getController().getInstance().getFecha().getDate() == fechaFin.getDate()
//                && getController().getInstance().getFecha().getMonth() == fechaFin.getMonth()) {
//            cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha())
//                    + "(" + nombreMenu + ")";
//            hVentas = ("Ventas del dia " + cDate);
//            hGastos = ("Gastos por productos del dia " + cDate);
//
//        } else {
//            cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha())
//                    + " al " + R.DATE_FORMAT.format(fechaFin) + "(" + nombreMenu + ")";
//            hVentas = ("Resumen de ventas del " + cDate);
//            hGastos = ("Resumen de gastos del " + cDate);
//        }
//
//        jTabbedPaneResumen.addTab("Resumen Total ", new Resumenes(getController().getInstance(), fechaFin, hVentas, hGastos));
//        List<Cocina> cocinas = CocinaDAO.getInstance().findAll();
//        for (int i = 0; i < cocinas.size(); i++) {
//            jTabbedPaneResumen.addTab(cocinas.get(i).getNombreCocina(),
//                    new Resumenes(getController().getInstance(), cocinas.get(i), fechaFin,
//                            "Ventas de " + cocinas.get(i).getNombreCocina() + " " + cDate,
//                            "Gastos por producto " + cocinas.get(i).getNombreCocina() + " " + cDate));
//        }
    }

    private void updateTableResumenDetallado() {
        if (fechaFin != null) {
            jTabbedPaneData.addTab("Resumen Detallado", jPanelResumenDetallado);
            crearFrame();
        }
    }

    private Card addCard(String title, String subtitle, Dimension dimension, JPanel panel, ValueModel valueModel, Action... menuActions) {
        Card c = MaterialComponentsFactory.Displayers.getSmallCardValueModel(null, title, subtitle, valueModel, menuActions);
        c.setPreferredSize(dimension);
        c.setMaximumSize(dimension);
        panel.add(c);
//        panel.add(Box.createRigidArea(new Dimension(1, 1)));

        return c;
    }
}
