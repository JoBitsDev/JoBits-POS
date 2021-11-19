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
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductovOrden;
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
import com.jobits.pos.ui.trabajadores.AsistenciaPersonalView;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.pos.utils.utils;
import com.jobits.pos.ui.venta.orden.VentaListOrdenesView;
import com.jobits.pos.ui.mainmenu.MenuBarClass;
import com.jobits.pos.core.repo.impl.ConfiguracionDAO;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewModel.*;
import com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter.*;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.ui.components.swing.displayers.Card;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class VentaDetailView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Comenzar Turno";
    Date fechaFin;
    MesaListView mesaView;
    private JFileChooser fileChooser;

    public VentaDetailView(VentaDetailViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelResumenDetallado = new javax.swing.JPanel();
        jTabbedPaneResumen = new javax.swing.JTabbedPane();
        jComboBoxSeleccionarVentaPorTurno = new javax.swing.JComboBox<>();
        jPanelExtracciones = new javax.swing.JPanel();
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
        jPanel19 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxAreaList = MaterialComponentsFactory.Displayers.getComboBox("");
        jScrollPane6 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasPorArea = new javax.swing.JTable();
        jPanel16 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel17 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelTotalAreas = new javax.swing.JLabel();
        jPanel18 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonImprimirResumenArea = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelDependientes = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel7 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxDependientesList = MaterialComponentsFactory.Displayers.getComboBox("");
        jScrollPane3 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasDependientes = new javax.swing.JTable();
        jPanel9 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel12 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTotalDependientes = new javax.swing.JLabel();
        jPanel13 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonImprimirDptes = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonImpPagoVentas = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelPtoElab = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel4 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxCocinasList = MaterialComponentsFactory.Displayers.getComboBox("");
        jScrollPane5 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasPorCocina = new javax.swing.JTable();
        jPanel5 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel10 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelTotalCocina = new javax.swing.JLabel();
        jPanel11 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonImprimirResumenPto = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelMesas = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jComboBoxMesaList = MaterialComponentsFactory.Displayers.getComboBox("");
        jScrollPane1 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableVentasPorMesa = new javax.swing.JTable();
        jPanel3 = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanel8 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelTotalMesas = new javax.swing.JLabel();
        jPanel6 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonImpimirResumenComisionPorcentual = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonImpimirResumenMesas1 = MaterialComponentsFactory.Buttons.getLinedButton();
        jPanelVentas = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jTabbedPaneResumenD1 = new javax.swing.JTabbedPane();
        jPanelPagoTrabajadores = new javax.swing.JPanel();

        jPanelResumenDetallado.setLayout(new java.awt.BorderLayout());
        jPanelResumenDetallado.add(jTabbedPaneResumen, java.awt.BorderLayout.CENTER);

        jComboBoxSeleccionarVentaPorTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Turno 1", "Turno 2" }));
        jComboBoxSeleccionarVentaPorTurno.setToolTipText("Seleccione el turno a visualizar");

        jPanelExtracciones.setLayout(new java.awt.BorderLayout());

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

        jPanel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 15));
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jComboBoxAreaList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxAreaList.setMaximumSize(new java.awt.Dimension(250, 26));
        jComboBoxAreaList.setMinimumSize(new java.awt.Dimension(250, 26));
        jComboBoxAreaList.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel19.add(jComboBoxAreaList);

        jPanelVentasArea.add(jPanel19, java.awt.BorderLayout.NORTH);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasPorArea.setAutoCreateRowSorter(true);
        jTableVentasPorArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jTableVentasPorArea.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel16.setOpaque(false);
        jPanel16.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Total: ");
        jPanel17.add(jLabel4, new java.awt.GridBagConstraints());

        jLabelTotalAreas.setText("xx.xx");
        jPanel17.add(jLabelTotalAreas, new java.awt.GridBagConstraints());

        jPanel16.add(jPanel17, java.awt.BorderLayout.WEST);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImprimirResumenArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenArea.setText("Resumen de ventas");
        jButtonImprimirResumenArea.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel18.add(jButtonImprimirResumenArea);

        jPanel16.add(jPanel18, java.awt.BorderLayout.EAST);

        jPanelVentasArea.add(jPanel16, java.awt.BorderLayout.SOUTH);

        jTabbedPaneResumenD.addTab("Areas", jPanelVentasArea);

        jPanelDependientes.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 15));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jComboBoxDependientesList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDependientesList.setMaximumSize(new java.awt.Dimension(250, 26));
        jComboBoxDependientesList.setMinimumSize(new java.awt.Dimension(250, 26));
        jComboBoxDependientesList.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel7.add(jComboBoxDependientesList);

        jPanelDependientes.add(jPanel7, java.awt.BorderLayout.NORTH);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasDependientes.setAutoCreateRowSorter(true);
        jTableVentasDependientes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Total: ");
        jPanel12.add(jLabel3, new java.awt.GridBagConstraints());

        jLabelTotalDependientes.setText("xx.xx");
        jPanel12.add(jLabelTotalDependientes, new java.awt.GridBagConstraints());

        jPanel9.add(jPanel12, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImprimirDptes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirDptes.setText("Resumen de ventas");
        jButtonImprimirDptes.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel13.add(jButtonImprimirDptes);

        jButtonImpPagoVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpPagoVentas.setText("Pago por comision");
        jButtonImpPagoVentas.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel13.add(jButtonImpPagoVentas);

        jPanel9.add(jPanel13, java.awt.BorderLayout.EAST);

        jPanelDependientes.add(jPanel9, java.awt.BorderLayout.SOUTH);

        jTabbedPaneResumenD.addTab("Dependientes", jPanelDependientes);

        jPanelPtoElab.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 15));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jComboBoxCocinasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCocinasList.setMaximumSize(new java.awt.Dimension(250, 26));
        jComboBoxCocinasList.setMinimumSize(new java.awt.Dimension(250, 26));
        jComboBoxCocinasList.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel4.add(jComboBoxCocinasList);

        jPanelPtoElab.add(jPanel4, java.awt.BorderLayout.NORTH);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasPorCocina.setAutoCreateRowSorter(true);
        jTableVentasPorCocina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Total: ");
        jPanel10.add(jLabel2, new java.awt.GridBagConstraints());

        jLabelTotalCocina.setText("xx.xx");
        jPanel10.add(jLabelTotalCocina, new java.awt.GridBagConstraints());

        jPanel5.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImprimirResumenPto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenPto.setText("Resumen de ventas");
        jButtonImprimirResumenPto.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel11.add(jButtonImprimirResumenPto);

        jPanel5.add(jPanel11, java.awt.BorderLayout.EAST);

        jPanelPtoElab.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jTabbedPaneResumenD.addTab("Ptos Elaboracion", jPanelPtoElab);

        jPanelMesas.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 15));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jComboBoxMesaList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMesaList.setMaximumSize(new java.awt.Dimension(250, 26));
        jComboBoxMesaList.setMinimumSize(new java.awt.Dimension(250, 26));
        jComboBoxMesaList.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel1.add(jComboBoxMesaList);

        jPanelMesas.add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTableVentasPorMesa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableVentasPorMesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableVentasPorMesa);

        jPanelMesas.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        jPanel3.setPreferredSize(new java.awt.Dimension(180, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Total: ");
        jPanel8.add(jLabel1, new java.awt.GridBagConstraints());

        jLabelTotalMesas.setText("xx.xx");
        jPanel8.add(jLabelTotalMesas, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel8, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImpimirResumenComisionPorcentual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpimirResumenComisionPorcentual.setText("Resumen de Comision %");
        jButtonImpimirResumenComisionPorcentual.setMaximumSize(new java.awt.Dimension(230, 50));
        jButtonImpimirResumenComisionPorcentual.setMinimumSize(new java.awt.Dimension(230, 50));
        jButtonImpimirResumenComisionPorcentual.setPreferredSize(new java.awt.Dimension(230, 50));
        jPanel6.add(jButtonImpimirResumenComisionPorcentual);

        jButtonImpimirResumenMesas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpimirResumenMesas1.setText("Resumen de Ventas");
        jButtonImpimirResumenMesas1.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel6.add(jButtonImpimirResumenMesas1);

        jPanel3.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanelMesas.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jTabbedPaneResumenD.addTab("Mesas", jPanelMesas);

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

        add(jPanelRoot, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarActionPerformed
        Object[] options = {"Si", "No"};
        int confirm = JOptionPane.showOptionDialog(
                Application.getInstance().getMainWindow(),
                ResourceHandler.getString("label_dialogo_terminar_exportar"),
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
    private javax.swing.JButton jButtonImpimirResumenComisionPorcentual;
    private javax.swing.JButton jButtonImpimirResumenMesas1;
    private javax.swing.JButton jButtonImprimirDptes;
    private javax.swing.JButton jButtonImprimirResumenArea;
    private javax.swing.JButton jButtonImprimirResumenPto;
    private javax.swing.JButton jButtonReabrirVentas;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonTerminar;
    private javax.swing.JComboBox<String> jComboBoxAreaList;
    private javax.swing.JComboBox<String> jComboBoxCocinasList;
    private javax.swing.JComboBox<String> jComboBoxDependientesList;
    private javax.swing.JComboBox<String> jComboBoxMesaList;
    private javax.swing.JComboBox<String> jComboBoxSeleccionarVentaPorTurno;
    private javax.swing.JComboBox<String> jComboBoxVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelTotalAreas;
    private javax.swing.JLabel jLabelTotalCocina;
    private javax.swing.JLabel jLabelTotalDependientes;
    private javax.swing.JLabel jLabelTotalMesas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDependientes;
    private javax.swing.JPanel jPanelExtracciones;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelGastos;
    private javax.swing.JPanel jPanelGastosCards;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelGraficaPieGenerales;
    private javax.swing.JPanel jPanelMesas;
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
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JTable jTableVentasPorMesa;
    private javax.swing.JTextField jTextFieldPropina;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Dimension dimension = new Dimension(190, 55);
        Action imprimirZ = getPresenter().getOperation(ACTION_IMPRIMIR_Z),
                imprimirAutorizos = getPresenter().getOperation(ACTION_IMPRIMIR_AUTORIZOS);

        addCard(null, "Venta Neta", dimension, jPanelVentasCards, getPresenter().getModel(PROP_VENTA_NETA));
        addCard(null, "Total", dimension, jPanelVentasCards, getPresenter().getModel(PROP_VENTA_TOTAL), imprimirZ);
        addCard(null, "Autorizos", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_AUTORIZOS), imprimirAutorizos);
        addCard(null, "Insumos", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_GASTO_INSUMOS));
        addCard(null, "Salarios", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_GASTO_SALARIO));
        addCard(null, "Otros", dimension, jPanelGastosCards, getPresenter().getModel(PROP_TOTAL_GASTO_OTROS));

        Bindings.bind(jTextFieldPropina, getPresenter().getModel(PROP_PROPINA_TOTAL));//TODO: manejar enabled
        Bindings.bind(fileChooser, "selectedFile", getPresenter().getModel(PROP_FILE_FOR_EXPORT));
        Bindings.bind(jLabelFecha, getPresenter().getModel(PROP_FECHA));
        Bindings.bind(MenuBarClass.getInstance().getjLabelFecha(), getPresenter().getModel(PROP_FECHA));

//        jButtonImprimirZ.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_Z));
//        jButtonImprimirAutorizos.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_AUTORIZOS));
        Bindings.bind(jButtonReabrirVentas, "enabled", getPresenter().getModel(PROP_REABRIR_VENTAS_ENABLED));
        jButtonReabrirVentas.addActionListener(getPresenter().getOperation(ACTION_REABRIR_VENTA));

        //Mesas
        Bindings.bind(jTableVentasPorMesa, new SelectionInList<ProductovOrden>(
                getPresenter().getModel(PROP_LISTA_PRODUCTOS_POR_MESA),
                getPresenter().getModel(PROP_PRODUCTO_POR_MESA_SELECCIONADO)));
        Bindings.bind(jComboBoxMesaList, new SelectionInList<Mesa>(
                getPresenter().getModel(PROP_LISTA_MESAS),
                getPresenter().getModel(PROP_MESA_SELECCIONADA)));

        //Pto Elab
        Bindings.bind(jTableVentasPorCocina, new SelectionInList<ProductovOrden>(
                getPresenter().getModel(PROP_LISTA_PRODUCTOS_POR_COCINA),
                getPresenter().getModel(PROP_PRODUCTO_POR_COCINA_SELECCIONADO)));
        Bindings.bind(jComboBoxCocinasList, new SelectionInList<Cocina>(
                getPresenter().getModel(PROP_LISTA_COCINAS),
                getPresenter().getModel(PROP_COCINA_SELECCIONADA)));

        //Dependiente
        Bindings.bind(jTableVentasDependientes, new SelectionInList<ProductovOrden>(
                getPresenter().getModel(PROP_LISTA_PRODUCTOS_POR_DEPENDIENTES),
                getPresenter().getModel(PROP_PRODUCTO_POR_DEPENDIENTE_SELECCIONADO)));
        Bindings.bind(jComboBoxDependientesList, new SelectionInList<Personal>(
                getPresenter().getModel(PROP_LISTA_DEPENDIENTES),
                getPresenter().getModel(PROP_PERSONAL_SELECCIONADO)));

        //Area
        Bindings.bind(jTableVentasPorArea, new SelectionInList<ProductovOrden>(
                getPresenter().getModel(PROP_LISTA_PRODUCTOS_POR_AREA),
                getPresenter().getModel(PROP_PRODUCTO_POR_AREA_SELECCIONADO)));
        Bindings.bind(jComboBoxAreaList, new SelectionInList<Area>(
                getPresenter().getModel(PROP_LISTA_AREAS),
                getPresenter().getModel(PROP_AREA_SELECCIONADA)));

        jButtonImprimirResumenArea.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_AREA));
        jButtonImprimirDptes.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_USUARIO));
        jButtonImpPagoVentas.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION));
        jButtonImprimirResumenPto.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_PTO));
        jButtonImpimirResumenMesas1.addActionListener(getPresenter().getOperation(ACTION_IMPIMIR_RESUMEN_MESA));
        jButtonImpimirResumenComisionPorcentual.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_COMISION_PORCENTUAL));

        jButtonRefresh.addActionListener(getPresenter().getOperation(ACTION_REFRESCAR_VENTA));
        Bindings.bind(jButtonCambiarTurno, "enabled", getPresenter().getModel(PROP_CAMBIAR_TURNO_ENABLED));
