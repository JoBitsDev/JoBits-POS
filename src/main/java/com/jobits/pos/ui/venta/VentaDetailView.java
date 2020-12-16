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
import com.jobits.pos.controller.gasto.GastoOperacionController;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalController;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.gastos.GastoOperacionView;
import com.jobits.pos.ui.gastos.GastosView;
import com.jobits.pos.ui.gastos.presenter.GastosViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.trabajadores.AsistenciaPersonalView;
import com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.orden.OldVentaListOrdenesView;
import com.jobits.pos.ui.venta.orden.VentaListOrdenesView;
import com.jobits.pos.ui.venta.orden.presenter.VentaOrdenListViewPresenter;
import com.jobits.pos.ui.venta.presenter.ResumenVentaAreaTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaUsuarioTablaModel;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewModel.*;
import com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter.*;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.ui.components.swing.displayers.Card;
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
        jPanelRoot = new javax.swing.JPanel();
        jPanelData = new javax.swing.JPanel();
        jTabbedPaneData = new javax.swing.JTabbedPane();
        jPanelResumen = new javax.swing.JPanel();
        jTabbedPaneResumenD = new javax.swing.JTabbedPane();
        jPanelGeneral = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanelGrafica = new javax.swing.JPanel();
        jPanelGraficaPieGenerales = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanelVentasCards = new javax.swing.JPanel();
        jPanelGastosCards = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldPropina = MaterialComponentsFactory.Input.getTextFielPrecioVenta("0.0", "Propina", R.COIN_SUFFIX);
        jPanelAreas = new javax.swing.JPanel();
        jPanelVentasArea = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableVentasPorArea = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButtonImprimirResumenArea = new javax.swing.JButton();
        jPanelDependientes = new javax.swing.JPanel();
        jPanelVentasCamareras = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVentasDependientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButtonImprimirDptes = new javax.swing.JButton();
        jButtonImpPagoVentas = new javax.swing.JButton();
        jPanelPtoElab = new javax.swing.JPanel();
        jPanelCocinaArea = new javax.swing.JPanel();
        jPanelVentasCocinas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVentasPorCocina = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButtonImprimirResumenPto = new javax.swing.JButton();
        jPanelVentas = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jTabbedPaneResumenD1 = new javax.swing.JTabbedPane();
        jPanelExtracciones = new javax.swing.JPanel();
        jPanelPagoTrabajadores = new javax.swing.JPanel();
        jPanelFooter = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanelTurnosTrabajo = new javax.swing.JPanel();
        jButtonCambiarTurno = new javax.swing.JButton();
        jComboBoxSeleccionarVentaPorTurno = new javax.swing.JComboBox<>();
        jPanelTerminarVentas = new javax.swing.JPanel();
        jButtonReabrirVentas = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonTerminar = MaterialComponentsFactory.Buttons.getMaterialButton();
        jPanelRefresh = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jButtonRefresh = new javax.swing.JButton();

        jPanelResumenDetallado.setLayout(new java.awt.BorderLayout());
        jPanelResumenDetallado.add(jTabbedPaneResumen, java.awt.BorderLayout.CENTER);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout());

        jPanelRoot.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelData.setLayout(new java.awt.BorderLayout());

        jTabbedPaneData.setToolTipText("");
        jTabbedPaneData.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelResumen.setLayout(new java.awt.BorderLayout());

        jTabbedPaneResumenD.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneResumenD.setToolTipText(null);
        jTabbedPaneResumenD.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelGeneral.setLayout(new java.awt.BorderLayout());

        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jPanelGrafica.setLayout(new java.awt.BorderLayout());

        jPanelGraficaPieGenerales.setLayout(new java.awt.BorderLayout());
        jPanelGrafica.add(jPanelGraficaPieGenerales, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanelGrafica);

        jPanelGeneral.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel15.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel15.setMinimumSize(new java.awt.Dimension(200, 20));
        jPanel15.setPreferredSize(new java.awt.Dimension(200, 78));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel14.setToolTipText(null);
        jPanel14.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel14.setMinimumSize(new java.awt.Dimension(200, 20));
        jPanel14.setPreferredSize(new java.awt.Dimension(200, 78));
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelVentasCards.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanelVentasCards.setToolTipText(null);
        jPanelVentasCards.setMaximumSize(new java.awt.Dimension(32767, 170));
        jPanelVentasCards.setMinimumSize(new java.awt.Dimension(170, 49));
        jPanelVentasCards.setPreferredSize(new java.awt.Dimension(170, 170));
        jPanel14.add(jPanelVentasCards);

        jPanelGastosCards.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Gastos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanelGastosCards.setToolTipText(null);
        jPanel14.add(jPanelGastosCards);

        jPanel15.add(jPanel14, java.awt.BorderLayout.CENTER);

        jTextFieldPropina.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jTextFieldPropina.setFocusTraversalPolicyProvider(true);
        jTextFieldPropina.setMaximumSize(new java.awt.Dimension(100, 50));
        jTextFieldPropina.setMinimumSize(new java.awt.Dimension(0, 50));
        jTextFieldPropina.setPreferredSize(new java.awt.Dimension(145, 50));
        jPanel2.add(jTextFieldPropina);

        jPanel15.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanelGeneral.add(jPanel15, java.awt.BorderLayout.EAST);

        jTabbedPaneResumenD.addTab("General", jPanelGeneral);

        jPanelAreas.setLayout(new javax.swing.BoxLayout(jPanelAreas, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelVentasArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de ventas por area"));
        jPanelVentasArea.setOpaque(false);
        jPanelVentasArea.setLayout(new java.awt.BorderLayout());

        jTableVentasPorArea.setAutoCreateRowSorter(true);
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

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonImprimirResumenArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonImprimirResumenArea.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jPanel7.add(jButtonImprimirResumenArea);

        jPanelVentasArea.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanelAreas.add(jPanelVentasArea);

        jTabbedPaneResumenD.addTab("Areas", jPanelAreas);

        jPanelDependientes.setLayout(new java.awt.BorderLayout());

        jPanelVentasCamareras.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen Ventas Dependientes"));
        jPanelVentasCamareras.setOpaque(false);
        jPanelVentasCamareras.setLayout(new java.awt.BorderLayout());

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

        jPanelVentasCamareras.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButtonImprimirDptes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirDptes.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jPanel4.add(jButtonImprimirDptes, java.awt.BorderLayout.WEST);

        jButtonImpPagoVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpPagoVentas.setText("Imprimir pago por comision");
        jPanel4.add(jButtonImpPagoVentas, java.awt.BorderLayout.EAST);

        jPanelVentasCamareras.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanelDependientes.add(jPanelVentasCamareras, java.awt.BorderLayout.CENTER);

        jTabbedPaneResumenD.addTab("Dependientes", jPanelDependientes);

        jPanelPtoElab.setLayout(new java.awt.BorderLayout());

        jPanelCocinaArea.setOpaque(false);
        jPanelCocinaArea.setLayout(new java.awt.GridLayout(1, 0));

        jPanelVentasCocinas.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_venta_punto_elaboracion"))); // NOI18N
        jPanelVentasCocinas.setOpaque(false);
        jPanelVentasCocinas.setLayout(new java.awt.BorderLayout());

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

        jPanelVentasCocinas.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonImprimirResumenPto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenPto.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jPanel5.add(jButtonImprimirResumenPto);

        jPanelVentasCocinas.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanelCocinaArea.add(jPanelVentasCocinas);

        jPanelPtoElab.add(jPanelCocinaArea, java.awt.BorderLayout.CENTER);

        jTabbedPaneResumenD.addTab("Ptos Elaboracion", jPanelPtoElab);

        jPanelResumen.add(jTabbedPaneResumenD, java.awt.BorderLayout.CENTER);

        jTabbedPaneData.addTab("Resumen", jPanelResumen);

        jPanelVentas.setLayout(new java.awt.BorderLayout(20, 20));
        jTabbedPaneData.addTab("Ventas", jPanelVentas);

        jPanelOperaciones.setLayout(new java.awt.BorderLayout());

        jTabbedPaneResumenD1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneResumenD1.setToolTipText("");
        jTabbedPaneResumenD1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelExtracciones.setLayout(new java.awt.BorderLayout());
        jTabbedPaneResumenD1.addTab("Extracciones Caja", jPanelExtracciones);

        jPanelPagoTrabajadores.setLayout(new java.awt.BorderLayout());
        jTabbedPaneResumenD1.addTab("Pago Empleados", jPanelPagoTrabajadores);

        jPanelOperaciones.add(jTabbedPaneResumenD1, java.awt.BorderLayout.CENTER);

        jTabbedPaneData.addTab("Operaciones", jPanelOperaciones);

        jPanelData.add(jTabbedPaneData, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelData, java.awt.BorderLayout.CENTER);

        jPanelFooter.setBackground(new java.awt.Color(204, 204, 204));
        jPanelFooter.setPreferredSize(new java.awt.Dimension(597, 50));
        jPanelFooter.setLayout(new java.awt.BorderLayout());

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(597, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jButtonCambiarTurno.setText(bundle.getString("label_cambiar_turno")); // NOI18N
        jPanelTurnosTrabajo.add(jButtonCambiarTurno);

        jComboBoxSeleccionarVentaPorTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Turno 1", "Turno 2" }));
        jComboBoxSeleccionarVentaPorTurno.setToolTipText("Seleccione el turno a visualizar");
        jPanelTurnosTrabajo.add(jComboBoxSeleccionarVentaPorTurno);

        jPanel8.add(jPanelTurnosTrabajo, java.awt.BorderLayout.WEST);

        jPanelTerminarVentas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jButtonReabrirVentas.setText(bundle.getString("label_reabrir_ventas")); // NOI18N
        jButtonReabrirVentas.setEnabled(false);
        jButtonReabrirVentas.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanelTerminarVentas.add(jButtonReabrirVentas);

        jButtonTerminar.setText("Terminar");
        jButtonTerminar.setPreferredSize(new java.awt.Dimension(100, 50));
        jButtonTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarActionPerformed(evt);
            }
        });
        jPanelTerminarVentas.add(jButtonTerminar);

        jPanel8.add(jPanelTerminarVentas, java.awt.BorderLayout.CENTER);

        jPanelRefresh.setLayout(new javax.swing.BoxLayout(jPanelRefresh, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabelFecha.setText("Del 15/03/19 al 25/03/19");
        jLabelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));
        jPanel1.add(jLabelFecha);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/icons pack/refrescar_indigo.png"))); // NOI18N
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jButtonRefresh);

        jPanelRefresh.add(jPanel1);

        jPanel8.add(jPanelRefresh, java.awt.BorderLayout.EAST);

        jPanelFooter.add(jPanel8, java.awt.BorderLayout.SOUTH);

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
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAreas;
    private javax.swing.JPanel jPanelCocinaArea;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDependientes;
    private javax.swing.JPanel jPanelExtracciones;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelGastosCards;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelGrafica;
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
    private javax.swing.JPanel jPanelVentasArea;
    private javax.swing.JPanel jPanelVentasCamareras;
    private javax.swing.JPanel jPanelVentasCards;
    private javax.swing.JPanel jPanelVentasCocinas;
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
        
        Bindings.bind(jButtonCambiarTurno, "enabled", getPresenter().getModel(PROP_CAMBIAR_TURNO_ENABLED));
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
        VentaDetailService ventaService = ((VentaDetailViewPresenter)getPresenter()).getService();
        VentaOrdenListViewPresenter ventaOrdenPresenter = new VentaOrdenListViewPresenter(ventaService, new OrdenController());
        jPanelVentas.add(new VentaListOrdenesView(ventaOrdenPresenter));

        jPanelPagoTrabajadores.add(new AsistenciaPersonalView(new AsistenciaPersonalPresenter(ventaInstance)));
        jPanelExtracciones.add(new GastosView(new GastosViewPresenter(new GastoOperacionController(ventaInstance))));
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
        if (insumos != null) {
            float insumo = Float.parseFloat(insumos.split(" ")[0]);
            perdida += insumo;
            chartPie.addSeries("Gastos de Insumo " + utils.setDosLugaresDecimales(insumo), insumo);
        }
        if (otro != null) {
            float otros = Float.parseFloat(otro.split(" ")[0]);
            perdida += otros;
            chartPie.addSeries("Otros gastos " + utils.setDosLugaresDecimales(otros), otros);
        }
        if (salarios != null) {
            float trabajadores = Float.parseFloat(salarios.split(" ")[0]);
            perdida += trabajadores;
            chartPie.addSeries("Pago a trabajadores " + utils.setDosLugaresDecimales(trabajadores), trabajadores);
        }
        if (ventasTotal != null) {
            float gananciaRelativa = Float.parseFloat(ventasTotal.split(" ")[0]) - perdida;
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
        panel.add(Box.createRigidArea(new Dimension(1, 1)));
        return c;
    }
}