//        Bindings.bind(jPanelResumen, "visible", getPresenter().getModel(PROP_SHOW_PANELES));
        jButtonCambiarTurno.addActionListener(getPresenter().getOperation(ACTION_CREAR_NUEVO_TURNO));

        updateGraficasResumenGeneralVentas();

        //Totales Resumenes
        Bindings.bind(jLabelTotalMesas, getPresenter().getModel(PROP_TOTAL_RESUMEN_MESA));//TODO: manejar enabled
        Bindings.bind(jLabelTotalCocina, getPresenter().getModel(PROP_TOTAL_RESUMEN_COCINA));//TODO: manejar enabled
        Bindings.bind(jLabelTotalDependientes, getPresenter().getModel(PROP_TOTAL_RESUMEN_DEPENDIENTE));//TODO: manejar enabled
        Bindings.bind(jLabelTotalAreas, getPresenter().getModel(PROP_TOTAL_RESUMEN_AREA));//TODO: manejar enabled
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
        initMesaTableUI();
//        if (UserResolver.resolveUser(Personal.class).getPuestoTrabajonombrePuesto().getNivelAcceso() < 3 && !R.CAJERO_PERMISOS_ESPECIALES) {
//            jTabbedPaneData.remove(0);
//        }//TODO autorizacion en el view
        jPanelTurnosTrabajo.setVisible(ConfiguracionDAO.getInstance().find(R.SettingID.GENERAL_TURNOS_VARIOS).getValor() == 1);//TODO:otro mojon
        jComboBoxSeleccionarVentaPorTurno.setEnabled(UserResolver.resolveUser(Personal.class).getPuestoTrabajonombrePuesto().getNivelAcceso() > 2);//TODO: otro mas
        fileChooser = new JFileChooser();
        //mesaView = new MesaListView(PresenterFacade.getPresenterFor(MesaListView.VIEW_NAME));
        if (getPresenter() != null) {
            jPanelVentas.add(new VentaListOrdenesView((getPresenter()).getVentaOrdenListViewPresenter()));
            jPanelPagoTrabajadores.add(new AsistenciaPersonalView((getPresenter()).getAsistenciaPersonalPresenter()));
            jTabbedPaneResumenD1.addTab("Extracciones Caja", jPanelExtracciones);
            jPanelExtracciones.add(new GastosView((getPresenter()).getGastosPresenter()));
        }
        initListeners();
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    private void initPtoElaboracionUI() {
        BindableTableModel<ProductovOrden> pvTableModel
                = new BindableTableModel<ProductovOrden>(jTableVentasPorCocina) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Producto Venta";
                    case 1:
                        return "Precio (" + R.COIN_SUFFIX + " )";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Recaudado (" + R.COIN_SUFFIX + " )";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ProductovOrden pv = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return pv.getProductoVenta().getNombre();
                    case 1:
                        return pv.getPrecioVendido();
                    case 2:
                        return utils.setDosLugaresDecimalesFloat(pv.getCantidad());
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(pv.getPrecioVendido() * pv.getCantidad());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                    case 2:
                    case 3:
                        return Float.class;
                    default:
                        return String.class;
                }
            }
        };
        jTableVentasPorCocina.setModel(pvTableModel);
        jTableVentasPorCocina.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableVentasPorCocina.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
        jTableVentasPorCocina.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
    }

    private void initUsuarioTablaUI() {
        BindableTableModel<ProductovOrden> pvTableModel
                = new BindableTableModel<ProductovOrden>(jTableVentasDependientes) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Producto Venta";
                    case 1:
                        return "Precio (" + R.COIN_SUFFIX + " )";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Recaudado (" + R.COIN_SUFFIX + " )";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ProductovOrden pv = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return pv.getProductoVenta().getNombre();
                    case 1:
                        return pv.getPrecioVendido();
                    case 2:
                        return utils.setDosLugaresDecimalesFloat(pv.getCantidad());
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(pv.getPrecioVendido() * pv.getCantidad());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                    case 2:
                    case 3:
                        return Float.class;
                    default:
                        return String.class;
                }
            }
        };
        jTableVentasDependientes.setModel(pvTableModel);
        jTableVentasDependientes.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableVentasDependientes.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
        jTableVentasDependientes.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
    }

    private void initAreaTableUI() {
        BindableTableModel<ProductovOrden> pvTableModel
                = new BindableTableModel<ProductovOrden>(jTableVentasPorArea) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Producto Venta";
                    case 1:
                        return "Precio (" + R.COIN_SUFFIX + " )";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Recaudado (" + R.COIN_SUFFIX + " )";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ProductovOrden pv = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return pv.getProductoVenta().getNombre();
                    case 1:
                        return pv.getPrecioVendido();
                    case 2:
                        return utils.setDosLugaresDecimalesFloat(pv.getCantidad());
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(pv.getPrecioVendido() * pv.getCantidad());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                    case 2:
                    case 3:
                        return Float.class;
                    default:
                        return String.class;
                }
            }
        };
        jTableVentasPorArea.setModel(pvTableModel);
        jTableVentasPorArea.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableVentasPorArea.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
        jTableVentasPorArea.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
    }

    private void initMesaTableUI() {
        BindableTableModel<ProductovOrden> pvTableModel
                = new BindableTableModel<ProductovOrden>(jTableVentasPorMesa) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Producto Venta";
                    case 1:
                        return "Precio (" + R.COIN_SUFFIX + " )";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Recaudado (" + R.COIN_SUFFIX + " )";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ProductovOrden pv = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return pv.getProductoVenta().getNombre();
                    case 1:
                        return pv.getPrecioVendido();
                    case 2:
                        return utils.setDosLugaresDecimalesFloat(pv.getCantidad());
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(pv.getPrecioVendido() * pv.getCantidad());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                    case 2:
                    case 3:
                        return Float.class;
                    default:
                        return String.class;
                }
            }
        };
        jTableVentasPorMesa.setModel(pvTableModel);
        jTableVentasPorMesa.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableVentasPorMesa.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
        jTableVentasPorMesa.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
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

    private void initListeners() {
        getPresenter().addPropertyChangeListener(PROP_HIDE_PANEL, (PropertyChangeEvent evt) -> {
            updateGraficasResumenGeneralVentas();
            boolean value = (boolean) evt.getNewValue();
            jTabbedPaneData.setEnabledAt(0, !value);
            if (value) {
                jTabbedPaneData.setSelectedIndex(1);
            }
        });
    }

//TODO: agregar listener al bean para nada mas cambien los datos se actualize la tabla p mejor agregar los listener a los jtextfield
    private void updateGraficasResumenGeneralVentas() {
        PieChart chartPie = new PieChartBuilder().theme(Styler.ChartTheme.XChart).title("Ventas/Gastos ").build();
        chartPie.getStyler().setAnnotationType(PieStyler.AnnotationType.Percentage);
        chartPie.getStyler().setChartTitleBoxVisible(true);
        chartPie.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chartPie.getStyler().setLegendBackgroundColor(new Color(255, 255, 255, 0));
        
        String insumos, salarios, otro, ventasTotal;

        insumos = getPresenter().getBean().getTotal_gasto_insumos();
        otro = getPresenter().getBean().getTotal_gasto_otros();
        salarios = getPresenter().getBean().getTotal_gasto_salario();
        ventasTotal = getPresenter().getBean().getVenta_total();

        float perdida = 0;
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
        jPanelGraficaPieGenerales.removeAll();
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

    @Override
    public VentaDetailViewPresenter getPresenter() {
        return (VentaDetailViewPresenter) super.getPresenter(); //To change body of generated methods, choose Tools | Templates.
    }

}
